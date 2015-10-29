package com.kainos.training.dropwizard.login.frontends.views;

import io.dropwizard.views.View;
import java.util.*;

import com.kainos.training.blackbox.client.FriendClient;
import com.kainos.training.blackboxinterface.model.person.Person;

public class LoginSuccessView extends View {
	
    private final List<Person> friends;
    FriendClient friendClient; 
	
	public LoginSuccessView(FriendClient friendClient){
		super("/login_success.ftl");
        this.friends = friendClient.getFriend();
	}
	
	public List<Person> getFriends() {
		return this.friends;
	}
}
