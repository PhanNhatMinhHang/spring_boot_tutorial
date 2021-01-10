package com.hang.springBoot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "card_name")
	private String cardName;
	@Column(name = "card_type")
	private String cardType;
	@Column(name = "card_number")
	private String cardNumber;
	@Column(name = "card_expired_month")
	private String cardExpiredMonth;
	@Column(name = "user_id")
	private Long userId;

	public Payment() {
		// TODO Auto-generated constructor stub
	}

	public Payment(Long id, String cardName, String cardType, String cardNumber, String cardExpiredMonth,
			Long userId) {
		this.id = id;
		this.cardName = cardName;
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.cardExpiredMonth = cardExpiredMonth;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		if (cardName!=null) {
			this.cardName = cardName;	
		}
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		if (cardType!=null) {
			this.cardType = cardType;	
		}
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		if (cardNumber!=null) {
			this.cardNumber = cardNumber;	
		}
	}

	public String getCardExpiredMonth() {
		return cardExpiredMonth;
	}

	public void setCardExpiredMonth(String cardExpiredMonth) {
		if (cardExpiredMonth!=null) {
			this.cardExpiredMonth = cardExpiredMonth;	
		}
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		if (userId!=null) {
			this.userId = userId;	
		}
	}

}
