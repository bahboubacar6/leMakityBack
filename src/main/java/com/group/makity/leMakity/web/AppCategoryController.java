package com.group.makity.leMakity.web;

import com.group.makity.leMakity.dtos.AppCategoryDTO;
import com.group.makity.leMakity.exceptions.CategoryNotFoundException;
import com.group.makity.leMakity.services.AppCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/category")
@CrossOrigin(origins = "*")
public class AppCategoryController {

    private AppCategoryService appCategoryService;

    public AppCategoryController(AppCategoryService appCategoryService) {
        this.appCategoryService = appCategoryService;
    }

    @GetMapping("/search")
    public List<AppCategoryDTO> searchCategory(@RequestParam(name = "keyword", defaultValue = "") String keyword){
        return appCategoryService.searchCategory("%" + keyword + "%");
    }

    @GetMapping("/{id}")
    public AppCategoryDTO getCategory(@PathVariable(name = "id") Long idCat) throws CategoryNotFoundException {
         return appCategoryService.findById(idCat);
    }

    @PostMapping("/save")
    public AppCategoryDTO saveCategory(@RequestBody AppCategoryDTO appCategoryDTO){
        return appCategoryService.saveCat(appCategoryDTO);
    }

    @PutMapping("/update/{idCat}")
    public AppCategoryDTO updateCategory(@PathVariable Long idCat, @RequestBody AppCategoryDTO appCategoryDTO) throws CategoryNotFoundException {
        appCategoryDTO.setIdCategory(idCat);
        return appCategoryService.updateCat(appCategoryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        appCategoryService.deleteAppCat(id);
    }
}
