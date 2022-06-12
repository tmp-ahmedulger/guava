package com.ulger.guava.parceldeliveryservice.api.courier.data;

import javax.persistence.*;

@Entity
@Table(name = "courier", indexes = @Index(name = "idx_1_unq_barcode", unique = true, columnList = "phone_number"))
public class CourierEntity {

    @Id
    @GeneratedValue(generator = "seq_gen_courier", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_gen_courier", sequenceName = "seq_gen_courier", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false, precision = 10)
    private Long id;

    @Column(name = "display_name", nullable = false, length = 64)
    private String displayName;

    @Column(name = "phone_number", unique = true, nullable = false, length = 16)
    private String phoneNumber;
}