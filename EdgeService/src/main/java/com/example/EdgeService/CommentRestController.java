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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author michellanet
 */
@RestController
public class CommentRestController {
    
    @Autowired
    private ICommentClient client;
    
    /*public MovieRestController(IMovieClient client){
        this.client = client;
    }*/
    
    @CrossOrigin(origins="http://localhost:3000")
    @GetMapping("/comments")
@HystrixCommand(fallbackMethod = "fallbackComments")
    public Collection<Comment> getComments(){
        return client.readComments().getContent().stream().collect(Collectors.toList());
    }    
    
    public Collection<Comment> fallbackComments(){
        List<Comment> comments = new ArrayList<>();
        
        Comment comment = new Comment();
        comment.setName("Fallback Comment");
        comment.setEmail("movieService@mail.com");
        comment.setBody("We are sorry, service will be back as soon as possible. If for more information please contact us by this email");
        
        comments.add(comment);
        
        return comments;
    }
}
