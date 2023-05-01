package com.charge.charge.services;

import com.charge.charge.entities.Charge;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ChargeService {
    List<Charge> getAllCharges();
    Charge getChargeById(int id) throws ChangeSetPersister.NotFoundException;
    Charge createCharge(Charge charge);
    Charge updateCharge(Charge charge);
    void deleteCharge(int id);
}
