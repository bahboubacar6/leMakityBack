package com.group.makity.leMakity.mappers;

import com.group.makity.leMakity.dtos.ProductDTO;
import com.group.makity.leMakity.entities.AppCategory;
import com.group.makity.leMakity.entities.Product;
import com.group.makity.leMakity.exceptions.CategoryNotFoundException;
import com.group.makity.leMakity.repositories.AppCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductMapImpl {
    private static final String CATEGORY_NOT_FOUND = "La categorie n'existe pas";
    private AppCategoryRepository appCategoryRepository;

    public ProductMapImpl(AppCategoryRepository appCategoryRepository) {
        this.appCategoryRepository = appCategoryRepository;
    }

    public Product toEntity(ProductDTO productDTO) throws CategoryNotFoundException {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        if(productDTO.getIdProduct() == null) {
            /*AppCategory appCategory = appCategoryRepository.findById(productDTO.getIdCategory()).orElseThrow(() -> new CategoryNotFoundException(CATEGORY_NOT_FOUND));
*/
            product.setIdProduct( productDTO.getIdProduct() );
            product.setProductName( productDTO.getProductName() );
            product.setPrice( productDTO.getPrice() );
            product.setDescription( productDTO.getDescription() );

            product.setStockQuantity(productDTO.getStockQuantity());
            /*product.setCategory(appCategory);*/

        }
        product.setImage( productDTO.getImage() );

        return product;
    }

    public ProductDTO toDto(Product Product) {
        if ( Product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();


        productDTO.setIdProduct( Product.getIdProduct() );
        productDTO.setProductName( Product.getProductName() );
        productDTO.setPrice( Product.getPrice() );
        productDTO.setDescription( Product.getDescription() );
        productDTO.setImage( Product.getImage() );
        productDTO.setStockQuantity( Product.getStockQuantity() );
       /* productDTO.setIdCategory(Product.getCategory().getIdCategory());*/

        return productDTO;
    }
}
