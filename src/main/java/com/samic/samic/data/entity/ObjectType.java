package com.samic.samic.data.entity;

import com.samic.samic.data.constants.ConstantsDomain;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "objectTypeName", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private List<StorageObject> storageObject = new ArrayList<>();


    /*
    attributes
     */
    @Column(name="object_Type_name", length = ConstantsDomain.DEFAULT_LENGTH)
    private String name;
}
