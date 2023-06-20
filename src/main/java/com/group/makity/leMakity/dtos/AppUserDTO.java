package com.group.makity.leMakity.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AppUserDTO {


    private Long idUser;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private String resetPasswordToken;
    private String telephone;
    private String address;
    private List<AppRoleDTO> roles;

}
