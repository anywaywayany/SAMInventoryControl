package com.samic.samic.data.entity;

import com.samic.samic.data.constants.ConstantsDomain;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cpes")
public class CPE extends AbstractPersistable<Long>{

//    @ManyToOne
////    @JoinColumn(name = "cpe")
//    private StorageObject storageObject;

    @Column(name = "storageObject")
    @OneToMany(mappedBy = "cpe", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<StorageObject> storageObject = new ArrayList<>();

    @Column(name = "serialnumber")
    private @NotBlank String serialnumber;

    @Column(name = "macaddress", length = ConstantsDomain.MAC_LENGTH_WITH_HYPHEN)
    private @NotBlank String macAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "fk_producer", foreignKey = @ForeignKey(name = "fk_producer_2_cpe"))
    private Producer producer;
}
