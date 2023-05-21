package com.charge.charge.controllers;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.ui.Model;
import com.charge.charge.entities.Famille;
import com.charge.charge.services.ChargeService;
import com.charge.charge.services.FamilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    @RequestMapping("/chargesList")
    public String chargesList (Model model) {
        List<Charge> chargesController = chargeService.getAllCharges();
        model.addAttribute("chargethym", chargesController);
        //modelMap.addAttribute(attributeName: "productsJspl", productsController);
        return "ChargesList";
    }
    @RequestMapping ("/deleteCharge")
    public String deleteProduct (@RequestParam("id") int id, Model model) {
        chargeService.deleteCharge(id);
        List<Charge> chargesController = chargeService.getAllCharges();
        model.addAttribute("chargethym", chargesController);
        return "ChargesList";
    }

    @RequestMapping("/showCharge")
    public String showCharge(@RequestParam("id") int id, Model model) throws ChangeSetPersister.NotFoundException {
        Charge charge = chargeService.getChargeById(id);
        model.addAttribute("chargethym", charge);
        return "EditCharge";
    }
    @RequestMapping("/updateCharge")
    public String updateCharge(@ModelAttribute("chargethym") Charge charge) {
        chargeService.saveCharge(charge);
        return "CreateCharge";
    }

}
