/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.people.api;

import ec.edu.espe.people.exception.InsertException;
import ec.edu.espe.people.exception.RegistryNotFoundException;
import ec.edu.espe.people.model.People;
import ec.edu.espe.people.service.PeopleService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@Slf4j
public class PeopleController {
    
    private PeopleService service;
    
    public PeopleController(PeopleService service) {
        this.service = service;
    }
    
    @PostMapping("/add")
    public ResponseEntity savePeople(@RequestBody People people) {
        try {
            log.info("Person added succesfully");
            this.service.savePeople(people);
            return ResponseEntity.ok().build();
        } catch (InsertException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/listAll")
    public ResponseEntity<List<People>> listAll() {
        log.info("Listed all people in the db");
        return ResponseEntity.ok(this.service.listAll());
    }
    
    @GetMapping("/findByIdentification/{identification}")
    public ResponseEntity<People> findByIdentification(@PathVariable String identification) {
        try {
            log.info("Person with identification {} found");
            return ResponseEntity.ok(this.service.findByIdentification(identification));
        } catch (RegistryNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
