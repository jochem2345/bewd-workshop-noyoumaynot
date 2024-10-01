package han.aim.se.noyoumaynot.movie.service;

import han.aim.se.noyoumaynot.movie.repository.UserToken;
import han.aim.se.noyoumaynot.movie.user.Role;
import han.aim.se.noyoumaynot.movie.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService {
  ArrayList<UserToken> userTokens = new ArrayList<>();
  ArrayList<User> users = new ArrayList<>();

  public AuthenticationService() {
    Role adminRole = new Role("admin", true);
    Role userRole = new Role("user", false);

    User user1 = new User("userJochem", "123", userRole);
    User admin1 = new User("adminJochem", "123", adminRole);

    users.add(user1);
    users.add(admin1);
  }

  public String login(String username, String password) {
    for (User user : users) {
      if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
        UserToken userToken = new UserToken(username);
        userTokens.add(userToken);
        return userToken.getToken();
      }
    }
    return null;
  }

  public boolean isValidToken(String token) {
    long currentTime = System.currentTimeMillis() / 1000;
    for (UserToken userToken : userTokens) {
      if (userToken.getToken().equals(token) && (userToken.getExpiresIn() > currentTime)) {
        return true;
      }
    }
    return false;
  }

  public String getUsername(String token) {
    for (UserToken userToken : userTokens) {
      if (userToken.getToken().equals(token)) {
        return userToken.getUsername();
      }
    }
    return null;
  }
}
