package com.group.makity.leMakity.services;

import com.group.makity.leMakity.dtos.ProductDTO;
import com.group.makity.leMakity.dtos.ProductHistoryDTO;
import com.group.makity.leMakity.entities.Product;
import com.group.makity.leMakity.exceptions.CategoryNotFoundException;
import com.group.makity.leMakity.exceptions.ProductNotFoundException;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    ProductDTO findById(Long IdProduct) throws ProductNotFoundException;
    ProductDTO findByProductName(String productName) throws ProductNotFoundException;
    ProductDTO saveProduct(ProductDTO productDTO) throws CategoryNotFoundException, ProductNotFoundException;

    ProductDTO saveProductDB(ProductDTO productDTO) throws IOException, CategoryNotFoundException, ProductNotFoundException;

    ProductDTO saveProductD(Product product) throws IOException, CategoryNotFoundException, ProductNotFoundException;

    ProductDTO updateProduct(ProductDTO productDTO) throws ProductNotFoundException, CategoryNotFoundException;
    boolean deleteProduct(Long idProduct);
    List<ProductDTO> listProduct();
    ProductHistoryDTO listPageProduct(String keyword, int page, int size);

}
