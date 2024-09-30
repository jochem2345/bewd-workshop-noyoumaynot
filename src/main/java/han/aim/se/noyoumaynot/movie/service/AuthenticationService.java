package han.aim.se.noyoumaynot.movie.service;

import han.aim.se.noyoumaynot.movie.repository.UserToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService {
  ArrayList<UserToken> userTokens = new ArrayList<>();
  private static final String USERNAME = "jochem";
  private static final String PASSWORD = "123";

  public String login(String username, String password) {
    if (username.equals(USERNAME) && password.equals(PASSWORD)) {
      UserToken userToken = new UserToken(username);
      userTokens.add(userToken);
      return userToken.getToken();
    } else {
      return null;
    }
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
