package com.group.makity.leMakity.services;

import com.group.makity.leMakity.dtos.CommandeClientDto;
import com.group.makity.leMakity.dtos.LigneCommandeClientDto;
import com.group.makity.leMakity.dtos.MvtStkDto;
import com.group.makity.leMakity.entities.*;
import com.group.makity.leMakity.exceptions.AppUserNotFoundException;
import com.group.makity.leMakity.exceptions.InvalidCmdClientException;
import com.group.makity.leMakity.exceptions.InvalidMvtStkException;
import com.group.makity.leMakity.exceptions.InvalidOperationException;
import com.group.makity.leMakity.mappers.AppUserMapper;
import com.group.makity.leMakity.mappers.CommandeClientMapper;
import com.group.makity.leMakity.mappers.LigneCommandeClientMapper;
import com.group.makity.leMakity.mappers.ProductMapper;
import com.group.makity.leMakity.repositories.AppUserRepository;
import com.group.makity.leMakity.repositories.CommandeClientRepository;
import com.group.makity.leMakity.repositories.LigneCommandeClientRepository;
import com.group.makity.leMakity.repositories.ProductRepository;
import com.group.makity.leMakity.validator.CommandeClientValidator;
import com.group.makity.leMakity.validator.ProductValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

  private CommandeClientRepository commandeClientRepository;
  private LigneCommandeClientRepository ligneCommandeClientRepository;
  private AppUserRepository clientRepository;
  private ProductRepository productRepository;
  private MvtStkService mvtStkService;
  private CommandeClientMapper commandeClientMapper;
  private LigneCommandeClientMapper ligneCommandeClientMapper;
  private ProductMapper productMapper;
  private AppUserMapper appUserMapper;

  @Autowired
  public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
                                   AppUserRepository clientRepository, ProductRepository productRepository, LigneCommandeClientRepository ligneCommandeClientRepository,
                                   MvtStkService mvtStkService, CommandeClientMapper commandeClientMapper, LigneCommandeClientMapper ligneCommandeClientMapper, ProductMapper productMapper, AppUserMapper appUserMapper) {
    this.commandeClientRepository = commandeClientRepository;
    this.ligneCommandeClientRepository = ligneCommandeClientRepository;
    this.clientRepository = clientRepository;
    this.productRepository = productRepository;
    this.mvtStkService = mvtStkService;
    this.commandeClientMapper = commandeClientMapper;
    this.ligneCommandeClientMapper = ligneCommandeClientMapper;
    this.productMapper = productMapper;
    this.appUserMapper = appUserMapper;
  }

  @Override
  public CommandeClientDto save(CommandeClientDto dto) throws InvalidCmdClientException, InvalidOperationException, AppUserNotFoundException {

    List<String> errors = CommandeClientValidator.validate(dto);

    if (!errors.isEmpty()) {
      log.error("Commande client n'est pas valide");
      throw new InvalidCmdClientException("La commande client n'est pas valide");
    }

    if (dto.getIdCmdClient() != null && dto.isCommandeLivree()) {
      throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livree");
    }

    Optional<AppUser> client = clientRepository.findById(dto.getClient().getIdUser());
    if (client.isEmpty()) {
      log.warn("Client with ID {} was not found in the DB", dto.getClient().getIdUser());
      throw new AppUserNotFoundException("Aucun client avec l'ID");
    }

    List<String> articleErrors = new ArrayList<>();

    if (dto.getLigneCommandeClients() != null) {
      dto.getLigneCommandeClients().forEach(ligCmdClt -> {
        if (ligCmdClt.getProduct() != null) {
          Optional<Product> article = productRepository.findById(ligCmdClt.getProduct().getIdProduct());
          if (article.isEmpty()) {
            articleErrors.add("L'article avec l'ID " + ligCmdClt.getProduct().getIdProduct() + " n'existe pas");
          }
        } else {
          articleErrors.add("Impossible d'enregister une commande avec un aticle NULL");
        }
      });
    }

    if (!articleErrors.isEmpty()) {
      log.warn("");
      throw new InvalidOperationException("Article n'existe pas dans la BDD");
    }
    dto.setDateCommande(Instant.now());
    CommandeClient savedCmdClt = commandeClientRepository.save(commandeClientMapper.toEntity(dto));

    if (dto.getLigneCommandeClients() != null) {
      dto.getLigneCommandeClients().forEach(ligCmdClt -> {
        LigneCommandeClient ligneCommandeClient = ligneCommandeClientMapper.toEntity(ligCmdClt);
        ligneCommandeClient.setCommandeClient(savedCmdClt);
        LigneCommandeClient savedLigneCmd = ligneCommandeClientRepository.save(ligneCommandeClient);

        try {
          effectuerSortie(savedLigneCmd);
        } catch (InvalidMvtStkException e) {
          throw new RuntimeException(e);
        }
      });
    }

    return commandeClientMapper.toDto(savedCmdClt);
  }

  @Override
  public CommandeClientDto findById(Long id) throws InvalidCmdClientException {
    if (id == null) {
      log.error("Commande client ID is NULL");
      return null;
    }
    return commandeClientRepository.findById(id)
        .map(commandeClientMapper::toDto)
        .orElseThrow(() -> new InvalidCmdClientException(
            "Aucune commande client n'a ete trouve avec l'ID "
        ));
  }

  @Override
  public CommandeClientDto findByCode(String code) throws InvalidCmdClientException {
    if (!StringUtils.hasLength(code)) {
      log.error("Commande client CODE is NULL");
      return null;
    }
    return commandeClientRepository.findCommandeClientByCode(code)
        .map(commandeClientMapper::toDto)
        .orElseThrow(() -> new InvalidCmdClientException(
            "Aucune commande client n'a ete trouve avec le CODE "
        ));
  }

  @Override
  public List<CommandeClientDto> findAll() {
    return commandeClientRepository.findAll().stream()
        .map(commandeClientMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Long id) throws InvalidOperationException {
    if (id == null) {
      log.error("Commande client ID is NULL");
      return;
    }
    List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRepository.findAllByCommandeClientId(id);
    if (!ligneCommandeClients.isEmpty()) {
      throw new InvalidOperationException("Impossible de supprimer une commande client deja utilisee");
    }
    commandeClientRepository.deleteById(id);
  }

  @Override
  public List<LigneCommandeClientDto> findAllLignesCommandesClientByCommandeClientId(Long idCommande) {
    return ligneCommandeClientRepository.findAllByCommandeClientId(idCommande).stream()
        .map(ligneCommandeClientMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public CommandeClientDto updateEtatCommande(Long idCommande, EtatCommande etatCommande) throws InvalidOperationException, InvalidCmdClientException {
    checkIdCommande(idCommande);
    if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
      log.error("L'etat de la commande client is NULL");
      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un etat null");
    }
    CommandeClientDto commandeClient = checkEtatCommande(idCommande);
    commandeClient.setEtatCommande(etatCommande);

    CommandeClient savedCmdClt = commandeClientRepository.save(commandeClientMapper.toEntity(commandeClient));
    if (commandeClient.isCommandeLivree()) {
      updateMvtStk(idCommande);
    }

    return commandeClientMapper.toDto(savedCmdClt);
  }

  @Override
  public CommandeClientDto updateQuantiteCommande(Long idCommande, Long idLigneCommande, BigDecimal quantite) throws InvalidCmdClientException, InvalidOperationException {
    checkIdCommande(idCommande);
    checkIdLigneCommande(idLigneCommande);

    if (quantite == null || quantite.compareTo(BigDecimal.ZERO) == 0) {
      log.error("L'ID de la ligne commande is NULL");
      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec une quantite null ou ZERO");
    }

    CommandeClientDto commandeClient = checkEtatCommande(idCommande);
    Optional<LigneCommandeClient> ligneCommandeClientOptional = findLigneCommandeClient(idLigneCommande);

    LigneCommandeClient ligneCommandeClient = ligneCommandeClientOptional.get();
    ligneCommandeClient.setQuantite(quantite);
    ligneCommandeClientRepository.save(ligneCommandeClient);

    return commandeClient;
  }

  @Override
  public CommandeClientDto updateClient(Long idCommande, Long idClient) throws InvalidCmdClientException, InvalidOperationException {
    checkIdCommande(idCommande);
    if (idClient == null) {
      log.error("L'ID du client is NULL");
      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID client null");
    }
    CommandeClientDto commandeClient = checkEtatCommande(idCommande);
    Optional<AppUser> clientOptional = clientRepository.findById(idClient);
    if (clientOptional.isEmpty()) {
      throw new InvalidOperationException(
          "Aucun client n'a ete trouve avec l'ID ");
    }
    commandeClient.setClient(appUserMapper.toDto(clientOptional.get()));

    return commandeClientMapper.toDto(
        commandeClientRepository.save(commandeClientMapper.toEntity(commandeClient))
    );
  }

  @Override
  public CommandeClientDto updateArticle(Long idCommande, Long idLigneCommande, Long idProduct) throws InvalidOperationException, InvalidCmdClientException {
    checkIdCommande(idCommande);
    checkIdLigneCommande(idLigneCommande);
    checkIdArticle(idProduct, "nouvel");

    CommandeClientDto commandeClient = checkEtatCommande(idCommande);

    Optional<LigneCommandeClient> ligneCommandeClient = findLigneCommandeClient(idLigneCommande);

    Optional<Product> productOptional = productRepository.findById(idProduct);
    if (productOptional.isEmpty()) {
      throw new InvalidOperationException(
          "Aucune article n'a ete trouve avec l'ID ");
    }

    List<String> errors = ProductValidator.validate(productMapper.toDto(productOptional.get()));
    if (!errors.isEmpty()) {
      throw new InvalidOperationException("Article invalid");
    }

    LigneCommandeClient ligneCommandeClientToSaved = ligneCommandeClient.get();
    ligneCommandeClientToSaved.setProduct(productOptional.get());
    ligneCommandeClientRepository.save(ligneCommandeClientToSaved);

    return commandeClient;
  }

  @Override
  public CommandeClientDto deleteArticle(Long idCommande, Long idLigneCommande) throws InvalidOperationException, InvalidCmdClientException {
    checkIdCommande(idCommande);
    checkIdLigneCommande(idLigneCommande);

    CommandeClientDto commandeClient = checkEtatCommande(idCommande);
    // Just to check the LigneCommandeClient and inform the client in case it is absent
    findLigneCommandeClient(idLigneCommande);
    ligneCommandeClientRepository.deleteById(idLigneCommande);

    return commandeClient;
  }

  private CommandeClientDto checkEtatCommande(Long idCommande) throws InvalidOperationException, InvalidCmdClientException {
    CommandeClientDto commandeClient = findById(idCommande);
    if (commandeClient.isCommandeLivree()) {
      throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livree");
    }
    return commandeClient;
  }

  private Optional<LigneCommandeClient> findLigneCommandeClient(Long idLigneCommande) throws InvalidCmdClientException {
    Optional<LigneCommandeClient> ligneCommandeClientOptional = ligneCommandeClientRepository.findById(idLigneCommande);
    if (ligneCommandeClientOptional.isEmpty()) {
      throw new InvalidCmdClientException(
          "Aucune ligne commande client n'a ete trouve avec l'ID ");
    }
    return ligneCommandeClientOptional;
  }

  private void checkIdCommande(Long idCommande) throws InvalidOperationException {
    if (idCommande == null) {
      log.error("Commande client ID is NULL");
      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID null");
    }
  }

  private void checkIdLigneCommande(Long idLigneCommande) throws InvalidOperationException {
    if (idLigneCommande == null) {
      log.error("L'ID de la ligne commande is NULL");
      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec une ligne de commande null");
    }
  }

  private void checkIdArticle(Long idArticle, String msg) throws InvalidOperationException {
    if (idArticle == null) {
      log.error("L'ID de " + msg + " is NULL");
      throw new InvalidOperationException("Impossible de modifier l'etat de la commande");
    }
  }

  private void updateMvtStk(Long idCommande) {
    List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRepository.findAllByCommandeClientId(idCommande);
    ligneCommandeClients.forEach(lig -> {
      try {
        effectuerSortie(lig);
      } catch (InvalidMvtStkException e) {
        throw new RuntimeException(e);
      }
    });
  }

  private void effectuerSortie(LigneCommandeClient lig) throws InvalidMvtStkException {
    MvtStkDto mvtStkDto = MvtStkDto.builder()
        .product(productMapper.toDto(lig.getProduct()))
        .dateMvt(Instant.now())
        .typeMvt(TypeMvtStk.SORTIE)
        .quantite(lig.getQuantite())
        .build();
    mvtStkService.sortieStock(mvtStkDto);
  }
}
