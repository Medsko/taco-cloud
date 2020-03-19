package com.medsko.tacos.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Taco_Order")
public class Order {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private User user;

	private Date placedAt;

	@NotBlank(message = "Name is required")
	private String name;

	@NotBlank(message = "Street is required")
	private String street;

	@NotBlank(message = "City is required")
	private String city;

	@NotBlank(message = "State is required")
	private String state;

	@NotBlank(message = "Zip code is required")
	private String zip;

	@CreditCardNumber(message = "Not a valid credit card number", ignoreNonDigitCharacters = true)
	private String ccNumber;

	@Pattern(regexp = "^(0[1-9]|1[0-2])/[0-9]{2}$", message = "Must be formatted MM/YY")
	private String ccExpiration;

	@Digits(integer = 3, fraction = 0, message = "Invalid CVV")
	private String ccCVV;

	@OneToMany
	private List<Taco> designs = new ArrayList<>();

	public Order(Order original) {
		this.user = original.user;
		this.placedAt = original.placedAt;
		this.name = original.name;
		this.street = original.street;
		this.city = original.city;
		this.state = original.state;
		this.zip = original.zip;
		this.ccNumber = original.ccNumber;
		this.ccExpiration = original.ccExpiration;
		this.ccCVV = original.ccCVV;
	}

	public void addDesign(Taco design) {
		designs.add(design);
	}
}
