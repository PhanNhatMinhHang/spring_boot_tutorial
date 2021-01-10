package com.hang.springBoot.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hang.springBoot.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
	List<Payment> findByUserId(Long userId);
	Optional<Payment> findByUserIdAndId(Long userId, Long id);
}
