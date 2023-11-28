package com.group.makity.leMakity.services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.group.makity.leMakity.dtos.ProductDTO;
import com.group.makity.leMakity.exceptions.CategoryNotFoundException;
import com.group.makity.leMakity.exceptions.InvalidOperationException;
import com.group.makity.leMakity.exceptions.ProductNotFoundException;
import com.group.makity.leMakity.services.FlickrService;
import com.group.makity.leMakity.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;

@Service("productStrategy")
@Slf4j
public class SaveProductPhoto implements Strategy<ProductDTO> {

    private FlickrService flickrService;

    private ProductService productService;

    @Autowired
    public SaveProductPhoto(FlickrService flickrService, ProductService productService) {
        this.flickrService = flickrService;
        this.productService = productService;
    }

    @Override
    public ProductDTO savePhoto(Long id, InputStream photo, String titre) throws ProductNotFoundException, FlickrException, InvalidOperationException, CategoryNotFoundException, IOException {

        ProductDTO product = productService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("Erreur lors de l'enregistrement de l'image du produit");
        }
        product.setImage(urlPhoto);
        return productService.saveProduct(product);
    }
}
