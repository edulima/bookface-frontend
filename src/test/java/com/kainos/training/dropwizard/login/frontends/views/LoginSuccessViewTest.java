package com.kainos.training.dropwizard.login.frontends.views;

import org.junit.Test;

import com.kainos.training.blackbox.client.FriendClient;
import com.kainos.training.blackboxinterface.model.person.Person;
import com.kainos.training.dropwizard.login.frontends.resources.ViewsResource;
import com.kainos.training.dropwizard.login.frontends.views.LoginSuccessView;
import com.kainos.training.jersey.client.LoginClient;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

public class LoginSuccessViewTest {
	//Test goes here...
	
	LoginClient mockClient = mock(LoginClient.class);
	FriendClient mockFriendClient = mock(FriendClient.class);
	Response  mockResponse = mock(Response.class);
	List<Person> friends = new ArrayList<Person>();
			
	
	@Test
	public void testGetFriend() {
		// Set our mocks up to return the results we want
		// addFriend should give us a response
		when(mockFriendClient.getFriend()).thenReturn(friends);
		
		// run our function
		new LoginSuccessView(mockFriendClient);
		
		// check that our mock was called
		verify(mockFriendClient).getFriend();
	}
	
}
