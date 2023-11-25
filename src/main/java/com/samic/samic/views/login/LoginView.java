package com.samic.samic.views.login;

import com.samic.samic.security.AuthenticatedUser;
import com.vaadin.flow.component.html.Anchor;
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

    public LoginView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;

        // Mulit Language Support

        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("SOME");
        i18n.getHeader().setDescription("Inventory Control");

        Anchor register = new Anchor("register", "Create an Account");
        register.setWidthFull();
        loginOverlay.setI18n(i18n);
        loginOverlay.getCustomFormArea().add(register);
        loginOverlay.getFooter().add(register);
        // end::snippet[]
        add(loginOverlay);
        loginOverlay.setOpened(true);
        // Prevent the example from stealing focus when browsing the
        // documentation
        loginOverlay.getElement().setAttribute("no-autofocus", "");

        // Login does not work without this one
        loginOverlay.setAction(
                RouteUtil.getRoutePath(VaadinService.getCurrent().getContext(), getClass()));
    }

/*
i18n.getHeader().setDescription("No Demo User Credentials");
i18n.setAdditionalInformation(null);
i18n.getForm().setForgotPassword("Forgot Password");
setI18n(i18n);

setForgotPasswordButtonVisible(true);
setOpened(true);
*/

    @Override
    public void beforeEnter(BeforeEnterEvent event) {

        if (authenticatedUser.get().isPresent()) {
            // Already logged in
            // setOpened(false);
            event.forwardTo("");
        }

        loginOverlay.setError(
                event.getLocation().getQueryParameters().getParameters().containsKey("error"));
    }
}