package com.example.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class MovieServiceApplication {
    
    @Autowired
    private IMovieRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(MovieServiceApplication.class, args);
                
                //Movie movie = new Movie();
	}

}

@Component
class ItemInitializer implements CommandLineRunner{

    private final IMovieRepository repo;
    
    ItemInitializer(IMovieRepository _repo){
        this.repo = _repo;
    }
    
    @Override
    public void run(String... args) throws Exception {
        for(int i=1; i<=5; i++){
            Movie movie = new Movie();
            movie.setName("Movie" + i);
            movie.setRating(10);
            
            repo.save(movie);
        }
        repo.findAll().forEach(System.out::println);
    }
}
