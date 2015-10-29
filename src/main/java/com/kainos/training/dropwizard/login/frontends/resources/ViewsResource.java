package com.kainos.training.dropwizard.login.frontends.resources;

import io.dropwizard.views.View;

import java.net.URI;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.codahale.metrics.annotation.Timed;
import com.kainos.training.blackbox.client.FriendClient;
import com.kainos.training.blackboxinterface.model.person.Person;
import com.kainos.training.dropwizard.login.frontends.views.Index;
import com.kainos.training.dropwizard.login.frontends.views.LoginFailureView;
import com.kainos.training.dropwizard.login.frontends.views.LoginSuccessView;
import com.kainos.training.jersey.client.LoginClient;

import ch.qos.logback.classic.Logger;

@Path("/test")
public class ViewsResource {
	LoginClient loginClient;
	
	public ViewsResource(LoginClient client) {
		this.loginClient = client;
	}

	@GET
	@Timed
	@Path("/login")
	@Produces(MediaType.TEXT_HTML)	
	public View login() {
		return new Index();
	}
	
	@POST
	@Timed
	@Path("login-details")
	@Produces(MediaType.TEXT_HTML)
	public Response loginDetails(@FormParam("username") String username,
			 			     @FormParam("password") String password){
		int status = loginClient.getLoginString(username, password);
	
		if(status == 204) {
			
			return Response.seeOther(URI.create("test/loginSuccess")).build();
			
		}else{
		
			return Response.seeOther(URI.create("test/loginFailed")).build();
		}
	}
	
	@GET
	@Timed
	@Path("/loginFailed")
	@Produces(MediaType.TEXT_HTML)	
	public View loginFailed() {
		return new LoginFailureView();
	}
	
	@GET
	@Timed
	@Path("/loginSuccess")
	@Produces(MediaType.TEXT_HTML)	
	public View loginSuccess() {
		return new LoginSuccessView();
	}
	
	@POST
	@Timed
	@Path("friend-details")
	@Produces(MediaType.TEXT_HTML)
	public Response addFriend(@FormParam("friendName") String friendName){

		Person newFriend = new Person();
		newFriend.setName(friendName);
		
		FriendClient friendClient = new FriendClient();
		
		int status = friendClient.addFriend(newFriend).getStatus();
	
		if(status == 200) {
			// FRIEND IS IN!
			System.out.println("friend added");
			return Response.seeOther(URI.create("test/loginSuccess")).build();
			
			
		}else{
			// FRIEND FAILEDDD
			System.out.println("friend not added");
			return Response.seeOther(URI.create("test/loginSuccess")).build();
		}
	}
	
	@GET
	@Timed
	@Path("friend-delete/{id}")
	@Produces(MediaType.TEXT_HTML)
	public Response deleteFriend(@PathParam("id") int idToDelete){

		FriendClient friendClient = new FriendClient();
		
		int status = friendClient.deleteFriend(idToDelete).getStatus();
	
		if(status == 200) {
			// FRIEND IS IN!
			System.out.println("friend deleted");
			return Response.seeOther(URI.create("test/loginSuccess")).build();
			
			
		}else{
			// FRIEND FAILEDDD
			System.out.println("friend not deleted");
			return Response.seeOther(URI.create("test/loginSuccess")).build();
		}
	
	}
	
}
