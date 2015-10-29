package com.kainos.training.dropwizard.login.frontends.resources;

import io.dropwizard.views.View;

import org.junit.Test;
import static org.junit.Assert.*;

import com.kainos.training.blackbox.client.FriendClient;
import com.kainos.training.blackboxinterface.model.person.Person;
import com.kainos.training.dropwizard.login.frontends.resources.ViewsResource;
import com.kainos.training.jersey.client.LoginClient;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.eq;

import com.kainos.training.jersey.client.LoginClient;
import javax.ws.rs.core.Response;

public class ViewsResourceTest {
	
	LoginClient mockClient = mock(LoginClient.class);
	FriendClient mockFriendClient = mock(FriendClient.class);
	Response  mockResponse = mock(Response.class);
	
	ViewsResource resource = new ViewsResource(mockClient, mockFriendClient);
	
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
	
	@Test
	public void testAddFriend() {
		// Make bob, and set his name
		Person person = new Person();
		person.setName("bob");
		
		// Set our mocks up to return the results we want
		// addFriend should give us a response
		when(mockFriendClient.addFriend(eq(person))).thenReturn(mockResponse);
		// our response should give status 200
		when(mockResponse.getStatus()).thenReturn(200);
		
		// run our function
		Response response = resource.addFriend("bob");
		
		// check it gave us the right response
		assertEquals(response.getLocation().toString(), "test/loginSuccess");
		
		// check that our mock was called
		verify(mockFriendClient).addFriend(person);
	}	
	
	@Test
	public void testDeleteFriend() {
		// Make bob, and set his name
		Person person = new Person();
		person.setName("bob");
		
		// Set our mocks up to return the results we want
		// addFriend should give us a response
		when(mockFriendClient.deleteFriend(0)).thenReturn(mockResponse);
		// our response should give status 200
		when(mockResponse.getStatus()).thenReturn(200);
		
		// run our function
		Response response = resource.deleteFriend(0);
		
		// check it gave us the right response
		assertEquals(response.getLocation().toString(), "test/loginSuccess");
		
		// check that our mock was called
		verify(mockFriendClient).deleteFriend(0);
	}
	
}
