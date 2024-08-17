package com.mdi.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdi.api.models.Lokasi;

public interface LokasiRepository extends JpaRepository<Lokasi, Integer> {
    // crud
}
