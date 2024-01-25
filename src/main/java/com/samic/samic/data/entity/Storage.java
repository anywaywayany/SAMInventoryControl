package com.samic.samic.data.entity;

import com.samic.samic.data.constants.ConstantsDomain;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.annotations.ValueGenerationType;
import org.hibernate.id.IdentityGenerator;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "storages")
public class Storage extends AbstractIdentityClass<Long>{


    /*
    relations
    */
    @OneToMany(mappedBy ="storage", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private List<StorageObject> storageObject = new ArrayList<>();

    @Column(name = "storage_Objekt_history")
    @OneToMany(mappedBy = "storage", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private List<StorageObjectHistory> storageObjectHistory;

    /*
    attributes
     */
    @Embedded
    private Address address;

    @Column(name = "storage_name", length = ConstantsDomain.OBJECTNAME_LENGTH)
    @NotBlank
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

      @Override
      public String toString(){
          StringBuilder builder = new StringBuilder();
          builder.append("Storage:\n").append("address=").append(address).append('\'').append("name='").append(name);
          return builder.toString();
      }
}
