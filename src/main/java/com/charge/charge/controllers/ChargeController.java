package com.charge.charge.controllers;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.ui.Model;
import com.charge.charge.entities.Famille;
import com.charge.charge.services.ChargeService;
import com.charge.charge.services.FamilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.charge.charge.entities.Charge;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class ChargeController {
    @Autowired
    ChargeService chargeService;
    @Autowired
    FamilleService familleService;
    @RequestMapping("/createCharge")
    public String createCharge(Model model) {
        List<Famille> familleList = familleService.getAllFamilles();
        model.addAttribute("familleList", familleList);
        model.addAttribute("charge", new Charge());
        return "CreateCharge";
    }

    @RequestMapping("/saveCharge")
    public String saveCharge(@ModelAttribute("charge") Charge charge, @RequestParam("familleId") int familleId) throws ChangeSetPersister.NotFoundException {
        Famille famille = familleService.getFamilleById(familleId);
        charge.setFamille(famille);
        Charge memo = chargeService.saveCharge(charge);
        return "CreateCharge";
    }
}
