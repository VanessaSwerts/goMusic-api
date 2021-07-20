package br.inatel.icc.goMusic.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.inatel.icc.goMusic.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${goMusic.jwt.expiration}")
	private String expiration;

	@Value("${goMusic.jwt.secret}")
	private String secret;

	public String generateToken(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		Date now = new Date();
		Date expiredAt = new Date(now.getTime() + Long.parseLong(expiration));
		
		System.out.println("TOKEN GERADO");
		return Jwts.builder().setIssuer("Music App API").setSubject(user.getId().toString()).setIssuedAt(now)
				.setExpiration(expiredAt).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getUserId(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
