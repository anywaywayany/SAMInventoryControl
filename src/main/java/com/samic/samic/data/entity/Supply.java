package com.samic.samic.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "supplies")
public class Supply extends AbstractIdentityClass<Long>{

    /*
    relations
     */
    @Column(name = "supply")
    @OneToMany(mappedBy = "supply", fetch = FetchType.LAZY, cascade ={CascadeType.PERSIST, CascadeType.MERGE})
    private List<StorageObject> storageObject = new ArrayList<>();

    /*
    attributes
     */
    @Column(name = "description")
    @NotBlank
    private String description;

    @Column(name = "amount")
    @Positive
    private int amount;
}
