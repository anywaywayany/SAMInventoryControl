package com.samic.samic.data.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "storages")
public class Storage extends AbstractPersistable<Long>{


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "fk_storage_objects")
    private StorageObject storageObject;


    @Embedded
    private @Valid Address address;

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
