package com.example.myproject.server;

import java.util.ArrayList;

import com.example.myproject.client.PersistentService;
import com.example.myproject.client.entities.Animal;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;


@SuppressWarnings("serial")
public class PersistentServiceImpl extends RemoteServiceServlet implements
PersistentService {
	
	//Method must be present in Service Interface and Async Interface
	public void persistAnimal(String color, String species) {
		
		//The Objectify service for the entity must be registered before any 
		//operations can be executed
		ObjectifyService.register(Animal.class);
		Objectify ofy = ObjectifyService.begin();
		
		Animal animal = new Animal();
		//Use setters to populate the object
		//the Key will be auto generated and does not need to be set
		animal.setColor(color);
		animal.setSpecies(species);
		
		ofy.put(animal);
		
	}

	
	public ArrayList<Animal> searchAnimal(String searchString) {
		
		ObjectifyService.register(Animal.class);
		Objectify ofy = ObjectifyService.begin();
		
		Query<Animal> q = ofy.query(Animal.class).filter("species",
				searchString);
		
		ArrayList<Animal> animals = new ArrayList<Animal>();
		
		//Loop the query results and add to the array
		for (Animal fetched : q) {
			
			animals.add(fetched);
		}
		
		return animals;
		
	}

}
