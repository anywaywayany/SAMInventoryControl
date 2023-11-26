package com.samic.samic.views.dashboard;

import com.samic.samic.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import lombok.Builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@PageTitle("Dashboard")
@Route(value = "dashboard", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class DashboardView extends VerticalLayout {

    public DashboardView() {

        //STATS
        VerticalLayout statsContainer = new VerticalLayout();

        statsContainer.add(new H4("Statistiken"));
        statsContainer.addClassName("container");
        add(statsContainer);

        HorizontalLayout statsChildren = new HorizontalLayout();
        statsContainer.add(statsChildren);

        statsChildren.setJustifyContentMode(JustifyContentMode.BETWEEN);
        statsChildren.setWidthFull();
        HashMap<String, List<Integer>> map = new HashMap<>();


        //Example Data
        map.put("Gerät Typ1", List.of(100, 50));
        map.put("Gerät Typ2", List.of(50, 50));
        map.put("Gerät Typ3", List.of(100, 1));

        map.forEach((key, value) -> {
            Span sp = new Span(key + " "+ value.get(0) + "/" + value.get(1));
            if (value.get(0) / value.get(1) >= 25) {
                sp.getElement().getThemeList().add("badge error");
            } else if(value.get(0) / value.get(1) >= 2 ) {
                sp.getElement().getThemeList().add("badge contrast");
            } else {
                sp.getElement().getThemeList().add("badge success");
            }
            statsChildren.add(sp);
        });
        statsChildren.add(new Span("Empty example"));
        statsChildren.add(new Span("Empty example"));
        statsChildren.add(new Span("Empty example"));


        //QUICK_ACCESS


        VerticalLayout actionContainer = new VerticalLayout();
        actionContainer.add(new H4("Quick-Access"));
        actionContainer.addClassName("container");
        add(actionContainer);

        HorizontalLayout actionChildren = new HorizontalLayout();
        actionChildren.setWidthFull();
        actionChildren.setJustifyContentMode(JustifyContentMode.AROUND);

        actionContainer.add(actionChildren);


        Button btnSoCreate = new Button("Lagerobjekt erfassen");
        btnSoCreate.getStyle().setBackground("#108AB2");
        btnSoCreate.getStyle().setColor("#FFFFFF");

        Button btnResAdd = new Button("Reservierung hinzufügen");
        btnResAdd.getStyle().setBackground("#108AB2");
        btnResAdd.getStyle().setColor("#FFFFFF");

        Button btnSoAdd = new Button("Lagerobjekt aufnehmen");
        btnSoAdd.getStyle().setBackground("#108AB2");
        btnSoAdd.getStyle().setColor("#FFFFFF");

        Button btnStadd = new Button("Lager hinzufügen");
        btnStadd.getStyle().setBackground("#108AB2");
        btnStadd.getStyle().setColor("#FFFFFF");

        Button btnUserAdd = new Button("Benutzer hinzufügen");
        btnUserAdd.getStyle().setBackground("#108AB2");
        btnUserAdd.getStyle().setColor("#FFFFFF");

        actionChildren.add(btnSoCreate,btnResAdd, btnSoAdd, btnStadd, btnUserAdd);



        //MY_HARDWARE

        VerticalLayout myHardwareContainer = new VerticalLayout();
        myHardwareContainer.add(new H4("Meine Hardware (Fake Überschriften im Grid!!)"));
        myHardwareContainer.addClassName("container");
        add(myHardwareContainer);

        HorizontalLayout myHardwareChildren = new HorizontalLayout();
        myHardwareChildren.setWidthFull();


        Grid<ExampleData> hardwareGrid = new Grid<>(ExampleData.class,false);

        hardwareGrid.isAllRowsVisible();
        hardwareGrid.setMaxHeight("300px");



        List<ExampleData> exampleDataList = new ArrayList<>();

        exampleDataList.add(ExampleData.builder().name("Gerät 1").type("Typ 1")
                .status("Status 1").location("Lagerort 1").reserved("Reserviert 1").reservedUntil("Reserviert bis 1").build());
        exampleDataList.add(ExampleData.builder().name("Gerät 2").type("Typ 2")
                .status("Status 2").location("Lagerort 2").reserved("Reserviert 2").reservedUntil("Reserviert bis 2").build());
        exampleDataList.add(ExampleData.builder().name("Gerät 3").type("Typ 3")
                .status("Status 3").location("Lagerort 3").reserved("Reserviert 3").reservedUntil("Reserviert bis 3").build());
        exampleDataList.add(ExampleData.builder().name("Gerät 4").type("Typ 4")
                .status("Status 4").location("Lagerort 4").reserved("Reserviert 4").reservedUntil("Reserviert bis 4").build());
        exampleDataList.add(ExampleData.builder().name("Gerät 5").type("Typ 5")
                .status("Status 5").location("Lagerort 5").reserved("Reserviert 5").reservedUntil("Reserviert bis 5").build());


        hardwareGrid.addColumn(ExampleData::getName).setHeader("Name");
        hardwareGrid.addColumn(ExampleData::getType).setHeader("Typ");
        hardwareGrid.addColumn(ExampleData::getStatus).setHeader("Status");
        hardwareGrid.addColumn(ExampleData::getLocation).setHeader("Lagerort");
        hardwareGrid.addColumn(ExampleData::getReserved).setHeader("Reserviert");
        hardwareGrid.addColumn(ExampleData::getReservedUntil).setHeader("Reserviert bis");

        hardwareGrid.setItems(exampleDataList);

        myHardwareContainer.add(myHardwareChildren);
        myHardwareChildren.add(hardwareGrid);


        //MY_RESERVATIONS

        VerticalLayout myReservationContainer = new VerticalLayout();
        myReservationContainer.add(new H4("Meine Reservierungen (Fake Überschriften im Grid!!)"));
        myReservationContainer.addClassName("container");
        add(myReservationContainer);

        HorizontalLayout myReservationChildren = new HorizontalLayout();
        myReservationChildren.setWidthFull();


        Grid<ExampleData> reservationGrid = new Grid<>(ExampleData.class,false);

        reservationGrid.isAllRowsVisible();
        reservationGrid.setMaxHeight("300px");



        reservationGrid.addColumn(ExampleData::getName).setHeader("Name");
        reservationGrid.addColumn(ExampleData::getType).setHeader("Typ");
        reservationGrid.addColumn(ExampleData::getStatus).setHeader("Status");
        reservationGrid.addColumn(ExampleData::getLocation).setHeader("Lagerort");
        reservationGrid.addColumn(ExampleData::getReserved).setHeader("Reserviert");
        reservationGrid.addColumn(ExampleData::getReservedUntil).setHeader("Reserviert bis");

        reservationGrid.setItems(exampleDataList);

        myReservationContainer.add(myReservationChildren);
        myReservationChildren.add(reservationGrid);


    }



    @Builder
    static protected class ExampleData{
        private String name;
        private String type;
        private String status;
        private String location;
        private String reserved;
        private String reservedUntil;

        public ExampleData exampleData(String name, String type, String status, String location, String reserved, String reservedUntil) {
            this.name = name;
            this.type = type;
            this.status = status;
            this.location = location;
            this.reserved = reserved;
            this.reservedUntil = reservedUntil;

            return this;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getStatus() {
            return status;
        }

        public String getLocation() {
            return location;
        }

        public String getReserved() {
            return reserved;
        }

        public String getReservedUntil() {
            return reservedUntil;
        }
    }
}
