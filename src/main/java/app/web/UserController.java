package app.web;
import app.config.UserSession;
import app.user.model.User;
import app.user.service.UserService;
import app.web.dto.LoginRequest;
import app.web.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserSession userSession;

    public UserController(UserService userService,
                          UserSession userSession) {
        this.userService = userService;
        this.userSession = userSession;
    }

    @GetMapping("/register")
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("register");
        modelAndView.addObject("registerDTO",RegisterRequest.builder().build());

        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid RegisterRequest registerRequest, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();

        if(bindingResult.hasErrors()){

            modelAndView.setViewName("register");
            modelAndView.addObject("registerDTO",RegisterRequest.builder().build());
            return modelAndView;
        }

        User user = this.userService.registerUser(registerRequest);

        if(user == null){
            modelAndView.setViewName("register");
            modelAndView.addObject("registerDTO",RegisterRequest.builder().build());
            return modelAndView;
        }

        userSession.setUsername(user.getUsername());

        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("login");
        modelAndView.addObject("loginDTO",LoginRequest.builder().build());
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid LoginRequest loginRequest,BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();

        if(bindingResult.hasErrors()){
            modelAndView.setViewName("login");
            modelAndView.addObject("loginDTO",LoginRequest.builder().build());

            return modelAndView;
        }
        User login = this.userService.login(loginRequest);

        if(login == null){
            modelAndView.setViewName("login");
            modelAndView.addObject("loginDTO",LoginRequest.builder().build());
            return modelAndView;
        }

        userSession.setUsername(login.getUsername());

        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(){
        this.userSession.logout();

        return new ModelAndView("redirect:/users/login");
    }
}
