package han.aim.se.noyoumaynot.movie.controller;

import han.aim.se.noyoumaynot.movie.domain.Movie;
import han.aim.se.noyoumaynot.movie.service.AuthenticationService;
import han.aim.se.noyoumaynot.movie.service.MovieService;
import han.aim.se.noyoumaynot.movie.exceptionhandling.AuthenticationException;
import han.aim.se.noyoumaynot.movie.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final AuthenticationService authenticationService;
//    private static final String USERNAME = "jochem";
//    private static final String PASSWORD = "123";

    public String currentToken;

    @Autowired
    public MovieController(MovieService movieService, AuthenticationService authenticationService) {
        this.movieService = movieService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        currentToken = authenticationService.login(user.getUsername(), user.getPassword());
        if (currentToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        return ResponseEntity.ok("Login successful, token: " + currentToken);
    }

    @GetMapping
    public ArrayList<Movie> getAllMovies(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        authenticate(authorization);
        return movieService.getMovieList();
    }


    @GetMapping("/show")
    public Movie getMovieById(@RequestParam("id") String id, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        authenticate(authorization);
        Movie movie = movieService.getMovieById(id);
        return movie;
    }

    @PostMapping("/add")
    public Movie addMovie(@RequestBody Movie movie, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        authenticate(authorization);
        movieService.insertMovie(movie);
        return movie;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") String id, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        authenticate(authorization);
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }

    private String authenticate(String token) throws AuthenticationException {
        if (authenticationService.isValidToken(token)){
            return authenticationService.getUsername(token);
        } else {
            throw new AuthenticationException("Invalid token");
        }
    }


}
