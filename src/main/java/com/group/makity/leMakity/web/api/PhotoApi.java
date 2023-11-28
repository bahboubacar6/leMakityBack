package com.group.makity.leMakity.web.api;

import com.flickr4java.flickr.FlickrException;
import com.group.makity.leMakity.exceptions.AppUserNotFoundException;
import com.group.makity.leMakity.exceptions.CategoryNotFoundException;
import com.group.makity.leMakity.exceptions.InvalidOperationException;
import com.group.makity.leMakity.exceptions.ProductNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.group.makity.leMakity.utils.Constants.APP_ROOT;

@Api("photos")
@Tag(name = APP_ROOT)
public interface PhotoApi {

  @PostMapping(path="v1/save/{id}/{title}/{context}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
  Object savePhoto(@PathVariable("context") String context, @PathVariable("id") Long id,@RequestPart("file") MultipartFile photo, @PathVariable(
      "title") String title) throws IOException,
          FlickrException, InvalidOperationException, CategoryNotFoundException, AppUserNotFoundException, ProductNotFoundException;

}
