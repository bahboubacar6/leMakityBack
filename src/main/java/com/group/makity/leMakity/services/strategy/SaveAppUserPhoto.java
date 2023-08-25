package com.group.makity.leMakity.services.strategy;

import com.group.makity.leMakity.entities.Product;

import java.io.InputStream;

public class SaveProductPhoto implements Strategy<Product> {

    @Override
    public Product savePhoto(InputStream photo, String titre) {
        return null;
    }
}
