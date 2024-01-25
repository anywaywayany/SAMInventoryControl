package com.samic.samic.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "producers")
public class Producer extends AbstractIdentityClass<Long>{

    /*
    relations
     */
    @OneToMany(mappedBy = "producer", fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<SFP> sfp = new ArrayList<>();

    @OneToMany(mappedBy = "producer", fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<CPE> cpe = new ArrayList<>();

    /*
    attributes
     */
    @Column(name="short_name")
    private String shortname;

    @Column(name = "producer_name")
    String name;

      @Override
      public String toString(){
          StringBuilder builder = new StringBuilder();
          builder.append("Producer:\n").append("shortname='").append(shortname).append('\'').append("name='").append(name);
          return builder.toString();
      }

}
