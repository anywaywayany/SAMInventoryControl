package com.samic.samic.data.entity;

import com.samic.samic.data.constants.ConstantsDomain;
import com.samic.samic.exceptions.SamicException;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@Table(name = "reservations")
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class Reservation extends AbstractIdentityClass<Long>{

    /*
    relations
     */
    @OneToOne(mappedBy = "reservation",
              fetch = FetchType.EAGER) //Shared Primary Key
    private StorageObject storageObject;

    @OneToMany(mappedBy = "reservation",
               fetch = FetchType.LAZY,
               cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<StorageObjectHistory> storageObjectHistory = new ArrayList<>();

    @JoinColumn(name = "fk_user",
                foreignKey = @ForeignKey(name = "fk_user_2_reservation"))
    @ManyToOne(fetch = FetchType.EAGER,
               cascade = {CascadeType.MERGE})
    private User reservedFrom;

    /*
    attributes
    */
    @Embedded
    @Column(name = "reserved_For")
    private Customer      customer;
    @PastOrPresent
    private LocalDateTime reservedAt;

    @Column(name = "reserved_description",
            length = ConstantsDomain.DEFAULT_LENGTH)
    private String reservedDescription;


    @Column(name = "completed")
    private Boolean completed;

    @Column(name = "last_modified")
    private LocalDateTime lastModified;


//    public void setReservedFrom(User reservedFrom){
//        if(reservedFrom != null){
//            if(this.getReservedFrom() == null){
//                this.reservedFrom = reservedFrom;
//            }else{
//                throw new SamicException("Reservation has already a User!");
//            }
//        }else{
//            throw new SamicException("Given User is null!");
//        }
//    }
//
//    public void setStorageObject(StorageObject storageObject){
//        if(storageObject != null){
//            if(this.getStorageObject() == null){
//                this.storageObject = storageObject;
//            }else{
//                throw new SamicException("Reservation has already set!");
//            }
//        }else{
//            throw new SamicException("Given StorageObject is null!");
//        }
//    }
//
//
//    public void setReservedAt(LocalDateTime reservedAt){
//        if(reservedAt != null){
//            if(this.getReservedAt() != null){
//                this.reservedAt = reservedAt;
//            }else{
//                throw new SamicException("Reservation Date has been already set!");
//            }
//        }
//    }

    @Override
    public int hashCode() {
        if (getId() != null) {
            return getId().hashCode();
        }
        return super.hashCode();
    }


    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Reservation:\n")
               //                .append("storageObject=")
               //                .append(storageObject)
               //                .append('\'')
               //               .append("reservedFrom=")
               //               .append(reservedFrom)
               //               .append('\'')
               //               .append("customer=")
               //               .append(customer)
               //               .append('\'')
               //               .append("reservedAt=")
               //               .append(reservedAt)
               //               .append('\'')
               .append("reservedDescription='")
               .append(reservedDescription)
               .append("\n")
               .append("completed=")
               .append((completed != null)?builder.append(completed):builder.append("not set"));
        //               .append('\'')
        //               .append("lastModified=").append(lastModified);
        return builder.toString();
    }
}


