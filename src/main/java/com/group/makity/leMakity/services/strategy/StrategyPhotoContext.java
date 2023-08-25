package com.group.makity.leMakity.services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.group.makity.leMakity.exceptions.AppUserNotFoundException;
import com.group.makity.leMakity.exceptions.CategoryNotFoundException;
import com.group.makity.leMakity.exceptions.InvalidOperationException;
import com.group.makity.leMakity.exceptions.ProductNotFoundException;
import lombok.Setter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class StrategyPhotoContext {

    private BeanFactory beanFactory;
    private Strategy strategy;
    @Setter
    private String context;

    @Autowired
    public StrategyPhotoContext(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object savePhoto(String context, Long id, InputStream photo, String titre) throws InvalidOperationException, FlickrException, CategoryNotFoundException, IOException, AppUserNotFoundException, ProductNotFoundException {
        determinContext(context);
        return strategy.savePhoto(id, photo, titre);
    }

    public void determinContext(String context) throws InvalidOperationException {
        final  String beanName = context + "Strategy";
        switch (context) {
            case "product":
                strategy = beanFactory.getBean(beanName, SaveProductPhoto.class);
                break;
            case "appUser":
                strategy = beanFactory.getBean(beanName, SaveAppUserPhoto.class);
                break;
            default: throw new InvalidOperationException("Contexte inconnu pour l'enregistrement de la photo");
        }
    }
}
