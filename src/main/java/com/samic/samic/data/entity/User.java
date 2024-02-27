package com.samic.samic.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends AbstractIdentityClass<Long> {


  /*
  relations
   */
  @OneToMany(mappedBy = "storedAtUser",
      fetch = FetchType.LAZY,
      cascade = {CascadeType.PERSIST})
  //        @JoinColumn(name = "fk_storageObject")
  private List<StorageObject> storageObject = new ArrayList<>();

  //    @OneToOne(targetEntity = StorageObject.class,fetch = FetchType.LAZY)
  //    @JoinColumn(name = "storage_object" )
  //    private StorageObject storageObject;


  @OneToMany(mappedBy = "reservedFrom",
      fetch = FetchType.LAZY,
      cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
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

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("User:\n")
//               .append("profile=")
//               .append(profile.getLastName())
        .append("\n")
        .append("role=")
        .append(role.getLongVersion())
//               .append('\'')
//               .append("createdAt=")
//               .append(createdAt)
//               .append('\'')
//               .append("lastLogin=")
//               .append(lastLogin)
//               .append('\'')
        .append("activated=")
        .append(activated)
        .append("\n")
        .append("mail='")
        .append(mail)
        .append("\n");
    return builder.toString();
  }

}
