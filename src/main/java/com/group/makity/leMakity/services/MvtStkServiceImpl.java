package com.group.makity.leMakity.services;

import com.group.makity.leMakity.dtos.MvtStkDto;
import com.group.makity.leMakity.entities.MvtStk;
import com.group.makity.leMakity.entities.TypeMvtStk;
import com.group.makity.leMakity.exceptions.InvalidMvtStkException;
import com.group.makity.leMakity.exceptions.ProductNotFoundException;
import com.group.makity.leMakity.mappers.MvtStkMapper;
import com.group.makity.leMakity.repositories.MvtStkRepository;
import com.group.makity.leMakity.validator.MvtStkValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MvtStkServiceImpl implements MvtStkService {

  private MvtStkRepository repository;
  private ProductService productService;

  private MvtStkMapper mvtStkMapper;

  @Autowired
  public MvtStkServiceImpl(MvtStkRepository repository, ProductService productService, MvtStkMapper mvtStkMapper) {
    this.repository = repository;
    this.productService = productService;
    this.mvtStkMapper = mvtStkMapper;
  }

  @Override
  public BigDecimal stockReelProduit(Long idProduct) throws ProductNotFoundException {
    if (idProduct == null) {
      log.warn("ID article is NULL");
      return BigDecimal.valueOf(-1);
    }
    productService.findById(idProduct);
    return repository.stockReelProduit(idProduct);
  }

  @Override
  public List<MvtStkDto> mvtStkProduit(Long idProduct) {
    List<MvtStk> mvtStks = repository.findAllByProductIdProduct(idProduct);
    List<MvtStkDto> mvtStkDtos = mvtStks.stream().map(mvtStk -> mvtStkMapper.toDto(mvtStk)).collect(Collectors.toList());
    return mvtStkDtos;
  }

  @Override
  public MvtStkDto entreeStock(MvtStkDto dto) throws InvalidMvtStkException {
    return entreePositive(dto, TypeMvtStk.ENTREE);
  }

  @Override
  public MvtStkDto sortieStock(MvtStkDto dto) throws InvalidMvtStkException {
    return sortieNegative(dto, TypeMvtStk.SORTIE);
  }

  @Override
  public MvtStkDto correctionStockPos(MvtStkDto dto) throws InvalidMvtStkException {
    return entreePositive(dto, TypeMvtStk.CORRECTION_POS);
  }

  @Override
  public MvtStkDto correctionStockNeg(MvtStkDto dto) throws InvalidMvtStkException {
    return sortieNegative(dto, TypeMvtStk.CORRECTION_NEG);
  }

  private MvtStkDto entreePositive(MvtStkDto dto, TypeMvtStk typeMvtStk) throws InvalidMvtStkException {
    List<String> errors = MvtStkValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Article is not valid {}", dto);
      throw new InvalidMvtStkException("Le mouvement du stock n'est pas valide");
    }
    dto.setQuantite(
        BigDecimal.valueOf(
            Math.abs(dto.getQuantite().doubleValue())
        )
    );
    dto.setTypeMvt(typeMvtStk);

    MvtStk mvtStk = mvtStkMapper.toEntity(dto);
    MvtStk mvtStkSaved = repository.save(mvtStk);
    MvtStkDto mvtStkDtoSaved = mvtStkMapper.toDto(mvtStkSaved);
    return mvtStkDtoSaved;
  }

  private MvtStkDto sortieNegative(MvtStkDto dto, TypeMvtStk typeMvtStk) throws InvalidMvtStkException {
    List<String> errors = MvtStkValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Article is not valid {}", dto);
      throw new InvalidMvtStkException("Le mouvement du stock n'est pas valide");
    }
    dto.setQuantite(
        BigDecimal.valueOf(
            Math.abs(dto.getQuantite().doubleValue()) * -1
        )
    );
    dto.setTypeMvt(typeMvtStk);

    MvtStk mvtStk = mvtStkMapper.toEntity(dto);
    MvtStk mvtStkSaved = repository.save(mvtStk);
    MvtStkDto mvtStkDtoSaved = mvtStkMapper.toDto(mvtStkSaved);
    return mvtStkDtoSaved;
  }
}
