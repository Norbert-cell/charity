package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.repository.DonationRepository;

@Service
public class DonationService {

    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public int sumAllBags(){
        return donationRepository.countAllByQuantity();
    }

    public int sumAllDonation(){
        return donationRepository.countAll();
    }
}
