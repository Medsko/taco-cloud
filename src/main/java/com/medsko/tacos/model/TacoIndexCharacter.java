package com.medsko.tacos.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Data
@Entity(name = "char")
public class TacoIndexCharacter {

	@Id
	@GeneratedValue
	private Long id;

	private final Character _char;
}
