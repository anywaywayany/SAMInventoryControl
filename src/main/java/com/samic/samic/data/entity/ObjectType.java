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
@Table(name = "object_type")
public class ObjectType extends AbstractPersistable<Long>{

    /*
    relations
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private StorageObject storageObject;

    /*
    attributes
     */
    @Column(length = ConstantsDomain.DEFAULT_LENGTH)
    @NotBlank
    private String name;
}
