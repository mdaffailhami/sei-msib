package com.mdi.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdi.api.models.Proyek;

public interface ProyekRepository extends JpaRepository<Proyek, Integer> {
    // crud
}
