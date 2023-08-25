package com.group.makity.leMakity.web;

import com.group.makity.leMakity.dtos.CommandeClientDto;
import com.group.makity.leMakity.dtos.LigneCommandeClientDto;
import com.group.makity.leMakity.entities.EtatCommande;
import com.group.makity.leMakity.exceptions.AppUserNotFoundException;
import com.group.makity.leMakity.exceptions.InvalidCmdClientException;
import com.group.makity.leMakity.exceptions.InvalidOperationException;
import com.group.makity.leMakity.services.CommandeClientService;
import com.group.makity.leMakity.web.api.CommandeClientApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeClientController implements CommandeClientApi {

  private CommandeClientService commandeClientService;

  @Autowired
  public CommandeClientController(CommandeClientService commandeClientService) {
    this.commandeClientService = commandeClientService;
  }

  @Override
  public ResponseEntity<CommandeClientDto> save(CommandeClientDto dto) throws InvalidOperationException, AppUserNotFoundException, InvalidCmdClientException {
    return ResponseEntity.ok(commandeClientService.save(dto));
  }

  @Override
  public ResponseEntity<CommandeClientDto> updateEtatCommande(Long idCommande, EtatCommande etatCommande) throws InvalidOperationException, InvalidCmdClientException {
    return ResponseEntity.ok(commandeClientService.updateEtatCommande(idCommande, etatCommande));
  }

  @Override
  public ResponseEntity<CommandeClientDto> updateQuantiteCommande(Long idCommande, Long idLigneCommande, BigDecimal quantite) throws InvalidOperationException, InvalidCmdClientException {
    return ResponseEntity.ok(commandeClientService.updateQuantiteCommande(idCommande, idLigneCommande, quantite));
  }

  @Override
  public ResponseEntity<CommandeClientDto> updateClient(Long idCommande, Long idClient) throws InvalidOperationException, InvalidCmdClientException {
    return ResponseEntity.ok(commandeClientService.updateClient(idCommande, idClient));
  }

  @Override
  public ResponseEntity<CommandeClientDto> updateArticle(Long idCommande, Long idLigneCommande, Long idArticle) throws InvalidOperationException, InvalidCmdClientException {
    return ResponseEntity.ok(commandeClientService.updateArticle(idCommande, idLigneCommande, idArticle));
  }

  @Override
  public ResponseEntity<CommandeClientDto> deleteArticle(Long idCommande, Long idLigneCommande) throws InvalidOperationException, InvalidCmdClientException {
    return ResponseEntity.ok(commandeClientService.deleteArticle(idCommande, idLigneCommande));
  }

  @Override
  public ResponseEntity<CommandeClientDto> findById(Long id) throws InvalidCmdClientException {
    return ResponseEntity.ok(commandeClientService.findById(id));
  }

  @Override
  public ResponseEntity<CommandeClientDto> findByCode(String code) throws InvalidCmdClientException {
    return ResponseEntity.ok(commandeClientService.findByCode(code));
  }

  @Override
  public ResponseEntity<List<CommandeClientDto>> findAll() {
    return ResponseEntity.ok(commandeClientService.findAll());
  }

  @Override
  public ResponseEntity<List<LigneCommandeClientDto>> findAllLignesCommandesClientByCommandeClientId(Long idCommande) {
    return ResponseEntity.ok(commandeClientService.findAllLignesCommandesClientByCommandeClientId(idCommande));
  }

  @Override
  public ResponseEntity<Void> delete(Long id) throws InvalidOperationException {
    commandeClientService.delete(id);
    return ResponseEntity.ok().build();
  }
}
