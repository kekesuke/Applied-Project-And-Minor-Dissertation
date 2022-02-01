package com.fitnessbuddyapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.fitnessbuddyapi.models.ERole;
import com.fitnessbuddyapi.models.Role;
import com.fitnessbuddyapi.services.interfaces.IRoleService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class AuthApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(AuthApplication.class);
	
	@Autowired
	private IRoleService<Role> roleService;


	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (roleService.findAll().isEmpty()) {
			roleService.saveOrUpdate(new Role(ERole.ROLE_ADMIN));
			roleService.saveOrUpdate(new Role(ERole.ROLE_USER));
		}
	}
	


}
