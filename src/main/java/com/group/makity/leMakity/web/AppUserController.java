package com.group.makity.leMakity.web;

import com.group.makity.leMakity.dtos.AppUserDTO;
import com.group.makity.leMakity.dtos.ProductHistoryDTO;
import com.group.makity.leMakity.dtos.UserHistoryDTO;
import com.group.makity.leMakity.exceptions.AppUserNotFoundException;
import com.group.makity.leMakity.services.AppUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/users")
@CrossOrigin(origins = "*")
public class AppUserController {

    private AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/all")
    public List<AppUserDTO> listUserDTOs(){
        return appUserService.listUsers();
    }

    @GetMapping("/pageUser")
    public UserHistoryDTO listProductPage(@RequestParam(name = "keyword", defaultValue = "") String keyword,
                                                     @RequestParam(name = "page", defaultValue = "0") int page,
                                                     @RequestParam(name = "size", defaultValue = "1") int size){
        return appUserService.listPageUser("%" + keyword + "%",page, size);
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
    public boolean deleteUser(@PathVariable Long idUser){
        return appUserService.deleteUserById(idUser);
    }
}
