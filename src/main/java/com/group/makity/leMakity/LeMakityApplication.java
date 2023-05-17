package com.group.makity.leMakity;

import com.group.makity.leMakity.dtos.AppUserDTO;
import com.group.makity.leMakity.entities.AppRole;
import com.group.makity.leMakity.services.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class LeMakityApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeMakityApplication.class, args);
	}

	/*@Bean
	CommandLineRunner start(AppUserRepository appUserRepository,
							AppRoleRepository AppOrderRepository,
							AppOrderRepository appOrderRepository,
							AppCategoryRepository appCategoryRepository,
							ProductRepository productRepository){
		return args -> {
			Stream.of("Lamarana", "Ibrahima", "Boubacar").forEach(name->{
				AppUser appUser = new AppUser();
				appUser.setFirstName("BAH");
				appUser.setLastName(name);
				appUser.setEmail(name+"@gmail.com");
				appUser.setPassword("1234");
				appUser.setTelephone("01 23 45 67 89");
				appUser.setAddress("25 Rue de Paris 75012 Paris");
				appUserRepository.save(appUser);
			});
		};
	}*/

	/*@Bean
	CommandLineRunner start(AppUserService appUserService){
		return args -> {
			appUserService.addNewRole(new AppRole(null, "ADMIN"));
			appUserService.addNewRole(new AppRole(null, "USER"));

			appUserService.saveAppUser(new AppUserDTO(null, "Boubacar", "BAH", "boubacar@gmail.com", "1234", "01 23 45 67 89", "25 Rue de Paris 75012 Paris", new ArrayList<>()));
			appUserService.saveAppUser(new AppUserDTO(null, "Lamarana", "DIALLO", "lamarana@gmail.com", "1234", "01 23 45 67 89", "26 Rue de Paris 75012 Paris", new ArrayList<>()));
			appUserService.saveAppUser(new AppUserDTO(null, "Ibrahima", "DIALLO", "ibrahima@gmail.com", "1234", "01 23 45 67 89", "27 Rue de Paris 75012 Paris", new ArrayList<>()));

			appUserService.addRoleToUser("boubacar@gmail.com", "ADMIN");
			appUserService.addRoleToUser("lamarana@gmail.com", "USER");
			appUserService.addRoleToUser("ibrahima@gmail.com", "USER");

		};
	}*/
}
