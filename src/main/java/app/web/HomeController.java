package app.web;
import app.config.UserSession;
import app.offer.model.Offer;
import app.offer.service.OfferService;
import app.user.model.User;
import app.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final UserService userService;
    private final OfferService offerService;
    private final UserSession userSession;

    public HomeController(UserService userService,
                          OfferService offerService, UserSession userSession) {
        this.userService = userService;
        this.offerService = offerService;
        this.userSession = userSession;
    }

    @GetMapping
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        if(!this.userSession.isLoggedIn()){
            modelAndView.setViewName("redirect:/users/register");
            return modelAndView;
        }

        User user = this.userService
                .getUserByUsername(userSession.getUsername());
        List<Offer> otherOffers = this.offerService.getAllOffers()
                .stream().filter(o -> !Objects.equals(o.getOwner().getUsername(), user.getUsername()))
                        .toList();

        modelAndView.setViewName("home");
        modelAndView.addObject("user",user);
        modelAndView.addObject("allPosts",otherOffers);

        return modelAndView;
    }
}
