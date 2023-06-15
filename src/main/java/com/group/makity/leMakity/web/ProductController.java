package com.group.makity.leMakity.web;

import com.group.makity.leMakity.dtos.ProductDTO;
import com.group.makity.leMakity.dtos.ProductHistoryDTO;
import com.group.makity.leMakity.exceptions.CategoryNotFoundException;
import com.group.makity.leMakity.exceptions.ProductNotFoundException;
import com.group.makity.leMakity.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

   /* @GetMapping("/search")
    public List<ProductDTO> searchProduct(@RequestParam(name = "keyword", defaultValue = "") String keyword){
        return productService.searchProduct("%" + keyword + "%");
    }*/

    /*@GetMapping("/pageProduct")
    public ProductHistoryDTO listProductPage(@RequestParam(name = "keyword", defaultValue = "") String keyword,
                                             @RequestParam(name = "page", defaultValue = "0") int page,
                                             @RequestParam(name = "size", defaultValue = "6") int size){
        return productService.listPageProduct("%" + keyword + "%",page, size);
    }*/

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
