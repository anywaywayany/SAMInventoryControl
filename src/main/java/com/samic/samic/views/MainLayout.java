package com.samic.samic.views;

import com.samic.samic.data.entity.User;
import com.samic.samic.security.AuthenticatedUser;
import com.samic.samic.views.dashboard.DashboardView;
import com.samic.samic.views.freie_lagerobjekte.FreieLagerobjekteView;
import com.samic.samic.views.lagerobjekt_aufnehmen.LagerobjektAufnehmenView;
import com.samic.samic.views.lagerobjekt_erfassen.LagerobjektErfassenView;
import com.samic.samic.views.meine_hardware.MeineHardwareView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility;
import java.util.Optional;
import org.vaadin.lineawesome.LineAwesomeIcon;

/** The main view is a top-level placeholder for other views. */
public class MainLayout extends AppLayout {

private H2 viewTitle;

private AuthenticatedUser authenticatedUser;
private AccessAnnotationChecker accessChecker;

public MainLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker) {
	this.authenticatedUser = authenticatedUser;
	this.accessChecker = accessChecker;

	setPrimarySection(Section.DRAWER);
	addDrawerContent();
	addHeaderContent();
}

private void addHeaderContent() {
	DrawerToggle toggle = new DrawerToggle();
	toggle.setAriaLabel("Menu toggle");

	viewTitle = new H2();
	viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

	Image logo = new Image("/images/logo_samic.svg", "Samic logo");
	logo.setHeight("34px");

	addToNavbar(true, toggle, viewTitle, logo);

	logo.getStyle().setFloat(Style.FloatCss.RIGHT);
	logo.getStyle().setPosition(Style.Position.ABSOLUTE);
	logo.getStyle().setRight("20px");
}

private void addDrawerContent() {
	H1 appName = new H1("");
	appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
	Header header = new Header(appName);

	Scroller scroller = new Scroller(createNavigation());

	addToDrawer(header, scroller, createFooter());
}

private SideNav createNavigation() {
	SideNav nav = new SideNav();

	if (accessChecker.hasAccess(DashboardView.class)) {
	nav.addItem(new SideNavItem("Dashboard", DashboardView.class, VaadinIcon.DASHBOARD.create()));
	}

	if (accessChecker.hasAccess(LagerobjektErfassenView.class)) {
	nav.addItem(
		new SideNavItem(
			"Lagerobjekt erfassen",
			LagerobjektErfassenView.class,
			LineAwesomeIcon.DASHCUBE.create()));
	}
	if (accessChecker.hasAccess(LagerobjektAufnehmenView.class))
		nav.addItem(
				new SideNavItem(
				"Lagerobjekt aufnehmen",
				LagerobjektAufnehmenView.class,
				LineAwesomeIcon.CART_ARROW_DOWN_SOLID.create()));
	if (accessChecker.hasAccess(FreieLagerobjekteView.class))
		nav.addItem(
				new SideNavItem(
				"Freie Lagerobjekte",
				FreieLagerobjekteView.class,
				LineAwesomeIcon.SEARCH_SOLID.create()));
	if (accessChecker.hasAccess(MeineHardwareView.class))
		nav.addItem(
				new SideNavItem(
				"Meine Hardware",
				MeineHardwareView.class,
				LineAwesomeIcon.STRIPE.create()));

	return nav;
}

private Footer createFooter() {
	Footer layout = new Footer();

	Optional<User> maybeUser = authenticatedUser.getUser();
	if (maybeUser.isPresent()) {
	User user = maybeUser.get();

	/* TODO remove if attribute gets deleted in backend
	Avatar avatar = new Avatar(user.getName());
	StreamResource resource = new StreamResource("profile-pic",
			() -> new ByteArrayInputStream(user.getProfilePicture()));
	avatar.setImageResource(resource);
	avatar.setThemeName("xsmall");
	avatar.getElement().setAttribute("tabindex", "-1");*/

	MenuBar userMenu = new MenuBar();
	userMenu.setThemeName("tertiary-inline contrast");

	MenuItem userName = userMenu.addItem("");
	Div div = new Div();
	// div.add(avatar);
	div.add(user.getProfile().getFirstName()); // habs hier ändern müssen. Kannst aber anpassen.
	div.add(new Icon("lumo", "dropdown"));
	div.getElement().getStyle().set("display", "flex");
	div.getElement().getStyle().set("align-items", "center");
	div.getElement().getStyle().set("gap", "var(--lumo-space-s)");
	userName.add(div);
	userName
		.getSubMenu()
		.addItem(
			"Sign out",
			e -> {
				authenticatedUser.logout();
			});

	layout.add(userMenu);
	} else {
	Anchor loginLink = new Anchor("login", "Sign in");
	layout.add(loginLink);
	}

	return layout;
}

@Override
protected void afterNavigation() {
	super.afterNavigation();
	viewTitle.setText(getCurrentPageTitle());
}

private String getCurrentPageTitle() {
	PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
	return title == null ? "" : title.value();
}
}
