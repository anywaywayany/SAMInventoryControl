package com.samic.samic.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StorageObjectHistory extends AbstractPersistable<Long>{

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "fk_storageObject_history", foreignKey = @ForeignKey(name = "fk_storageObject_2_storageObjectHistory"))
    private StorageObject storageObject;

    @Column(name = "inser_date_time")
    @PastOrPresent
    private LocalDateTime insertDateTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "until_Date_Time")
    @PastOrPresent
    private LocalDateTime untilDateTime;

    @JoinColumn(name = "fk_storage", foreignKey = @ForeignKey(name= "fk_storage_2_storageObjectHistory"))
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Storage storage;

    @JoinColumn(name = "fk_reservation", foreignKey = @ForeignKey(name = "fk_reservation_2_storageObjectHistory"))
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Reservation reservation;
}
