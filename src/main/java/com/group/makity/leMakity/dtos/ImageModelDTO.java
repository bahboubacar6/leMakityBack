package com.group.makity.leMakity.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data @NoArgsConstructor
public class ImageModelDTO {

    private Long id;
    private String name;
    private String type;
    private byte[] picByte;

    public ImageModelDTO(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }
}
