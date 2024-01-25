package com.samic.samic.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends AbstractIdentityClass<Long>{


    /*
    relations
     */
    @OneToMany(mappedBy = "storedAtUser", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    //        @JoinColumn(name = "fk_storageObject")
    private List<StorageObject> storageObject = new ArrayList<>();

    //    @OneToOne(targetEntity = StorageObject.class,fetch = FetchType.LAZY)
    //    @JoinColumn(name = "storage_object" )
    //    private StorageObject storageObject;


    @OneToMany(mappedBy = "reservedFrom", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE ,CascadeType.PERSIST})
    private List<Reservation> reservation = new ArrayList<>();


    //    @JsonIgnore
    /* @NotBlank*/
    //    @Column(name = "password")
    //    private String password;

    /*
    attributes
     */
    @Embedded
    private Profile profile;

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
    @Column(name = "email")
    private String mail;

    @JsonIgnore
    @Column(name = "hashed_password")
    private String hashedPassword;

    //    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    //    private ApplicationUser applicationUser;


    //    @OneToOne
    //    @JoinColumn(foreignKey = @ForeignKey(name = "FK_User_2_Profile"))


}
