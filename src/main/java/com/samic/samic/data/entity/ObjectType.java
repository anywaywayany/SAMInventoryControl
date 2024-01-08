package com.samic.samic.data.entity;

import com.samic.samic.data.constants.ConstantsDomain;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "object_types")
public class ObjectType extends AbstractIdentityClass<Long>{

    /*
    relations
     */
    @OneToOne(mappedBy = "objectTypeName", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private StorageObject storageObject;


    /*
    attributes
     */
    @Column(name="objectType_name", length = ConstantsDomain.DEFAULT_LENGTH)
    private String name;
}
