package app.offer.service;

import app.offer.model.Offer;
import app.offer.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferService {
    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public List<Offer> getAllOffers(){
        if(this.offerRepository.findAll().isEmpty()){
            return new ArrayList<>();
        }

        return this.offerRepository.findAll();
    }
}
