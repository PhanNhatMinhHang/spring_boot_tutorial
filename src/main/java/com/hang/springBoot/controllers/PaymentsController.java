package com.hang.springBoot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hang.springBoot.models.Payment;
import com.hang.springBoot.repositories.PaymentRepository;
import com.hang.springBoot.repositories.UserRepository;

@RestController
@RequestMapping("/")
public class PaymentsController {

	@Autowired
	private PaymentRepository repository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/users/{userId}/payments")
	public List<Payment> findAllPayment(@PathVariable Long userId) {
		return repository.findByUserId(userId);
	}

	@GetMapping("/users/{userId}/payments/{id}")
	public Payment findByPaymentId(@PathVariable Long userId, @PathVariable Long id) {
		return repository.findByUserIdAndId(userId, id).orElseThrow(() -> new ResourceNotFoundException());
	}

	@PostMapping("/users/{userId}/payments")
	public Payment createPayment(@PathVariable Long userId, Payment payment) {
		payment.setUser(CommonFinder.findUserById(userRepository, userId));
		return repository.save(payment);
	}

	@PutMapping("/users/{userId}/payments/{id}")
	public Payment updatePayment(@PathVariable Long userId, @PathVariable Long id, Payment newPayment) {
		Payment payment = findByPaymentId(userId, id);
		payment.setCardName(newPayment.getCardName());
		payment.setCardExpiredMonth(newPayment.getCardExpiredMonth());
		payment.setCardNumber(newPayment.getCardNumber());
		payment.setCardType(newPayment.getCardType());
		return repository.save(payment);
	}

	@DeleteMapping("/users/{userId}/payments/{id}")
	public boolean deletePayment(@PathVariable Long userId, @PathVariable Long id) {
		repository.delete(findByPaymentId(userId, id));
		return true;
	}
}
