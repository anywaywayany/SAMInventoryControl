package com.samic.samic.data.entity;


import com.samic.samic.data.constants.ConstantsDomain;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

//@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class Address{

    @Column(name = "street", length = ConstantsDomain.OBJECTNAME_LENGTH)
    @NotBlank
    private String street;

    @Column(name = "house_no")
    @Min(1)
    @Max(1000)
    private Integer houseNo;

    @Column(name = "door_no")
    @Positive()
    @Min(1)
    @Max(1000)
    private Integer doorNo;

    @Column(name = "city", length = ConstantsDomain.OBJECTNAME_LENGTH)
    private String city;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_address_to_storage"))
    private Storage storage;

    //
    //    public Integer getHouseNo(){
    //        return houseNo;
    //    }
    //
    //    public void setHouseNo(Integer houseNo){
    //
    //            if(houseNo > 0 && houseNo < 1000){
    //                this.houseNo = houseNo;
    //            }else{
    //                throw new DomainException(houseNo < 0 ? "HouseNo is less than 0": "HouseNo is bigger than 1000");
    //            }
    //
    //
    //    }
    //
    //    public Integer getDoorNo(){
    //        return doorNo;
    //    }
    //
    //    public void setDoorNo(Integer doorNo){
    //        if(doorNo > 0 && doorNo < 1000){
    //            this.doorNo = doorNo;
    //        }
    //    }
}
