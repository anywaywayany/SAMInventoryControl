package com.samic.samic.data.entity;


import com.samic.samic.data.constants.ConstantsDomain;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "storage_objects")
public class StorageObject extends AbstractIdentityClass<Long>{

    /*
    relations
     */
    @OneToOne(mappedBy = "storageObject", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "object_type", foreignKey = @ForeignKey(name = "fk_objectType_2_storageObject"))
    @NotNull
    private ObjectType objectTypeName;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}) //Shared Primary Key
    @JoinColumn(name = "fk_reservation"/*, referencedColumnName = "id"*/,foreignKey = @ForeignKey(name = "fk_reservation_2_storageObject"))    //foreignKey should be named only reservation
    private Reservation reservation;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "fk_CPE", foreignKey = @ForeignKey(name = "fk_cpe_2_storageObject"))
    private CPE cpe;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE} ) //Viele StorageObj. die auf einen SFP zeigen.
    @JoinColumn(name = "SFP", foreignKey = @ForeignKey(name = "fk_sfp_2_storageObject"))
    private SFP sfp;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumn(name = "supply", foreignKey = @ForeignKey(name = "fk_supply_2_storageObject"))
    private Supply supply;

    @OneToMany(mappedBy = "storageObject", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<StorageObjectHistory> storageObjectHistory;

    @Embedded
    @Column(name = "stored_at_customer")
    private Customer storedAtCustomer;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "fk_storaed_at_user", foreignKey = @ForeignKey(name = "fk_User_2_storageObject"))
    private User storedAtUser;

    //Einseitige Beziehung
    @ManyToOne(targetEntity = Storage.class,fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "fk_storageObject", foreignKey = @ForeignKey(name = "fk_storage_2_storageObject"))
    private Storage storage;

    /*
    attributes
     */
    @Column(name = "remark", length = ConstantsDomain.DEFAULT_LENGTH)
    //    @NotBlank
    private String remark;

    @Enumerated(EnumType.STRING)
    @Column(name = "status"/*, length = 1*//*,columnDefinition = "CHAR(1)CHECK (Status IN ('C','R','M','P','A'))"*/)
    private Status status;

    //    @Column(name = "object_name", length = ConstantsDomain.DEFAULT_LENGTH)
    //    private @NotBlank String name;


    @Column(name = "project_device")
    private Boolean projectDevice;


}

