package com.group.makity.leMakity.services;

import com.group.makity.leMakity.dtos.MvtStkDto;
import com.group.makity.leMakity.exceptions.InvalidMvtStkException;
import com.group.makity.leMakity.exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.List;

public interface MvtStkService {

  BigDecimal stockReelProduit(Long idProduct) throws ProductNotFoundException;

  List<MvtStkDto> mvtStkProduit(Long idProduct);

  MvtStkDto entreeStock(MvtStkDto dto) throws InvalidMvtStkException;

  MvtStkDto sortieStock(MvtStkDto dto) throws InvalidMvtStkException;

  MvtStkDto correctionStockPos(MvtStkDto dto) throws InvalidMvtStkException;

  MvtStkDto correctionStockNeg(MvtStkDto dto) throws InvalidMvtStkException;


}
