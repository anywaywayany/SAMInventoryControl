package com.samic.samic.data.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "storages")

public class Storage extends AbstractPersistable<Long>{


    @OneToMany(mappedBy ="storage", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "storage_objects")
    private List<StorageObject> storageObject = new ArrayList<>();

    @Column(name = "storage_Onjekt_history")
    @OneToMany(mappedBy = "storage", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<StorageObjectHistory> storageObjectHistory;

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
