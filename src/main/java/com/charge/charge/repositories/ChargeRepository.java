package com.charge.charge.repositories;

import com.charge.charge.entities.Charge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargeRepository extends JpaRepository<Charge, Integer> {
    // Méthodes pour récupérer, créer, modifier et supprimer des charges
}

