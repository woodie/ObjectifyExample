//This package must be a sub package of client
package com.example.myproject.client.entities;

import java.io.Serializable;

import javax.persistence.Id;

@SuppressWarnings("serial")
public class Animal implements Serializable {
	
	@Id
	Long id;
	private String species;
	private String color;

	public void setSpecies(String species) {
		this.species = species;
	}


	public String getSpecies() {
		return species;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getColor() {
		return color;
	}
	
}
