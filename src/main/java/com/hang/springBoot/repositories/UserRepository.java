package com.hang.springBoot.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hang.springBoot.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
