package com.example.myproject.client;

import java.util.ArrayList;

import com.example.myproject.client.entities.Animal;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("persistentservice")
public interface PersistentService extends RemoteService {
	
	void persistAnimal(String color, String species) throws IllegalArgumentException;

	ArrayList<Animal> searchAnimal(String searchString);
	
}