package com.group.makity.leMakity.web;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/photos")
public class ImageController {

    @PostMapping(name = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        // Traitez le fichier ici (par exemple, sauvegardez-le sur le disque)
        return ResponseEntity.ok("Image uploaded successfully");
    }
}

