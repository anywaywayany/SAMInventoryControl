package com.samic.samic.views.login;

import com.samic.samic.security.AuthenticatedUser;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.router.internal.RouteUtil;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@PageTitle("Login")
@Route(value = "login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

private final AuthenticatedUser authenticatedUser;

private final LoginOverlay loginOverlay = new LoginOverlay();
private static final String USERNAME_LABEL = "Username";
private static final String PASSWORD_LABEL = "Password";
private static final boolean HAS_FORGOT_PASSWORD = false;

public LoginView(AuthenticatedUser authenticatedUser) {
	this.authenticatedUser = authenticatedUser;
	initUI();
}

private void initUI() {
	LoginI18n i18n = LoginI18n.createDefault();
	i18n.setHeader(new LoginI18n.Header());
	i18n.getForm().setUsername(USERNAME_LABEL);
	i18n.getForm().setPassword(PASSWORD_LABEL);

	loginOverlay.setI18n(i18n);
	loginOverlay.setForgotPasswordButtonVisible(HAS_FORGOT_PASSWORD);
	loginOverlay.setOpened(true);
	add(loginOverlay);
	// Prevent the example from stealing focus when browsing the
	// documentation
	loginOverlay.getElement().setAttribute("no-autofocus", "");
	// Login does not work without this one
	loginOverlay.setAction(
		RouteUtil.getRoutePath(VaadinService.getCurrent().getContext(), getClass()));
}

@Override
public void beforeEnter(BeforeEnterEvent event) {

	if (authenticatedUser.getUser().isPresent()) {
	// Already logged in
	// setOpened(false);
	event.forwardTo("");
	}

	loginOverlay.setError(
		event.getLocation().getQueryParameters().getParameters().containsKey("error"));
}
}
