/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.people.api;

import ec.edu.espe.people.model.People;
import ec.edu.espe.people.service.PeopleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author esteban
 */

@RestController
@RequestMapping("/api/people")
public class PeopleController {
    
    private PeopleService service;
    
    public PeopleController(PeopleService service) {
        this.service = service;
    }
    
    @PostMapping("/add")
    public ResponseEntity savePeople(@RequestBody People people) {
        this.service.savePeople(people);
        return ResponseEntity.ok().build();
    }
}
