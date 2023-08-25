package com.group.makity.leMakity.web;

import com.group.makity.leMakity.dtos.MvtStkDto;
import com.group.makity.leMakity.exceptions.InvalidMvtStkException;
import com.group.makity.leMakity.exceptions.ProductNotFoundException;
import com.group.makity.leMakity.services.MvtStkService;
import com.group.makity.leMakity.web.api.MvtStkApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class MvtStkController implements MvtStkApi {

  private MvtStkService service;

  @Autowired
  public MvtStkController(MvtStkService service) {
    this.service = service;
  }

  @Override
  public BigDecimal stockReelArticle(Long idArticle) throws ProductNotFoundException {
    return service.stockReelProduit(idArticle);
  }

  @Override
  public List<MvtStkDto> mvtStkArticle(Long idArticle) {
    return service.mvtStkProduit(idArticle);
  }

  @Override
  public MvtStkDto entreeStock(MvtStkDto dto) throws InvalidMvtStkException {
    return service.entreeStock(dto);
  }

  @Override
  public MvtStkDto sortieStock(MvtStkDto dto) throws InvalidMvtStkException {
    return service.sortieStock(dto);
  }

  @Override
  public MvtStkDto correctionStockPos(MvtStkDto dto) throws InvalidMvtStkException {
    return service.correctionStockPos(dto);
  }

  @Override
  public MvtStkDto correctionStockNeg(MvtStkDto dto) throws InvalidMvtStkException {
    return service.correctionStockNeg(dto);
  }
}
