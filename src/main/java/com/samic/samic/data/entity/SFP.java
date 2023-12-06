package com.samic.samic.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sfps")
public class SFP extends AbstractPersistable<Long>{

    /*
    relations
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "fk_producer", foreignKey = @ForeignKey(name = "fk_producer_2_sfp"))
    private Producer producer;

    /*
    attributes
     */
    @NotBlank
    @Column(name = "wavelength")
    private String wavelength;

    @Min(0)
    @Column(name = "nic_speed")
    private Integer nicSpeed;

    @NotBlank
    @Column(name = "serialnumber")
    private String serialnumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @Enumerated
    @Column(name = "type_SFP")
    private SFPType sfpType;


}
