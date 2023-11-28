package com.samic.samic.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_storage")
public class User extends AbstractPersistable<Long>{



    @OneToMany(mappedBy = "storedAtUser", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//        @JoinColumn(name = "fk_storageObject")
    private List<StorageObject> storageObject = new ArrayList<>();

    //    @OneToOne(targetEntity = StorageObject.class,fetch = FetchType.LAZY)
    //    @JoinColumn(name = "storage_object" )
    //    private StorageObject storageObject;


    @OneToOne(mappedBy = "reservedFrom", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Reservation reservation;

    @Embedded
    private Profile profile;

    @JsonIgnore
    @NotBlank
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @PastOrPresent
    @Column(name = "created_At")
    private LocalDateTime createdAt;

    @PastOrPresent
    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "activated")
    private Boolean activated;

    @Email
    private String mail;

    @JsonIgnore
    private String hashedPassword;

//    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    private ApplicationUser applicationUser;


    //    @OneToOne
    //    @JoinColumn(foreignKey = @ForeignKey(name = "FK_User_2_Profile"))


}
