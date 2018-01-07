package pl.corp.dziadzia.MnemoTutorBack.rest.users;

import lombok.Data;
import pl.corp.dziadzia.MnemoTutorBack.model.User;

@Data
public class UserPresentation {

    private String email;
    private String username;

    public UserPresentation(User user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
    }
}
