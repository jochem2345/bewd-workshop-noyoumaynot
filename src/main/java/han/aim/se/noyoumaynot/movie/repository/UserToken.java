package han.aim.se.noyoumaynot.movie.repository;

import java.util.UUID;

public class UserToken {
    private final int TOKENVALIDTIME = 60*10;
    private String token;
    private String username;
    private long expiresIn;

    public UserToken(String username){
        this.username = username;
        this.expiresIn = System.currentTimeMillis() / 1000 + TOKENVALIDTIME;
        this.token = UUID.randomUUID().toString();
    }

    public String getToken() {
        return token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public String getUsername() {
        return username;
    }
}
