package com.samic.samic.data.entity;


import com.samic.samic.data.constants.ConstantsDomain;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class Profile /*extends AbstractPersistable<Long>*/{ //extend hier entferenen weil eine entity nicht
    // lgeichzeitig ein embaddable sein kann!!!


    @Column(name = "user_name", length = ConstantsDomain.DEFAULT_LENGTH)
    @NotBlank
    private  String username;

    @Column(name = "first_name", length = ConstantsDomain.DEFAULT_LENGTH)
    private @NotBlank String firstName;

    @Column(name = "last_name", length = ConstantsDomain.DEFAULT_LENGTH)
    private @NotBlank String lastName;

    @Column(name = "phone")
    private @NotBlank String phone;


}
