package com.ulger.guava.parceldeliveryservice.api.parcel.data;

import com.ulger.guava.parceldeliveryservice.api.parcel.Status;
import com.ulger.guava.parceldeliveryservice.api.parcel.data.converter.StatusConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "parcel", indexes = @Index(name = "idx_1_unq_barcode", unique = true, columnList = "barcode"))
public class ParcelEntity {

    @Id
    @GeneratedValue(generator = "seq_gen_parcel_delivery", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_gen_parcel_delivery", sequenceName = "seq_gen_parcel_delivery", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false, precision = 10)
    private Long id;

    @Column(name = "owner_user_id", nullable = false, length = 64)
    private Long ownerUserId;

    @Column(name = "barcode", unique = true, nullable = false, length = 64)
    private String barcode;

    @Column(name = "weight_in_grams", nullable = false)
    private long weightInGrams;

    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @Convert(converter = StatusConverter.class)
    @Column(name = "status", nullable = false)
    private Status status;

}