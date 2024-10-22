package com.scm;

import com.scm.entities.User;
import com.scm.helpers.AppConstants;
import com.scm.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class ScmApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScmApplication.class, args);
	}

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public void run(String... args) throws Exception {
		User user=new User();
		user.setUserID(UUID.randomUUID().toString());
		user.setName("admin");
		user.setEmail("admin@gmail.com");
		user.setPassword(passwordEncoder.encode("admin"));
		user.setRoleList(List.of(AppConstants.ROLE_USER));
		user.setEmailVerified(true);
		user.setEnabled(true);
		user.setAbout("Dummy admin user created");
		user.setPhoneVerified(true);
		userRepo.findByEmail("admin@gmail.com").ifPresentOrElse(user1 -> {},()->userRepo.save(user));
		System.out.println("Admin user created");
}

