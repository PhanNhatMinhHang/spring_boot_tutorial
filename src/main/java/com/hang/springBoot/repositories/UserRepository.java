package com.hang.springBoot.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hang.springBoot.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByNameAndPassword(String name, String password);

	List<User> findByNameLike(String name);
}
