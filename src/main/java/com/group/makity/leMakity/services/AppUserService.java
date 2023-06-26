package com.group.makity.leMakity.services;

import com.group.makity.leMakity.dtos.AppUserDTO;
import com.group.makity.leMakity.dtos.UserHistoryDTO;
import com.group.makity.leMakity.entities.AppRole;
import com.group.makity.leMakity.exceptions.AppUserNotFoundException;

import java.util.List;

public interface AppUserService {

    AppUserDTO findById(Long idUser) throws AppUserNotFoundException;

    boolean deleteUserById(Long idUser);

    AppUserDTO findByEmail(String email) throws AppUserNotFoundException;

    List<AppUserDTO> listUsers();

    AppRole addNewRole(AppRole role);

    void addRoleToUser(String email, String roleName);

    UserHistoryDTO listPageUser(String keyword, int page, int size);
}
