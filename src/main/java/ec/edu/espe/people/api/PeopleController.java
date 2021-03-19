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
    
    @PostMapping()
    public ResponseEntity savePeople(@RequestBody People person) {
        ResponseEntity response;
        try {
            this.service.savePeople(person);
            response = ResponseEntity.ok().build();
        } catch (InsertException e) {
            log.error(e.getMessage());
            response = ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }
    
    @GetMapping()
    public ResponseEntity<List<People>> listAll() {
        return ResponseEntity.ok(this.service.listAll());
    }
    
    @GetMapping("/{identification}")
    public ResponseEntity<People> findByIdentification(@PathVariable String identification) {
        ResponseEntity response;
        try {
            response = ResponseEntity.ok(this.service.findByIdentification(identification));
        } catch (RegistryNotFoundException e) {
            log.error(e.getMessage());
            response = ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }
}
