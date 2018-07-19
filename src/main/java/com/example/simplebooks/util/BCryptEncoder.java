package com.example.simplebooks.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptEncoder {

	public String getBcryptHash(String plainText) {
		return new BCryptPasswordEncoder().encode(plainText);
	}
}
