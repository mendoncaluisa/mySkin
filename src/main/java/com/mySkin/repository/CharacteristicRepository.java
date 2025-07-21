package com.mySkin.repository;

import com.mySkin.entities.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
}
