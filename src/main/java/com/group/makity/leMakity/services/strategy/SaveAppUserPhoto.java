package com.group.makity.leMakity.services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.group.makity.leMakity.dtos.AppUserDTO;
import com.group.makity.leMakity.exceptions.AppUserNotFoundException;
import com.group.makity.leMakity.exceptions.InvalidOperationException;
import com.group.makity.leMakity.security.service.AccountService;
import com.group.makity.leMakity.services.AppUserService;
import com.group.makity.leMakity.services.FlickrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("appUserStrategy")
@Slf4j
public class SaveAppUserPhoto implements Strategy<AppUserDTO> {

    private FlickrService flickrService;

    private AccountService accountService;

    @Autowired
    public SaveAppUserPhoto(FlickrService flickrService, AppUserService appUserService, AccountService accountService) {
        this.flickrService = flickrService;
        this.accountService = accountService;
    }

    /*@Override
    public String urlPhoto(InputStream photo) throws FlickrException, InvalidOperationException {
        return null;
    }*/

    @Override
    public AppUserDTO savePhoto(Long id, InputStream photo, String titre) throws AppUserNotFoundException, FlickrException, InvalidOperationException {
        AppUserDTO utilisateur = accountService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("Erreur lors de l'enregistrement de l'image de l'utilisateur");
        }
        utilisateur.setImage(urlPhoto);
        return accountService.saveUser(utilisateur);
    }
}
