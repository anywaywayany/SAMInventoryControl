package com.samic.samic.data.entity;


import com.samic.samic.data.constants.ConstantsDomain;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "storage_objects")
public class StorageObject extends AbstractPersistable<Long>{

    @Column(name = "object_name", length = ConstantsDomain.DEFAULT_LENGTH)
    private @NotBlank String name;

    @Column(name = "project_device")
    private Boolean projectDevice;

    @Embedded
    @Column(name = "stored_at_customer")
    private Customer storedAtCustomer;

    @Column(name = "remark", length = ConstantsDomain.DEFAULT_LENGTH)
    private @NotBlank String remark;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumn(name = "fk_stored_at_user")
    private User storedAtUser;

    //Einseitige Beziehung
   /* @OneToMany(targetEntity = Storage.class,fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "storage")
    private Storage storage;*/

    @Enumerated(EnumType.STRING)
    @Column(name = "status"/*, length = 1*//*,columnDefinition = "CHAR(1)CHECK (Status IN ('C','R','M','P','A'))"*/)
    private Status status;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "fk_reservation")    //foreignKey should be named only reservation
    private Reservation reservation;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "cpe")
//    private CPE /*Set<CPE>*/ cpe;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "CPE")
//    @OneToMany
    private CPE cpe;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE} ) //Viele StorageObj. die auf einen SFP zeigen.
    @JoinColumn(name = "SFP")
    private SFP sfp;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumn(name = "supply")
    private Supply supply;

}
