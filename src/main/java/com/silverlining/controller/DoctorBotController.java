package com.silverlining.controller;


import com.silverlining.model.DiseaseSummary;
import com.silverlining.model.ProbableDiseases;
import com.silverlining.model.SelectedExtraSymptoms;
import com.silverlining.model.SelectedSymptoms;
import com.silverlining.service.DoctorBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
// calls the service layer(buisness logic) and gets the output and returns the valuse to calling system(frontend)
@RequestMapping("/docBot")
public class DoctorBotController {

    @Autowired
    private DoctorBotService service;

    @GetMapping("/commonSymptoms")
    public List<String> getCommonSymptoms(){
        return service.getAllCommonSymptoms();
    }

    @PostMapping("/probableDiagnosis")
    public ProbableDiseases getProbableDiseases(@RequestBody SelectedSymptoms selectedSymptoms){
        return service.getProbableDiagnosis(selectedSymptoms);
    }

    @PostMapping("/diagnose")
    public DiseaseSummary diagnose(@RequestBody SelectedExtraSymptoms selectedExtraSymptoms){
        return service.diagnose(selectedExtraSymptoms);
    }
}
