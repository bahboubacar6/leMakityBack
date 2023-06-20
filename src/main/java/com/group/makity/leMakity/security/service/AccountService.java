package com.group.makity.leMakity.security.service;

import com.group.makity.leMakity.dtos.AppUserDTO;
import com.group.makity.leMakity.entities.AppRole;
import com.group.makity.leMakity.exceptions.AppUserNotFoundException;

import java.util.List;

public interface AccountService {
    AppUserDTO saveUser(AppUserDTO appUserDTO);
    AppUserDTO updateUser(AppUserDTO appUserDTO) throws AppUserNotFoundException;
    AppRole addNewRole(AppRole role);
    void addRoleToUser(String email, String roleName) throws AppUserNotFoundException;
    AppUserDTO loadUserByEmail(String email) throws AppUserNotFoundException;
    List<AppUserDTO> listUsers();
    AppUserDTO findByEmail(String email) throws AppUserNotFoundException;
}
