package com.ulger.guava.parceldeliveryservice.api.parcel.data;

import com.ulger.guava.parceldeliveryservice.api.parcel.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelRepository extends JpaRepository<ParcelEntity, Long> {

    @Modifying
    @Query("update ParcelEntity p set p.deliveryAddress = :deliveryAddress WHERE p.id = :parcelId")
    int updateDeliveryAddress(@Param("parcelId") long parcelId, @Param("deliveryAddress") String deliveryAddress);

    @Modifying
    @Query("update ParcelEntity p set p.status = :status WHERE p.id = :parcelId")
    int updateStatus(@Param("parcelId") long parcelId, @Param("status") Status status);
}