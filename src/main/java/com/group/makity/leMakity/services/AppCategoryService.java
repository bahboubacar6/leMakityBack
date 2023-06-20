package com.group.makity.leMakity.services;

import com.group.makity.leMakity.dtos.AppCategoryDTO;
import com.group.makity.leMakity.exceptions.CategoryNotFoundException;

import java.util.List;

public interface AppCategoryService {

    AppCategoryDTO findById(Long idCat) throws CategoryNotFoundException;

    AppCategoryDTO saveCat(AppCategoryDTO appCategoryDTO);

    boolean deleteAppCat(Long idCat);

    AppCategoryDTO updateCat(AppCategoryDTO appCategoryDTO) throws CategoryNotFoundException;

    List<AppCategoryDTO> searchCategory(String keyword);
}
