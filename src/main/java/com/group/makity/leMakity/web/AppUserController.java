package com.group.makity.leMakity.web;

import com.group.makity.leMakity.dtos.AppUserDTO;
import com.group.makity.leMakity.exceptions.AppUserNotFoundException;
import com.group.makity.leMakity.services.AppUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/users")
public class AppUserController {

    private AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/all")
    public List<AppUserDTO> listUserDTOs(){
        return appUserService.listUsers();
    }

    @GetMapping("/{id}")
    public AppUserDTO getUser(@PathVariable(name = "id") Long idUser) throws AppUserNotFoundException {
        return appUserService.findById(idUser);
    }

    @PostMapping("/save")
    public AppUserDTO saveUser(@RequestBody AppUserDTO appUserDTO){
        return appUserService.saveAppUser(appUserDTO);
    }

    @PutMapping("/update/{id}")
    public AppUserDTO updateUser(@PathVariable(name = "id") Long idUser, @RequestBody AppUserDTO appUserDTO) throws AppUserNotFoundException {
        appUserDTO.setIdUser(idUser);
        return appUserService.updateUser(appUserDTO);
    }

    @DeleteMapping("/{idUser}")
    public void deleteUser(@PathVariable Long idUser){
        appUserService.deleteUserById(idUser);
    }
}
