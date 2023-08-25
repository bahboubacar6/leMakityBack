package com.group.makity.leMakity.web;

import com.group.makity.leMakity.dtos.AppUserDTO;
import com.group.makity.leMakity.dtos.UserHistoryDTO;
import com.group.makity.leMakity.exceptions.AppUserNotFoundException;
import com.group.makity.leMakity.security.service.AccountService;
import com.group.makity.leMakity.services.AppUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/users", produces = "application/json")
@CrossOrigin(origins = "*")
public class AppUserController {

    private AppUserService appUserService;
    private AccountService accountService;

    public AppUserController(AppUserService appUserService, AccountService accountService) {
        this.appUserService = appUserService;
        this.accountService = accountService;
    }

    @GetMapping("/all")
    public List<AppUserDTO> listUserDTOs(){
        return appUserService.listUsers();
    }

    @GetMapping("/pageUser")
    public UserHistoryDTO listUserPage(@RequestParam(name = "keyword", defaultValue = "") String keyword,
                                                     @RequestParam(name = "page", defaultValue = "0") int page,
                                                     @RequestParam(name = "size", defaultValue = "3") int size){
        return appUserService.listPageUser("%" + keyword + "%",page, size);
    }

    @GetMapping("/{id}")
    public AppUserDTO getUser(@PathVariable(name = "id") Long idUser) throws AppUserNotFoundException {
        return appUserService.findById(idUser);
    }

    @PutMapping("/update/{id}")
    public AppUserDTO updateUser(@PathVariable(name = "id") Long idUser, @RequestBody AppUserDTO appUserDTO) throws AppUserNotFoundException {
        appUserDTO.setIdUser(idUser);
        return accountService.updateUser(appUserDTO);
    }

    @DeleteMapping("/{idUser}")
    public boolean deleteUser(@PathVariable Long idUser){
        return appUserService.deleteUserById(idUser);
    }
}
