package app.web;
import app.config.UserSession;
import app.offer.service.OfferService;
import app.user.service.UserService;
import app.web.dto.OfferRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class OfferController {
    private final OfferService offerService;
    private final UserSession userSession;

    public OfferController(OfferService offerService,
                           UserSession userSession) {
        this.offerService = offerService;
        this.userSession = userSession;
    }

    @GetMapping("/create-offer")
    public ModelAndView createOffer(){
        ModelAndView modelAndView = new ModelAndView();

        if(!this.userSession.isLoggedIn()){
            modelAndView.setViewName("redirect:/login");
        }

        modelAndView.setViewName("offer-add");
        modelAndView.addObject("offer",OfferRequest.builder().build());
        return modelAndView;
    }

    @PostMapping("/create-offer")
    public ModelAndView createOffer(@Valid OfferRequest offerRequest, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();

        if(!this.userSession.isLoggedIn()){
            modelAndView.setViewName("redirect:/login");
        }

        if(bindingResult.hasErrors()){
            modelAndView.setViewName("offer-add");
            return modelAndView;
        }

        this.offerService.createOffer(offerRequest);

        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }

    @GetMapping("/offer-buy/{id}")
    public String buyOffer(@PathVariable(name = "id")UUID id){
        this.offerService.buyOffer(id);
        return "redirect:/home";
    }

    @GetMapping("/remove-offer/{id}")
    public String removeOffer(@PathVariable(name = "id")UUID id){
        return "redirect:/home";
    }
}




