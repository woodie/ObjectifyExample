package com.example.myproject.client;

import java.util.ArrayList;

import com.example.myproject.client.entities.Animal;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ObjectifyExample implements EntryPoint {

	// Create some fields for the UI
	VerticalPanel mainVerticalPanel = new VerticalPanel();
	HorizontalPanel hp1 = new HorizontalPanel();
	HorizontalPanel hp2 = new HorizontalPanel();
	HorizontalPanel hp3 = new HorizontalPanel();
	HTML colorLabel = new HTML("Color");
	HTML speciesLabel = new HTML("Species");
	TextBox colorTextBox = new TextBox();
	TextBox speciesTextBox = new TextBox();
	Button submit = new Button("Submit");

	TextBox searchTextBox = new TextBox();
	HTML searchLabel = new HTML("Enter a species to search for");
	Button search = new Button("Search");
	FlexTable searchResultsTable = new FlexTable();

	// Instantiate the interfaces to access methods in the interface
	private final PersistentServiceAsync persistentService = GWT
			.create(PersistentService.class);

	public void onModuleLoad() {

		hp1.add(colorLabel);
		hp1.add(colorTextBox);
		hp2.add(speciesLabel);
		hp2.add(speciesTextBox);
		hp3.add(searchLabel);
		hp3.add(searchTextBox);
		hp3.add(search);

		mainVerticalPanel.add(hp1);
		mainVerticalPanel.add(hp2);
		mainVerticalPanel.add(submit);
		mainVerticalPanel.add(hp3);
		mainVerticalPanel.add(searchResultsTable);

		hp1.setSpacing(5);
		hp2.setSpacing(5);
		hp3.setSpacing(5);
		mainVerticalPanel.setSpacing(5);

		submit.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				persistentService.persistAnimal(colorTextBox.getText(),
						speciesTextBox.getText(), new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {

								System.out
										.println("PersistentService RPC call failed "
												+ caught);

							}

							@Override
							public void onSuccess(Void result) {

								System.out
										.println("PersistentService RPC call succeded");

							}

						});

			}
		});

		search.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				persistentService.searchAnimal(searchTextBox.getText(),
						new AsyncCallback<ArrayList<Animal>>() {

							@Override
							public void onFailure(Throwable caught) {

								System.out
										.println("PersistentService RPC call failed "
												+ caught);

							}

							public void onSuccess(ArrayList<Animal> result) {

								int row = 0;
								searchResultsTable.removeAllRows();
								searchResultsTable.setText(0, 0, "Color");
								searchResultsTable.setText(0, 1, "Species");
								//loop the array list and user getters to add 
								//records to the table
								for (Animal animal : result) {
									row = searchResultsTable.getRowCount();
									searchResultsTable.setText(row, 0,
											animal.getColor());
									searchResultsTable.setText(row, 1,
											animal.getSpecies());

								}

							}

						});

			}
		});

		RootPanel.get("container").add(mainVerticalPanel);
	}
}
