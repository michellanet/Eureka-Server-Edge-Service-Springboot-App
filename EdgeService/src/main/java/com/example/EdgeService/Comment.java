/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EdgeService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author michellanet
 */
@Data
@Entity
public class Comment {
    private String name;
    private String email;
    private String body;
    
    @GeneratedValue
    @Id
    private long id;
    
    
}
