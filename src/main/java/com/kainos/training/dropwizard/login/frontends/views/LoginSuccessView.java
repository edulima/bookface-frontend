package com.kainos.training.dropwizard.login.frontends.views;

import io.dropwizard.views.View;
import java.util.*;

import com.kainos.training.blackbox.client.FriendClient;
import com.kainos.training.blackboxinterface.model.person.Person;

public class LoginSuccessView extends View {
	
    private final List<Person> friends;
    FriendClient friendClient; 
	
	public LoginSuccessView(){
		super("/login_success.ftl");
        friendClient = new FriendClient();
        this.friends = friendClient.getFriend();
        System.out.println(this.friends.get(0).getName());
	}
	
	public List<Person> getFriends() {
		return this.friends;
	}
}
