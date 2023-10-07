//package com.rhymthwave.Service.Implement;
//
//import java.util.Date;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Service;
//
//import com.rhymthwave.Service.JWTService;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.SignatureException;
//import io.jsonwebtoken.UnsupportedJwtException;
//
//@Service
//public class JWTServiceImpl implements JWTService {
//	private static final String SECRET_KEY = "11111111111111111111111111111111";
//
//	private static final long EXPIRE_TIME = 86400000000L;
//
//	private static final Logger logger = LoggerFactory.getLogger(JWTService.class.getName());
//
//	@Override
//	public String generateTokenLogin(Authentication authentication) {
//		UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
//		return Jwts.builder().setSubject((userPrinciple.getUsername()))
//				.setIssuedAt(new Date())
//				.setExpiration(new Date((new Date()).getTime()+EXPIRE_TIME*1000))
//				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//				.compact();
//	}
//
//	@Override
//	public Boolean validateJwtToken(String authToken) {
//		try {
//			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
//			return true;
//		} catch (SignatureException  e) {
//			 logger.error("Invalid JWT signature -> Message: {} ", e);
//		} catch (MalformedJwtException e) {
//
//            logger.error("Invalid JWT token -> Message: {}", e);
//
//        } catch (ExpiredJwtException e) {
//
//            logger.error("Expired JWT token -> Message: {}", e);
//
//        } catch (UnsupportedJwtException e) {
//
//            logger.error("Unsupported JWT token -> Message: {}", e);
//
//        } catch (IllegalArgumentException e) {
//
//            logger.error("JWT claims string is empty -> Message: {}", e);
//
//        }
//        return false;
//	}
//
//	@Override
//	public String getUserNameFromJwtToken(String token) {
//		String username = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
//		return username;
//	}
//
//}
