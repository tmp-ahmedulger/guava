package com.ulger.guava.parceldeliveryservice.api.parcel.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelRepository extends JpaRepository<ParcelEntity, Long> {

    @Query("update ParcelEntity p set p.deliveryAddress = :deliveryAddress WHERE p.id = :parcelId")
    int updateDeliveryAddress(@Param("parcelId") long parcelId, @Param("deliveryAddress") String deliveryAddress);
}