package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.UserRepository;

@Service
public class DonationService {

    private final DonationRepository donationRepository;
    private final UserRepository userRepository;

    public DonationService(DonationRepository donationRepository, UserRepository userRepository) {
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
    }

    public int sumAllBags(){
        return donationRepository.countAllByQuantity();
    }

    public int sumAllDonation(){
        return donationRepository.countAll();
    }

    public void save(Donation donation, String email) {
        User user = userRepository.findByUsername(email).orElse(new User());
        donation.setUser(user);
        donationRepository.save(donation);
    }

}
