package com.group.makity.leMakity.web.api;

import com.group.makity.leMakity.dtos.MvtStkDto;
import java.math.BigDecimal;
import java.util.List;

import com.group.makity.leMakity.exceptions.InvalidMvtStkException;
import com.group.makity.leMakity.exceptions.ProductNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static com.group.makity.leMakity.utils.Constants.APP_ROOT;

@Tag(name = APP_ROOT)
public interface MvtStkApi {

  @GetMapping(APP_ROOT + "/mvtstk/stockreel/{idArticle}")
  BigDecimal stockReelArticle(@PathVariable("idArticle") Long idArticle) throws ProductNotFoundException;

  @GetMapping(APP_ROOT + "/mvtstk/filter/article/{idArticle}")
  List<MvtStkDto> mvtStkArticle(@PathVariable("idArticle") Long idArticle);

  @PostMapping(APP_ROOT + "/mvtstk/entree")
  MvtStkDto entreeStock(@RequestBody MvtStkDto dto) throws InvalidMvtStkException;

  @PostMapping(APP_ROOT + "/mvtstk/sortie")
  MvtStkDto sortieStock(@RequestBody MvtStkDto dto) throws InvalidMvtStkException;

  @PostMapping(APP_ROOT + "/mvtstk/correctionpos")
  MvtStkDto correctionStockPos(@RequestBody MvtStkDto dto) throws InvalidMvtStkException;

  @PostMapping(APP_ROOT + "/mvtstk/correctionneg")
  MvtStkDto correctionStockNeg(@RequestBody MvtStkDto dto) throws InvalidMvtStkException;

}
