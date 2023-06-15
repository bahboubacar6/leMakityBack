package com.group.makity.leMakity.services;

import com.group.makity.leMakity.dtos.ProductDTO;
import com.group.makity.leMakity.dtos.ProductHistoryDTO;
import com.group.makity.leMakity.exceptions.CategoryNotFoundException;
import com.group.makity.leMakity.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {

    ProductDTO findById(Long IdProduct) throws ProductNotFoundException;
    ProductDTO findByProductName(String productName) throws ProductNotFoundException;
    ProductDTO saveProduct(ProductDTO productDTO) throws CategoryNotFoundException;
    ProductDTO updateProduct(ProductDTO productDTO) throws ProductNotFoundException, CategoryNotFoundException;
    boolean deleteProduct(Long idProduct);
    List<ProductDTO> listProduct();
    ProductHistoryDTO listPageProduct(String keyword, int page, int size);
//    List<ProductDTO> searchProduct(String keyword);
}
