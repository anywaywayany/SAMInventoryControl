package com.samic.samic.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Builder
@Entity
@NoArgsConstructor
@Table(name = "supply")
public class Supply extends AbstractPersistable<Long>{
}
