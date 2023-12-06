package com.samic.samic.data.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "storages")
public class Storage extends AbstractPersistable<Long>{

    /*
    relations
    */
    @OneToMany(mappedBy ="storage", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "storage_objects")
    private List<StorageObject> storageObject = new ArrayList<>();

    @Column(name = "storage_Objekt_history")
    @OneToMany(mappedBy = "storage", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<StorageObjectHistory> storageObjectHistory;

    /*
    attributes
     */
    @Embedded
    private @Valid Address address;

    @Column(name = "storage_name")
    private String name;

//
//    public Storage addAddress(Address storageAddress){
//        return this;
//    }
//
//    public Storage clearAddres(){
//        address = null;
//        return this;
//    }
//
//    public void setAddress(Address address){
//        this.address = address;
//    }
}
