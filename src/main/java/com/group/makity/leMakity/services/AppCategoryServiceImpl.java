package com.group.makity.leMakity.services;

import com.group.makity.leMakity.dtos.AppCategoryDTO;
import com.group.makity.leMakity.entities.AppCategory;
import com.group.makity.leMakity.exceptions.CategoryNotFoundException;
import com.group.makity.leMakity.mappers.AppCategoryMapper;
import com.group.makity.leMakity.repositories.AppCategoryRepository;
import com.group.makity.leMakity.repositories.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AppCategoryServiceImpl implements AppCategoryService {

    private static final String CATEGORY_NOT_FOUND = "La categorie n'existe pas";
    private AppCategoryRepository appCategoryRepository;
    private AppCategoryMapper appCategoryMapper;
    private final AppUserRepository appUserRepository;

    public AppCategoryServiceImpl(AppCategoryRepository appCategoryRepository, AppCategoryMapper appCategoryMapper,
                                  AppUserRepository appUserRepository) {
        this.appCategoryRepository = appCategoryRepository;
        this.appCategoryMapper = appCategoryMapper;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppCategoryDTO findById(Long idCat) throws CategoryNotFoundException {
        AppCategory appCategory = appCategoryRepository.findById(idCat).orElseThrow(() -> new CategoryNotFoundException(CATEGORY_NOT_FOUND));
        AppCategoryDTO appCategoryDTO = appCategoryMapper.toDto(appCategory);
        return appCategoryDTO;
    }

    @Override
    public AppCategoryDTO saveCat(AppCategoryDTO appCategoryDTO) {
        appCategoryRepository.findById(appCategoryDTO.getIdCategory()).ifPresent(
                (present) -> {
                    throw new RuntimeException("La categorie existe déjà");
                }
        );
        AppCategory appCategory = appCategoryMapper.toEntity(appCategoryDTO);
        AppCategory appCategorySaved = appCategoryRepository.save(appCategory);
        AppCategoryDTO appCategoryDTOSaved = appCategoryMapper.toDto(appCategorySaved);
        return appCategoryDTOSaved;
    }

    @Override
    public boolean deleteAppCat(Long idCat) {
        appCategoryRepository.deleteById(idCat);
        return true;
    }

    @Override
    public AppCategoryDTO updateCat(AppCategoryDTO appCategoryDTO) throws CategoryNotFoundException {
        AppCategory appCategory = appCategoryRepository.findById(appCategoryDTO.getIdCategory()).get();
        if(appCategory == null){
            throw new CategoryNotFoundException(CATEGORY_NOT_FOUND);
        }
        if(!Objects.equals(appCategory.getCategoryName(),appCategoryDTO.getCategoryName())){
            appCategory.setCategoryName(appCategoryDTO.getCategoryName());
        }
        AppCategory categorySaved = appCategoryRepository.save(appCategory);
        AppCategoryDTO categoryDTOSaved = appCategoryMapper.toDto(categorySaved);
        return categoryDTOSaved;
    }

    @Override
    public List<AppCategoryDTO> listCat() {
        List<AppCategory> categoryList = appCategoryRepository.findAll();
        List<AppCategoryDTO> categoryDTOList = categoryList.stream().map(cat -> appCategoryMapper.toDto(cat)).collect(Collectors.toList());
        return categoryDTOList;
    }
}
