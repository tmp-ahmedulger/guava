package com.ulger.guava.parceldeliveryservice.api.parcel.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelRepository extends JpaRepository<ParcelEntity, Long> {


}