package com.mercadolibre.quasar.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SATELLITE")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SatelliteEntity {

    @Id
    private String name;

    @Column
    private float distance;

    @Column
    private String[] message;
}
