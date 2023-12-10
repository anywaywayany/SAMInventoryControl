package com.samic.samic.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.HashMap;

public class UIFactory {

public static VerticalLayout rootComponentContainer(String heading, Component... containers) {
	VerticalLayout container = new VerticalLayout();

	// add class-name for css
	container.addClassName("container");

	// add heading
	container.add(new H4(heading));

	// add layouts/containers that contain multiple components
	container.add(containers);
	return container;
}

public static HorizontalLayout childContainer(
	FlexComponent.JustifyContentMode justifyContentMode, Component... components) {
	// Create container
	HorizontalLayout childContainer = new HorizontalLayout();

	// Style
	childContainer.setJustifyContentMode(justifyContentMode);
	// make it wrap content
	childContainer.getStyle().set("flex-wrap", "wrap");
	childContainer.setWidthFull();

	// Add optional components passed to method
	childContainer.add(components);

	return childContainer;
}

public static Button btnPrimary(String text) {
	Button btnPrimary = new Button(text);
	btnPrimary.getStyle().setBackground("#108AB2").setColor("#FFFFFF");
	return btnPrimary;
}

public static Button btnPrimary(
	String text, ComponentEventListener<ClickEvent<Button>> listener) {
	Button btnPrimary = UIFactory.btnPrimary(text);
	btnPrimary.addClickListener(listener);
	return btnPrimary;
}

public static Button btnPrimary(String text, HashMap<String, String> cssKeyValue) {
	Button btnPrimary = UIFactory.btnPrimary(text);
	cssKeyValue.forEach(
		(cssKey, cssValue) -> {
		btnPrimary.getStyle().set(cssKey, cssValue);
		});
	return btnPrimary;
}

public static Button btnPrimary(
	String text,
	ComponentEventListener<ClickEvent<Button>> listener,
	HashMap<String, String> cssKeyValuePairs) {
	Button btnPrimary = UIFactory.btnPrimary(text, cssKeyValuePairs);
	btnPrimary.addClickListener(listener);
	return btnPrimary;
}

public static Button btnPrimaryError(String text) {
	Button btnPrimary = new Button(text);
	btnPrimary.getStyle().setBackground("#FF3101").setColor("#FFFFFF");
	return btnPrimary;
}

public static Button btnPrimaryError(
	String text, ComponentEventListener<ClickEvent<Button>> listener) {
	Button btnPrimary = UIFactory.btnPrimaryError(text);
	btnPrimary.addClickListener(listener);
	return btnPrimary;
}

public static Notification NotificationSuccess(String text){
	Notification notification = new Notification(text);
	notification.setDuration(5000);
	notification.setPosition(Notification.Position.BOTTOM_CENTER);
	notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
	return notification;
}

	public static Notification NotificationError(String text) {
	Notification notification = new Notification(text);
		notification.setDuration(5000);
		notification.setPosition(Notification.Position.BOTTOM_CENTER);
		notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
		return notification;
	}

}
