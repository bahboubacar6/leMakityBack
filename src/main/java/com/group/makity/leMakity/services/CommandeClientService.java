package com.group.makity.leMakity.services;

import com.group.makity.leMakity.dtos.CommandeClientDto;
import com.group.makity.leMakity.dtos.LigneCommandeClientDto;
import com.group.makity.leMakity.entities.EtatCommande;
import com.group.makity.leMakity.exceptions.AppUserNotFoundException;
import com.group.makity.leMakity.exceptions.InvalidCmdClientException;
import com.group.makity.leMakity.exceptions.InvalidOperationException;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientService {

  CommandeClientDto save(CommandeClientDto dto) throws InvalidCmdClientException, InvalidOperationException, AppUserNotFoundException;

  CommandeClientDto updateEtatCommande(Long idCommande, EtatCommande etatCommande) throws InvalidOperationException, InvalidCmdClientException;

  CommandeClientDto updateQuantiteCommande(Long idCommande, Long idLigneCommande, BigDecimal quantite) throws InvalidCmdClientException, InvalidOperationException;

  CommandeClientDto updateClient(Long idCommande, Long idClient) throws InvalidCmdClientException, InvalidOperationException;

  CommandeClientDto updateArticle(Long idCommande, Long idLigneCommande, Long newIdArticle) throws InvalidOperationException, InvalidCmdClientException;

  // Delete article ==> delete LigneCommandeClient
  CommandeClientDto deleteArticle(Long idCommande, Long idLigneCommande) throws InvalidOperationException, InvalidCmdClientException;

  CommandeClientDto findById(Long id) throws InvalidCmdClientException;

  CommandeClientDto findByCode(String code) throws InvalidCmdClientException;

  List<CommandeClientDto> findAll();

  List<LigneCommandeClientDto> findAllLignesCommandesClientByCommandeClientId(Long idCommande);

  void delete(Long id) throws InvalidOperationException;

}
