package com.example.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class CommentServiceApplication {
    
    @Autowired
    private ICommentRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(CommentServiceApplication.class, args);
	}

}

@Component
class ItemInitializer implements CommandLineRunner{

    private final ICommentRepository repo;
    
    ItemInitializer(ICommentRepository _repo){
        this.repo = _repo;
    }
    
    @Override
    public void run(String... args) throws Exception {
        for(int i=1; i<=5; i++){
            Comment comment = new Comment();
            comment.setName("Comment Name" + i);
            comment.setEmail("email"+i+"@mail.com");
            comment.setBody("Comment Body" + i);
            
            repo.save(comment);
        }
        repo.findAll().forEach(System.out::println);
    }
}

