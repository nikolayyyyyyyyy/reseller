package app.user.service;
import app.exception.DomainException;
import app.user.model.User;
import app.user.repository.UserRepository;
import app.web.dto.LoginRequest;
import app.web.dto.RegisterRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(RegisterRequest registerRequest){
        if(this.userRepository.findByUsername(registerRequest.getUsername()) != null ||
        this.userRepository.findByEmail(registerRequest.getEmail()) != null){

            throw new DomainException("Username or email invalid.");
        }

        return this.userRepository.save(User
                .builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build());
    }

    public User login(LoginRequest loginRequest){
        if(this.userRepository.findByUsername(loginRequest.getUsername()) == null){

            throw new DomainException("Username or password incorrect.");
        }

        if(passwordEncoder.matches(loginRequest.getPassword(),this.userRepository.findByUsername(loginRequest.getUsername()).getPassword())){

            throw new DomainException("Username or password incorrect.");
        }


        return this.userRepository.findByUsername(loginRequest.getUsername());
    }

    public User getUserByUsername(String username){
        return this.userRepository.findByUsername(username);
    }
}










