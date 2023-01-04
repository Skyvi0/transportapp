package com.transport.transportapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.transport.transportapp.security.JwtTokenProvider;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@Component
public class JwtTokenProviderTest {
    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private JwtTokenProvider jwtTokenProvider;

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000; // 1h

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateToken_ValidInput_TokenCreated() {
        String username = "test";

        String token = jwtTokenProvider.createToken(username);

        Claims claims = Jwts.parser().setSigningKey(jwtTokenProvider.secretKey).parseClaimsJws(token).getBody();
        assertThat(claims.getSubject()).isEqualTo(username);
        assertThat(claims.getIssuedAt()).isCloseTo(new Date(), validityInMilliseconds);
        assertThat(claims.getExpiration()).isAfter(new Date());
    }

    @Test
    public void testResolveToken_ValidHeader_TokenReturned() {
    when(request.getHeader("Authorization")).thenReturn("Bearer valid");
    String token = jwtTokenProvider.resolveToken(request);

assertThat(token).isEqualTo("valid");
}

@Test
public void testResolveToken_InvalidHeader_NullReturned() {
when(request.getHeader("Authorization")).thenReturn("invalid");
String token = jwtTokenProvider.resolveToken(request);

assertThat(token).isNull();
}

    @Test
    public void testValidateToken_ValidToken_TrueReturned() {
        String token = Jwts.builder()//
                .setSubject("test")//
                .setIssuedAt(new Date())//
                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))//
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, secretKey)//
                .compact();
        boolean result = jwtTokenProvider.validateToken(token);

        assertThat(result).isTrue();
    }

    @Test
    public void testValidateToken_InvalidToken_FalseReturned() {
        String token = "invalid";
        boolean result = jwtTokenProvider.validateToken(token);

        assertThat(result).isFalse();
    }

    @Test
    public void testGetUsername_ValidToken_UsernameReturned() {
        String username = "test";
        String token = Jwts.builder()//
                .setSubject(username)//
                .setIssuedAt(new Date())//
                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))//
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, secretKey)//
                .compact();
        String result = jwtTokenProvider.getUsername(token);

        assertThat(result).isEqualTo(username);
    }
}