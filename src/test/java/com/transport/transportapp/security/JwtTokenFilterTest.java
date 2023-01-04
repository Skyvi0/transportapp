package com.transport.transportapp.security;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;

import com.transport.transportapp.security.JwtTokenFilter;
import com.transport.transportapp.security.JwtTokenProvider;
import com.transport.transportapp.service.UserService;

import io.jsonwebtoken.ExpiredJwtException;

@RunWith(MockitoJUnitRunner.class)
public class JwtTokenFilterTest {
    public class InvocationCountingFilterChain implements FilterChain {
        private int invocationCount = 0;

        @Override
        public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
            invocationCount++;
        }

        public int getInvocationCount() {
            return invocationCount;
        }
    }

    @Mock
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;
    private JwtTokenFilter jwtTokenFilter;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockFilterChain filterChain;

    @Before
    public void setUp() {
        // Create a mock instance of the UserService
        userService = mock(UserService.class);
        jwtTokenFilter = new JwtTokenFilter(jwtTokenProvider, userService);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        filterChain = new MockFilterChain();
    }

    @Test
    public void testDoFilterInternal_Success() throws Exception {
        // Set the authorization header
        request.addHeader("Authorization", "Bearer valid_token");
        // Set up the mock behavior for the JWT token provider
        when(jwtTokenProvider.resolveToken(request)).thenReturn("valid_token");
        when(jwtTokenProvider.validateToken("valid_token")).thenReturn(true);
        // Set up the mock behavior for the UserService
        UserDetails userDetails = mock(UserDetails.class);
        // Create an InvocationCountingFilterChain and pass it to the JWT token filter
        InvocationCountingFilterChain filterChain = new InvocationCountingFilterChain();
        jwtTokenFilter.doFilter(request, response, filterChain);
        // Verify that the filter chain was executed
        assertEquals(1, filterChain.getInvocationCount());
    }

@Test
public void testDoFilterInternal_NoToken() throws Exception {
    // Set up the mock behavior for the JWT token provider
    when(jwtTokenProvider.resolveToken(request)).thenReturn(null);
    // Create an InvocationCountingFilterChain and pass it to the JWT token filter
    InvocationCountingFilterChain filterChain = new InvocationCountingFilterChain();
    jwtTokenFilter.doFilter(request, response, filterChain);
    // Verify that the filter chain was executed
    assertEquals(1, filterChain.getInvocationCount());
}

}