package com.samic.samic.data.entity;

import com.samic.samic.data.constants.ConstantsDomain;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

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
    @OneToMany(mappedBy = "objectTypeName",
               fetch = FetchType.LAZY,
               cascade = {CascadeType.PERSIST})
    private List<StorageObject> storageObject = new ArrayList<>();


    /*
    attributes
     */
    @Column(name = "object_Type_name",
            length = ConstantsDomain.DEFAULT_LENGTH)
    private String name;

    @Column(name = "min_value")
    @Positive
    private Long minValue;

    @Column(name = "max_amount")
    @Positive
    private Long maxAmount;

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        builder.append(name);
        return builder.toString();
    }
}
