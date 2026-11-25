package com.Library.library_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Library.library_management.entity.AppUser;

public interface UserRepository extends JpaRepository<AppUser,Long>{
	 Optional<AppUser> findByUsername(String username);

}
