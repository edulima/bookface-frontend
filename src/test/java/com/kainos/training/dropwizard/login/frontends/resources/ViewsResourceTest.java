package com.kainos.training.dropwizard.login.frontends.resources;

import io.dropwizard.views.View;

import org.junit.Test;
import static org.junit.Assert.*;

import com.kainos.training.dropwizard.login.frontends.resources.ViewsResource;
import com.kainos.training.jersey.client.LoginClient;
import static org.mockito.Mockito.*;

import com.kainos.training.jersey.client.LoginClient;
import javax.ws.rs.core.Response;

public class ViewsResourceTest {
	
	LoginClient mockClient = mock(LoginClient.class);
	ViewsResource resource = new ViewsResource(mockClient);
	
	private static final String EXPECTED_USERNAME = "admin";
	private static final String EXPECTED_PASSWORD = "password";
	
	private static final String INVALID_USERNAME = "failed";
	private static final String INVALID_PASSWORD = "failed";

	
	@Test
	public void correctUsernameAndPasswordRedirectToSuccess() {
		when(mockClient.getLoginString(EXPECTED_USERNAME, EXPECTED_PASSWORD)).thenReturn(204);
		Response response = resource.loginDetails(EXPECTED_USERNAME, EXPECTED_PASSWORD);
		assertEquals(response.getLocation().toString(), "test/loginSuccess");	
	}
	
	@Test
	public void testInvalidPassword() {
		when(mockClient.getLoginString(EXPECTED_USERNAME, INVALID_PASSWORD)).thenReturn(401);
		
		Response response = resource.loginDetails(EXPECTED_USERNAME, INVALID_PASSWORD);
		assertEquals(response.getLocation().toString(), "test/loginFailed");	
		
		verify(mockClient).getLoginString(EXPECTED_USERNAME, INVALID_PASSWORD);
	}
	
	@Test
	public void testInvalidUsername() {
		when(mockClient.getLoginString(INVALID_USERNAME, EXPECTED_PASSWORD)).thenReturn(404);
		
		Response response = resource.loginDetails(INVALID_USERNAME, EXPECTED_PASSWORD);
		assertEquals(response.getLocation().toString(), "test/loginFailed");
		
		verify(mockClient).getLoginString(INVALID_USERNAME, EXPECTED_PASSWORD);
	}
		
}
