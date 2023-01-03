package com.transport.transportapp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.transport.transportapp.security.JwtTokenFilter;
import com.transport.transportapp.security.JwtTokenProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

@RunWith(MockitoJUnitRunner.class)
public class JwtTokenFilterTest {

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    private JwtTokenFilter jwtTokenFilter;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockFilterChain filterChain;

    @Before
    public void setUp() {
        jwtTokenFilter = new JwtTokenFilter(null, jwtTokenProvider);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        filterChain = new MockFilterChain();
    }

    @Test
    public void testDoFilterInternal_Success() throws Exception {
        // Set the authorization header
        request.addHeader("Authorization", "Bearer valid_token");
        // Configure the mock JwtTokenProvider to return true when validateToken is
        // called
        // with the "valid_token" string
        when(jwtTokenProvider.validateToken("valid_token")).thenReturn(true);
        // Call the doFilterInternal method of the JwtTokenFilter
        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        // Verify that the request and response objects are forwarded to the next filter
        // in the
        // chain
        filterChain.doFilter(request, response);
        // Verify that the response status is set to 200 (OK)
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testDoFilterInternal_InvalidToken() throws Exception {
        // Set the authorization header
        request.addHeader("Authorization", "Bearer invalid_token");
        // Configure the mock JwtTokenProvider to return false when validateToken is
        // called
        // with the "invalid_token" string
        when(jwtTokenProvider.validateToken("invalid_token")).thenReturn(false);

        // Call the doFilterInternal method of the JwtTokenFilter
        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        // Verify that the response status is set to 401 (Unauthorized)
        assertEquals(401, response.getStatus());
    }

    @Test
    public void testDoFilterInternal_MissingAuthorizationHeader() throws Exception {
        // Call the doFilterInternal method of the JwtTokenFilter without setting the
        // authorization header
        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        // Verify that the response status is set to 401 (Unauthorized)
        assertEquals(401, response.getStatus());
    }
}