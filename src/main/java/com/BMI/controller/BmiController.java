package com.BMI.controller;

import com.BMI.model.BmiRecord;
import com.BMI.model.User;
import com.BMI.repository.BmiRecordRepository;
import com.BMI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;

@Controller
public class BmiController {

    @Autowired
    private BmiRecordRepository bmiRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public String viewForm(Model model) {
        model.addAttribute("bmiRecord", new BmiRecord());
        return "index";
    }

    @PostMapping("/calculate")
    public String calculateForm(@ModelAttribute BmiRecord BmiRecord, Model model) {
        // Oblicz wartość BMI
        double bmi = (BmiRecord.getWeight() / (BmiRecord.getHeight() * BmiRecord.getHeight())) * 10000;
        // Określ kategorię BMI
        String category = getBmiCategory(bmi);
        // Zapisz rekord BMI w bazie danych
        BmiRecord.setBmi(bmi);
        BmiRecord.setBmiCategory(category);
        BmiRecord.setRecordDate(new Timestamp(System.currentTimeMillis()));

        // Ustaw użytkownika
        User user = userRepository.findById(1L).orElse(null); // Przykład, gdzie szukam użytkownika o ID 1
        BmiRecord.setUser(user);

        // Zapisz rekord BMI do bazy danych
        bmiRecordRepository.save(BmiRecord);

        // Dodaj wyniki do modelu
        model.addAttribute("bmi", String.format("%.2f", bmi));
        model.addAttribute("category", category);
        return "calculate";
    }


    private String getBmiCategory(double bmi) {
        if (bmi < 16) {
            return "Wygłodzenie";
        } else if (bmi < 17) {
            return "Wychudzenie";
        } else if (bmi < 18.5) {
            return "Niedowaga";
        } else if (bmi < 25) {
            return "Wartość prawidłowa";
        } else if (bmi < 30) {
            return "Nadwaga";
        } else if (bmi < 35) {
            return "I stopień otyłości";
        } else if (bmi < 40) {
            return "II stopień otyłości";
        } else {
            return "Otyłość skrajna";
        }
    }
}
