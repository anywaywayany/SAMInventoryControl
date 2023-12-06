package com.samic.samic.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "producer")
public class Producer extends AbstractPersistable<Long>{

    /*
    relations
     */
    @OneToMany(mappedBy = "producer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<SFP> sfp;

    @OneToMany(mappedBy = "producer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<CPE> cpe;

    /*
    attributes
     */
    @Column(name="short_name")
    private String shortname;

    @Column(name = "producer_name")
    String name;

}
