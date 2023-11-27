package com.samic.samic.data.entity;

import com.samic.samic.data.constants.ConstantsDomain;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDateTime;

@Builder
@Entity
@Table(name = "reservations")
@NoArgsConstructor
@AllArgsConstructor
public class Reservation extends AbstractPersistable<Long>{

    @OneToOne(mappedBy = "reservation",fetch = FetchType.LAZY)
    private StorageObject storageObject;

    @PastOrPresent
    private LocalDateTime reservedAt;

    @Column(name = "reserved_description", length = ConstantsDomain.DEFAULT_LENGTH)
    private String reservedDescription;

    @JoinColumn(name = "fk_user")
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private User reservedFrom;

    @Embedded
    @Column(name="reserved_For")
    private Customer customer;

    @Column(name="completed")
    private Boolean completed;

    @Column(name = "last_modified")
    private LocalDateTime lastModified;

}
