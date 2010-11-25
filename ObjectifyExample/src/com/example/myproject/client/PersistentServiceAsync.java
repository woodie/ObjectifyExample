package com.example.myproject.client;

import java.util.ArrayList;

import com.example.myproject.client.entities.Animal;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PersistentServiceAsync {

	void persistAnimal(String color, String species, AsyncCallback<Void> callback);

	void searchAnimal(String searchString,
			AsyncCallback<ArrayList<Animal>> callback);

}
