package com.group.makity.leMakity.security.service;


import com.group.makity.leMakity.dtos.AppUserDTO;
import com.group.makity.leMakity.entities.AppRole;
import com.group.makity.leMakity.entities.AppUser;
import com.group.makity.leMakity.exceptions.AppUserNotFoundException;
import com.group.makity.leMakity.mappers.AppUserMapper;
import com.group.makity.leMakity.repositories.AppRoleRepository;
import com.group.makity.leMakity.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private AppUserMapper appUserMapper;
    private PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, AppUserMapper appUserMapper, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.appUserMapper = appUserMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUserDTO saveUser(AppUserDTO appUserDTO) {
        appUserRepository.findByEmail(appUserDTO.getEmail()).ifPresent(
                (present)->{
                    throw new RuntimeException("L'utilisateur exist déjà");
                }
        );
        AppUser appUser = appUserMapper.toEntity(appUserDTO);
        appUser.setPassword(passwordEncoder.encode(appUserDTO.getPassword()));
        AppRole role = appRoleRepository.findByRoleName("USER");
        appUser.setRoles(List.of(role));
        AppUser appUserSaved = appUserRepository.save(appUser);
        return appUserMapper.toDto(appUserSaved);
    }

    @Override
    public AppUserDTO updateUser(AppUserDTO appUserDTO) throws AppUserNotFoundException {
        AppUser appUser = appUserRepository.findById(appUserDTO.getIdUser()).get();

        if(appUser == null){
            throw new AppUserNotFoundException("L'utilisateur n'est pas connecté");
        }
        if(!Objects.equals(appUser.getEmail(),appUserDTO.getEmail())){
            appUser.setEmail(appUserDTO.getEmail());
        }
        if(!Objects.equals(appUser.getPassword(),appUserDTO.getPassword())){
            appUser.setPassword(appUserDTO.getPassword());
        }
        AppUser userSaved = appUserRepository.save(appUser);
        AppUserDTO userDTOSaved = appUserMapper.toDto(userSaved);
        return userDTOSaved;
    }

    @Override
    public AppRole addNewRole(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) throws AppUserNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email).orElseThrow(()-> new AppUserNotFoundException("User not found"));
        AppRole role = appRoleRepository.findByRoleName(roleName);
        appUser.getRoles().add(role);
    }

    @Override
    public AppUserDTO loadUserByEmail(String email) throws AppUserNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email).orElseThrow(()-> new AppUserNotFoundException("User not found"));
        AppUserDTO appUserDTO = appUserMapper.toDto(appUser);
        return appUserDTO;
    }

    @Override
    public List<AppUserDTO> listUsers() {
        List<AppUser> users = appUserRepository.findAll();
        List<AppUserDTO> appUserDTOS = users.stream().map(u -> appUserMapper.toDto(u)).collect(Collectors.toList());
        return appUserDTOS;
    }

    @Override
    public AppUserDTO findByEmail(String email) throws AppUserNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email).orElseThrow(()-> new AppUserNotFoundException("User not found"));
        return appUserMapper.toDto(appUser);
    }

}
