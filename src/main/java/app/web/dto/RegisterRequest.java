package app.web.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RegisterRequest {
    @Size(min = 3,max = 20,message = "Username must be between 3 and 20 characters.")
    private String username;

    @Email
    private String email;

    @Size(min = 3,max = 20,message = "Password must be between 3 and 20 characters.")
    private String password;
}
