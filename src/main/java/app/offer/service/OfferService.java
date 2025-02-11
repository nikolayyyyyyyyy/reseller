package app.offer.service;

import app.condition.model.Condition;
import app.condition.service.ConditionService;
import app.config.UserSession;
import app.offer.model.Offer;
import app.offer.repository.OfferRepository;
import app.user.model.User;
import app.user.service.UserService;
import app.web.dto.OfferRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final UserService userService;
    private final UserSession userSession;
    private final ConditionService conditionService;

    public OfferService(OfferRepository offerRepository,
                        UserService userService,
                        UserSession userSession,
                        ConditionService conditionService) {
        this.offerRepository = offerRepository;
        this.userService = userService;
        this.userSession = userSession;
        this.conditionService = conditionService;
    }

    public List<Offer> getAllOffers(){
        if(this.offerRepository.findAll().isEmpty()){
            return new ArrayList<>();
        }

        return this.offerRepository.findAll();
    }

    public void createOffer(OfferRequest offerRequest){
        User user = this
                .userService
                .getUserByUsername(this.userSession.getUsername());
        Condition condition = this
                .conditionService
                .getConditionByConditionType(offerRequest.getCondition());


        Offer offer = Offer
                .builder()
                .description(offerRequest.getDescription())
                .price(offerRequest.getPrice())
                .owner(user)
                .condition(condition)
                .build();

        user.getOffers().add(offer);
        condition.getOffers().add(offer);

        this.offerRepository.save(offer);
    }

    public void buyOffer(UUID id){
        Offer offer = this.offerRepository.findById(id)
                .orElse(null);

        User user = this.userService.getUserByUsername(this.userSession.getUsername());

        if(offer != null){
            user.getBoughtOffers().add(offer);
            offer.getBoughtBy().add(user);
            this.offerRepository.save(offer);
        }
    }

    public void removeOffer(UUID id){
        Offer offer = this.offerRepository.findById(id)
                .orElse(null);
        User user = this.userService.getUserByUsername(this.userSession.getUsername());

        if(offer != null){

            user.getOffers().remove(offer);
        }
    }
}
