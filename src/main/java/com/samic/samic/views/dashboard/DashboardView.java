package com.samic.samic.views.dashboard;

import com.samic.samic.components.UIFactory;
import com.samic.samic.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@PageTitle("Dashboard")
@Route(value = "dashboard", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class DashboardView extends VerticalLayout {

public DashboardView() {
	initUI();
}

private void initUI() {
	initStats();
	initQuickAccess();
	initHardware();
	initReservation();
}

private void initStats() {
	// STATS - Container
	VerticalLayout statsContainer = UIFactory.rootComponentContainer("Statistiken");
	HorizontalLayout statsComponents = UIFactory.childContainer(JustifyContentMode.EVENLY);

	HashMap<String, List<Integer>> map = new HashMap<>();
	map.put("Gerät Typ1", List.of(100, 50));
	map.put("Gerät Typ2", List.of(50, 50));
	map.put("Gerät Typ3", List.of(100, 1));

	map.forEach(
		(key, value) -> {
		Span sp = new Span(key + " " + value.get(0) + "/" + value.get(1));
		if (value.get(0) / value.get(1) >= 25) {
			sp.getElement().getThemeList().add("badge error");
		} else if (value.get(0) / value.get(1) >= 2) {
			sp.getElement().getThemeList().add("badge contrast");
		} else {
			sp.getElement().getThemeList().add("badge success");
		}
		statsComponents.add(sp);
		});
	statsContainer.add(statsComponents);
	add(statsContainer);
}

private void initQuickAccess() {
	// CSS porperties
	HashMap<String, String> cssValues = new HashMap<>(Map.of("flex", "1 250px"));
	// QUICK_ACCESS - PUT TOGETHER UI
	add(
		UIFactory.rootComponentContainer(
			"Quick-Access",
			UIFactory.childContainer(
				JustifyContentMode.BETWEEN,
				UIFactory.btnPrimary("Lagerobjekt erfassen", cssValues),
				UIFactory.btnPrimary("Reservierung hinzufügen", cssValues),
				UIFactory.btnPrimary("Lagerobjekt aufnehmen", cssValues),
				UIFactory.btnPrimary("Lager hinzufügen", cssValues),
				UIFactory.btnPrimary("Benutzer hinzufügen", cssValues))));
}

private void initHardware() {
	// MY_HARDWARE - PREPARE GRID
	Grid<ExampleData> hardwareGrid = new Grid<>(ExampleData.class, true);
	hardwareGrid.isAllRowsVisible();
	hardwareGrid.setMaxHeight("300px");
	hardwareGrid.setItems(initData());
	// MY_HARDWARE - PUT UI TOGETHER
	add(UIFactory.rootComponentContainer("Meine Hardware", hardwareGrid));
}

private void initReservation() {
	// MY_RESERVATIONS
	Grid<ExampleData> reservationGrid = new Grid<>(ExampleData.class, true);
	reservationGrid.isAllRowsVisible();
	reservationGrid.setMaxHeight("300px");
	reservationGrid.setItems(initData());
	// MY RESERVATION - PUT UI TOGETHER
	add(UIFactory.rootComponentContainer("Meine Reservierungen", reservationGrid));
}

private List<ExampleData> initData() {
	// PREPARE DATA FOR HARDWARE
	// TODO use Service Method for Data and remove sub Class
	List<ExampleData> exampleDataList = new ArrayList<>();

	exampleDataList.add(
		ExampleData.builder()
			.name("Gerät 1")
			.type("Typ 1")
			.status("Status 1")
			.location("Lagerort 1")
			.reserved("Reserviert 1")
			.reservedUntil("Reserviert bis 1")
			.build());
	exampleDataList.add(
		ExampleData.builder()
			.name("Gerät 2")
			.type("Typ 2")
			.status("Status 2")
			.location("Lagerort 2")
			.reserved("Reserviert 2")
			.reservedUntil("Reserviert bis 2")
			.build());
	exampleDataList.add(
		ExampleData.builder()
			.name("Gerät 3")
			.type("Typ 3")
			.status("Status 3")
			.location("Lagerort 3")
			.reserved("Reserviert 3")
			.reservedUntil("Reserviert bis 3")
			.build());
	exampleDataList.add(
		ExampleData.builder()
			.name("Gerät 4")
			.type("Typ 4")
			.status("Status 4")
			.location("Lagerort 4")
			.reserved("Reserviert 4")
			.reservedUntil("Reserviert bis 4")
			.build());
	exampleDataList.add(
		ExampleData.builder()
			.name("Gerät 5")
			.type("Typ 5")
			.status("Status 5")
			.location("Lagerort 5")
			.reserved("Reserviert 5")
			.reservedUntil("Reserviert bis 5")
			.build());
	return exampleDataList;
}

@Builder
@Getter
@Setter
@AllArgsConstructor
protected static class ExampleData {
	private String name;
	private String type;
	private String status;
	private String location;
	private String reserved;
	private String reservedUntil;
}
}
