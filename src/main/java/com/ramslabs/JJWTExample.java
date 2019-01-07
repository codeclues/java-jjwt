package com.ramslabs;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JJWTExample {

	public static void main(String[] args) {
		String jwt = createJWTToken();
		System.out.println(jwt);
		validateJWT(jwt);
	}

	public static String createJWTToken() {
		String jwt = null;
		try {
			jwt = Jwts.builder().setSubject("users/TzMUocMF4p")
					.claim("name", "Robert Token Man").claim("scope", "self groups/admins")
					.signWith(SignatureAlgorithm.HS256, "secret".getBytes("UTF-8")).compact();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return jwt;
	}

	public static void validateJWT(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey("secret".getBytes("UTF-8")).parseClaimsJws(jwt);
			String scope = (String) claims.getBody().get("scope");
			System.out.println(scope);
			if (scope.equalsIgnoreCase("self groups/admins")) {
				System.out.println("jwt is valid");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
