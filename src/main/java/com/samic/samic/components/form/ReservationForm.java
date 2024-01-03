package com.samic.samic.components.form;

import com.samic.samic.data.entity.Customer;
import com.samic.samic.data.entity.Reservation;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
@Scope("prototype")
public class ReservationForm extends FormLayout {
    private final TextArea description = new TextArea("Beschreibung");
    private final IntegerField reservedFor = new IntegerField("Verbindungsnummer (Optional)");
    private final Binder<Reservation> reservationBinder = new Binder<>(Reservation.class);

    private Reservation reservation;
    @PostConstruct
    public void initUI() {
        description.setPlaceholder("Zusätzliche relevante Informationen zu meiner Reservierung ...");
        reservedFor.setRequired(false);
        add(description, reservedFor);
        initBinder();
    }

    private void initBinder() {
        reservationBinder.forField(description).bind(Reservation::getReservedDescription, Reservation::setReservedDescription);
        reservationBinder.bind(reservedFor, item -> item.getCustomer().connectionNo(), (item, value) -> item.setCustomer(
            Customer.builder().connectionNo(value).build()));
    }


    public void setBean(Reservation reservation) {
        this.reservation = reservation;
        reservationBinder.setBean(this.reservation);
    }

    public Reservation save() {
        return reservationBinder.getBean();
    }
}
