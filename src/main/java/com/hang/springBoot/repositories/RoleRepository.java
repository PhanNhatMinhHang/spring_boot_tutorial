package com.hang.springBoot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hang.springBoot.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(String name);
}
