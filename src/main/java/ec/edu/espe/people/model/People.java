/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.people.model;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author esteban
 */

@Data
public class People {
    private String identification;
    private String name;
    private String lastname;
    private Date birthdate;
}
