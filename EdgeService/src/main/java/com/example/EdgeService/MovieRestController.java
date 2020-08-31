/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EdgeService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author michellanet
 */
@RestController
public class MovieRestController {
    
    @Autowired
    private IMovieClient client;
    
    /*public MovieRestController(IMovieClient client){
        this.client = client;
    }*/
    
    @GetMapping("/movies")
@HystrixCommand(fallbackMethod = "fallbackMovies")
    public Collection<Movie> getMovies(){
        return client.readMovies().getContent().stream().collect(Collectors.toList());
    }    
    
    public Collection<Movie> fallbackMovies(){
        List<Movie> movies = new ArrayList<>();
        
        Movie movie = new Movie();
        movie.setName("fallback movie");
        movie.setRating(10);
        
        movies.add(movie);
        
        return movies;
    }
}
