package app.config;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Objects;

@SessionScope
@Component
public class UserSession {
    private String username;

    public UserSession() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLoggedIn(){
        return !Objects.equals(this.username, null);
    }

    public void logout(){
        this.username = null;
    }
}
