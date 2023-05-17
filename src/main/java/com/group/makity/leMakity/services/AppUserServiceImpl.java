package com.group.makity.leMakity.services;

import com.group.makity.leMakity.dtos.AppUserDTO;
import com.group.makity.leMakity.entities.AppRole;
import com.group.makity.leMakity.entities.AppUser;
import com.group.makity.leMakity.exceptions.AppUserNotFoundException;
import com.group.makity.leMakity.mappers.AppUserMapper;
import com.group.makity.leMakity.repositories.AppRoleRepository;
import com.group.makity.leMakity.repositories.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImpl implements AppUserService{

    private static final String USER_NOT_FOUND = "L'utilisateur n'existe pas";
    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;
    private final AppRoleRepository appRoleRepository;


    public AppUserServiceImpl(AppUserRepository appUserRepository, AppUserMapper appUserMapper,
                              AppRoleRepository appRoleRepository) {
        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
        this.appRoleRepository = appRoleRepository;
    }

    @Override
    public AppUserDTO findById(Long idUser) throws AppUserNotFoundException {
        AppUser appUser = appUserRepository.findById(idUser).orElseThrow(() -> new AppUserNotFoundException(USER_NOT_FOUND));
        AppUserDTO appUserDTO = appUserMapper.toDto(appUser);
        return appUserDTO;
    }

    @Override
    public AppUserDTO saveAppUser(AppUserDTO appUserDTO) {

        appUserRepository.findByEmail(appUserDTO.getEmail()).ifPresent(
                (present) -> {
                    throw new RuntimeException("L'utilisateur existe déjà");
                });
        AppUser appUser = appUserMapper.toEntity(appUserDTO);
        //TO DO Encodage du password
        AppRole role = appRoleRepository.findByRoleName("USER");
        appUser.setRoles(List.of(role));
        AppUser appUserSaved = appUserRepository.save(appUser);
        AppUserDTO appUserDTOSaved = appUserMapper.toDto(appUserSaved);
        return appUserDTOSaved;
    }

    @Override
    public boolean deleteUserById(Long idUser) {
        appUserRepository.deleteById(idUser);
        return true;
    }

    @Override
    public AppUserDTO findByEmail(String email) throws AppUserNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email).orElseThrow(() -> new AppUserNotFoundException(USER_NOT_FOUND));
        AppUserDTO appUserDTO = appUserMapper.toDto(appUser);
        return appUserDTO;
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
    public List<AppUserDTO> listUsers() {
        List<AppUser> users = appUserRepository.findAll();
        List<AppUserDTO> userDTOS = users.stream().map(user -> appUserMapper.toDto(user)).collect(Collectors.toList());
        return userDTOS;
    }

    @Override
    public AppRole addNewRole(AppRole role){
        AppRole appRole = appRoleRepository.save(role);
        return appRole;
    }

    @Override
    public void addRoleToUser(String email, String roleName){
        Optional<AppUser> user = appUserRepository.findByEmail(email);
        AppRole role = appRoleRepository.findByRoleName(roleName);
        user.get().getRoles().add(role);
    }
}
