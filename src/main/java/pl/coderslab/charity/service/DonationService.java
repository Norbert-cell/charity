package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.Status;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

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

    public void save(Donation donation, User user) {
        donation.setUser(user);
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

    public List<Donation> checkIsDateAfterDateNowAndStatusIsPlaced() {
        return donationRepository.findAllByPickUpDateIsAfterAndPickUpTimeIsAfterAndStatusIs();
    }

    @Transactional
    public void updateStatus(Long id, Status status) {
        donationRepository.updateStatus(id, status);
    }

    public List<Donation> findAllMyDonationForInstitutionSortByDate(long institutionId, String email) {
        Optional<User> optionalUser = userRepository.findByUsername(email);
        Optional<Institution> optionalInstitution = institutionRepository.findById(institutionId);
        if (optionalUser.isPresent() && optionalInstitution.isPresent()){
            User user = optionalUser.get();
            Institution institution = optionalInstitution.get();
            List<Donation> donationList = donationRepository.findAllByUserAndInstitution(user, institution);
            donationList.sort(Comparator.comparing(Donation::getPickUpDate));
            return donationList;
        }
        return new ArrayList<>();
    }
}
