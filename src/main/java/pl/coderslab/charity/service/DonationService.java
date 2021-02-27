package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.entity.*;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DonationService {

    private final DonationRepository donationRepository;
    private final UserRepository userRepository;
    private final InstitutionRepository institutionRepository;

    public DonationService(DonationRepository donationRepository, UserRepository userRepository, InstitutionRepository institutionRepository) {
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
        this.institutionRepository = institutionRepository;
    }

    public int sumAllBags(){
        Object i = donationRepository.countAllByQuantity();
        if (i instanceof Number){
           return  ((Number) i).intValue();
        }
        return 0;
    }

    public int sumAllDonation(){
        return donationRepository.countAll();
    }

    public void save(Donation donation) {
        donationRepository.save(donation);
    }

    public int sumAllDonationForInputInstitution(long institutionId) {
       return donationRepository.countAllByInstitution(institutionId);
    }

    public Set<Institution> getInstitutionNameByMyDonation(String email) {
        Optional<User> byUsername = userRepository.findByUsername(email);
        if (byUsername.isPresent()){
            User user = byUsername.get();
            return donationRepository.findAllByUser(user).stream().map(Donation::getInstitution)
                    .collect(Collectors.toSet());
        }
        return new HashSet<>();
    }


    public void updateStatus(Donation donation, Status status) {
        donation.setStatus(status);
        donationRepository.save(donation);
    }

    public List<Donation> findAllMyDonationForInstitutionSortByDate(String email) {
        Optional<User> optionalUser = userRepository.findByUsername(email);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            List<Donation> donationList = donationRepository.findAllByUser(user);
            donationList.sort(Comparator.comparing(Donation::getCreatedDateTime));
            return donationList;
        }
        return new ArrayList<>();
    }

    public Optional<Donation> findById(long donationId) {
        return donationRepository.findById(donationId);
    }


    public Map<Integer, Integer> sendAllPackageToInstitutionAndSetStatus(Institution institution, Status status) {
        List<Donation> allByInstitutionAndStatusIs = donationRepository.findAllByInstitutionAndStatus(institution, status);
        Archives archives = new Archives();
        archives.setLocalDate(LocalDate.now());
        archives.setLocalTime(LocalTime.now());
        archives.setStatus(Status.DELIVERED);

        int counter = 0;
        int quantity =0;

        for (Donation donation : allByInstitutionAndStatusIs) {
            quantity += donation.getQuantity();
            counter++;
            donation.setStatus(Status.DELIVERED);
            donation.getArchives().add(archives);
            donationRepository.save(donation);
        }
        HashMap<Integer, Integer> giftsAndQuantityHashMap = new HashMap<>();
        giftsAndQuantityHashMap.put(counter,quantity);
        return giftsAndQuantityHashMap;
    }

    public int sumAllDonationWhereStatusIs(Status status) {
        return donationRepository.countAllByStatus(status);
    }
}
