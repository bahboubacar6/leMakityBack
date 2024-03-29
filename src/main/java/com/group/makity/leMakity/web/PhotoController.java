package com.group.makity.leMakity.web;

import com.flickr4java.flickr.FlickrException;
import com.group.makity.leMakity.exceptions.AppUserNotFoundException;
import com.group.makity.leMakity.exceptions.CategoryNotFoundException;
import com.group.makity.leMakity.exceptions.InvalidOperationException;
import com.group.makity.leMakity.exceptions.ProductNotFoundException;
import com.group.makity.leMakity.services.strategy.StrategyPhotoContext;
import com.group.makity.leMakity.web.api.PhotoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.group.makity.leMakity.utils.Constants.APP_ROOT;


@RestController
@CrossOrigin(origins = "*")
public class PhotoController implements PhotoApi {

    private StrategyPhotoContext strategyPhotoContext;

    @Autowired
    public PhotoController(StrategyPhotoContext strategyPhotoContext) {
        this.strategyPhotoContext = strategyPhotoContext;
    }

    @Override
    public Object savePhoto(String context, Long id, MultipartFile photo, String title) throws IOException, FlickrException, InvalidOperationException, CategoryNotFoundException, AppUserNotFoundException, ProductNotFoundException {
        return strategyPhotoContext.savePhoto(context, id, photo.getInputStream(), title);
    }
}
