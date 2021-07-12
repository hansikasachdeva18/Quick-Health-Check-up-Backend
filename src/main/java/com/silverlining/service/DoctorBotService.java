package com.silverlining.service;

import com.silverlining.model.*;
import com.silverlining.repository.DiseasesRepository;
import com.silverlining.repository.SymptomsRepository;
import com.silverlining.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class DoctorBotService {

    private final static String COMMON = "Common";
    @Autowired
    SymptomsRepository symptomsRepository;
    @Autowired
    DiseasesRepository diseasesRepository;
    @Autowired
    UsersRepository usersRepository;

    public List<String> getAllCommonSymptoms(){
        List<String> commonSymptoms = new ArrayList<>();
        symptomsRepository.findAllByType(COMMON).forEach(
                symptom -> commonSymptoms.add(symptom.getSymptom()));
        return commonSymptoms;
    }

    public ProbableDiseases getProbableDiagnosis(SelectedSymptoms selectedSymptoms){
        List<Symptoms> selectedSymptomList = getSelectedSymptomList(selectedSymptoms);
        List<ProbableDisease> list = getProbableDiseases(selectedSymptomList);
        Long userId = saveUserDetails(selectedSymptomList, selectedSymptoms.getName());
        return new ProbableDiseases(list, userId);
    }

    public DiseaseSummary diagnose(SelectedExtraSymptoms selectedExtraSymptoms){
        User user = usersRepository.findById(selectedExtraSymptoms.getUserId())
                .orElse(null);
        assert user != null;
        List<Symptoms> mergedList = getMergedSymptomsList(selectedExtraSymptoms, user);
        Optional<Diseases> opt = getDiseasesOptions(mergedList);
        if(opt.isPresent()){
            Diseases d = opt.get();
            Map<String, String> uniqueLinkPair = generateLinkPair();
            String key = uniqueLinkPair.keySet().iterator().next();
            usersRepository.save(
                    new User(user.getId(), user.getName(), mergedList, d.getDisease(),
                            key, d.getReportsTD(), uniqueLinkPair.get(key)));
            return new DiseaseSummary(d.getDisease(), d.getReportsTD(), d.getMedicines(),
                    key, uniqueLinkPair.get(key));
        }
        return new DiseaseSummary();
    }
// link paswd generate
    private Map<String, String> generateLinkPair() {
        UUID uuid=UUID.randomUUID();
        Map<String, String> map = new HashMap<>();
        String temp = uuid.toString();
        map.put(temp.substring(0, 12),
            temp.substring(temp.length() -12, temp.length()-1));
        return map;
    }

    private Optional<Diseases> getDiseasesOptions(List<Symptoms> mergedList) {
        return StreamSupport.stream(diseasesRepository.findAll().spliterator(), false)
                .filter(
                        disease -> disease.getSymptomsList().containsAll(mergedList)
                )
        .min(
                Comparator.comparingInt(a -> a.getSymptomsList().size())
        );
    }

    private List<Symptoms> getMergedSymptomsList(SelectedExtraSymptoms selectedExtraSymptoms, User user) {
        List<Symptoms> symps = StreamSupport.stream(symptomsRepository.findAll().spliterator(), false)
                .filter(s ->
                        selectedExtraSymptoms.getSelectedExtraSymptoms()
                                .contains(s.getSymptom()))
                .collect(Collectors.toList());
        return Stream.concat(user.getSymptoms().stream(), symps.stream())
                .collect(Collectors.toList());
    }

    private Long saveUserDetails(List<Symptoms> selectedSymptomList, String name) {
        Long userId = usersRepository.count()+1;
        while(usersRepository.existsById(userId))
            userId++;
        usersRepository.save(
                new User(userId, name,
                        selectedSymptomList,
                        null, null, null, null));
        return userId;
    }

    private List<ProbableDisease> getProbableDiseases(List<Symptoms> selectedSymptomsList) {
        List<ProbableDisease> list = new ArrayList<>();
        StreamSupport.stream(diseasesRepository.findAll().spliterator(), false)
                .filter(
                        disease -> disease.getSymptomsList().containsAll(selectedSymptomsList)
                ).forEach(
                        disease -> {
                            List<Symptoms> listOfExtraSymptoms =
                                    disease.getSymptomsList().stream().filter(
                                           symptom -> !selectedSymptomsList.contains(symptom)
                                    ).collect(Collectors.toList());
                            ProbableDisease pd = new ProbableDisease
                                    (disease.getDisease(), listOfExtraSymptoms);
                            list.add(pd);
                        }
                );
        return list;
    }

    private List<Symptoms> getSelectedSymptomList(SelectedSymptoms selectedSymptoms) {
        return StreamSupport.stream(symptomsRepository.findAll().spliterator(), false)
                .filter(s ->  selectedSymptoms.getSelectedSymptoms().contains(s.getSymptom()))
                .collect(Collectors.toList());
    }

}
