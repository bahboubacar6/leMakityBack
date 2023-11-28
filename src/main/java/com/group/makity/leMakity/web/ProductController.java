package com.group.makity.leMakity.web;

import com.flickr4java.flickr.FlickrException;
import com.group.makity.leMakity.dtos.ProductDTO;
import com.group.makity.leMakity.dtos.ProductHistoryDTO;
import com.group.makity.leMakity.exceptions.AppUserNotFoundException;
import com.group.makity.leMakity.exceptions.CategoryNotFoundException;
import com.group.makity.leMakity.exceptions.InvalidOperationException;
import com.group.makity.leMakity.exceptions.ProductNotFoundException;
import com.group.makity.leMakity.services.ProductService;
import com.group.makity.leMakity.services.strategy.StrategyPhotoContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "v1/products", produces = "application/json")
@CrossOrigin(origins = "*")
public class ProductController {

    private ProductService productService;

    private StrategyPhotoContext strategyPhotoContext;

    public ProductController(ProductService productService, StrategyPhotoContext strategyPhotoContext) {
        this.productService = productService;
        this.strategyPhotoContext = strategyPhotoContext;
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
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO) throws CategoryNotFoundException, ProductNotFoundException {
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

    /*@PostMapping("/{idProd}/photos/{title}")
    public Object savePhoto( @PathVariable("idProd") Long id,@RequestPart("file") MultipartFile photo, @PathVariable(
            "title") String title) throws IOException, FlickrException, InvalidOperationException, CategoryNotFoundException, AppUserNotFoundException, ProductNotFoundException {
        return strategyPhotoContext.savePhoto("product", id, photo.getInputStream(), title);
    }*/

    /*@PostMapping(path="/{id}/photos/{title}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public Object savePhoto(@PathVariable("id") Long id, @PathVariable("title") String title,
    @RequestPart MultipartFile file) throws IOException, FlickrException, InvalidOperationException, CategoryNotFoundException, AppUserNotFoundException, ProductNotFoundException {
        return strategyPhotoContext.savePhoto("product",id, file.getInputStream(),title);
    }*/
}
