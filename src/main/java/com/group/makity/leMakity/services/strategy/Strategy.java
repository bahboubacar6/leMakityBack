package com.group.makity.leMakity.services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.group.makity.leMakity.exceptions.AppUserNotFoundException;
import com.group.makity.leMakity.exceptions.CategoryNotFoundException;
import com.group.makity.leMakity.exceptions.InvalidOperationException;
import com.group.makity.leMakity.exceptions.ProductNotFoundException;

import java.io.IOException;
import java.io.InputStream;

public interface Strategy<T> {

    T savePhoto(Long id, InputStream photo, String titre) throws ProductNotFoundException, FlickrException, InvalidOperationException, CategoryNotFoundException, IOException, AppUserNotFoundException;
}
