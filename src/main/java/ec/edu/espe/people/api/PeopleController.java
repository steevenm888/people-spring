package ec.edu.espe.people.api;

import ec.edu.espe.people.exception.InsertException;
import ec.edu.espe.people.exception.RegistryNotFoundException;
import ec.edu.espe.people.model.People;
import ec.edu.espe.people.service.PeopleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Dictionary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/people")
@Slf4j
public class PeopleController {
    
    private PeopleService service;
    
    public PeopleController(PeopleService service) {
        this.service = service;
    }
    
    @PostMapping()
    @ApiOperation(value = "Create a new person")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Person created succesfully"),
        @ApiResponse(code = 400, message = "A person with the sent indentification already exists"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
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
    @ApiOperation(value = "List registered people")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "All registries listed"),
        @ApiResponse(code = 404, message = "No registries found"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<Dictionary<String, People>> listAll() {
        return ResponseEntity.ok(this.service.listAll());
    }
    
    @GetMapping("/{identification}")
    @ApiOperation(value = "Find a person by its identification")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Person with sent identification found"),
        @ApiResponse(code = 404, message = "No person with sent identification found"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
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
