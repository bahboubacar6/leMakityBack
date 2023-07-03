package com.group.makity.leMakity.web;

import com.group.makity.leMakity.dtos.ImageModelDTO;
import com.group.makity.leMakity.dtos.ProductDTO;
import com.group.makity.leMakity.dtos.ProductHistoryDTO;
import com.group.makity.leMakity.exceptions.CategoryNotFoundException;
import com.group.makity.leMakity.exceptions.ProductNotFoundException;
import com.group.makity.leMakity.services.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("v1/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<ProductDTO> listProduct(){
        return productService.listProduct();
    }

    @GetMapping("/pageProduct")
    public ProductHistoryDTO listProductPage(@RequestParam(name = "keyword", defaultValue = "") String keyword,
                                             @RequestParam(name = "page", defaultValue = "0") int page,
                                             @RequestParam(name = "size", defaultValue = "6") int size){
        return productService.listPageProduct("%" + keyword + "%",page, size);
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable(name = "id") Long idProd) throws ProductNotFoundException {
        return productService.findById(idProd);
    }

    @PostMapping("/save")
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO) throws CategoryNotFoundException {
        return productService.saveProduct(productDTO);
    }

    @PostMapping(value = {"/add"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ProductDTO saveProductDB(@RequestPart( "imageFile") MultipartFile[] file, @RequestPart("productDTO") ProductDTO productDTO) throws IOException {

        try {
            Set<ImageModelDTO> images = uploadImage(file);
            productDTO.setProductImages(images);
            return  productService.saveProductDB(productDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Set<ImageModelDTO> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModelDTO> imageModels = new HashSet<>();
        for (MultipartFile file: multipartFiles){
            ImageModelDTO imageModelDTO = new ImageModelDTO(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModelDTO);
        }
        return imageModels;
    }

    @PutMapping("update/{idProd}")
    public ProductDTO updateProduct(@PathVariable Long idProd, @RequestBody ProductDTO productDTO) throws ProductNotFoundException, CategoryNotFoundException {
        productDTO.setIdProduct(idProd);
        return productService.updateProduct(productDTO);
    }

    @DeleteMapping("/{idProd}")
    public void deleteProduct(@PathVariable Long idProd){
         productService.deleteProduct(idProd);
    }
}
