/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import edu.eci.arsw.model.Blueprint;
import edu.eci.arsw.model.Point;
import edu.eci.arsw.services.BlueprintsServices;
import edu.eci.arsw.services.BlueprintsServicesStub;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Juan
 */

@RestController
@RequestMapping(value = "/blueprints") 
public class BlueprintAPIController {
    
    @Autowired
    @Qualifier("BluePrintServicesStub")
    BlueprintsServices blueprintservices;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getBlueprints(){
 	try {
            return new ResponseEntity<>(blueprintservices.getAllBlueprints(),HttpStatus.ACCEPTED);
 	} catch (Exception ex) {
            return new ResponseEntity<>("Error 404",HttpStatus.NOT_FOUND);
 	}  
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/{author}")
    public ResponseEntity<?> getBlueprintsByAuthor(@PathVariable String author) {
        try {
            return new ResponseEntity<>(blueprintservices.getBlueprintsByAuthor(author),HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error 404 no se encuentra el autor",HttpStatus.NOT_FOUND);
        }    
        }
    @GetMapping("/{author}/{bpname}")    
    public ResponseEntity<?> getBlueprint( @PathVariable String author, @PathVariable String bpname ) {
        try {
            return new ResponseEntity<>(blueprintservices.getBlueprint(author, bpname),HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex+"Error 404, The blueprint doesnt exist",HttpStatus.NOT_FOUND);
        }
            }
    //Sample Post
    //$ curl -i -X POST -HContent-Type:application/json -HAccept:application/json http://localhost:8080/blueprints -d '{"author":"jhon","points":[{"x":67,"y":89},{"x":47,"y":15},{"x":23,"y":12},{"x":85,"y":23},{"x":95,"y":40}],"name":"plano3"}'
    @RequestMapping(method = RequestMethod.POST)	
    public ResponseEntity<?> manejadorPost(@RequestBody Blueprint blueprint){
		try {
			blueprintservices.addNewBlueprint(blueprint);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception ex) {
			Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error on uploading the Blueprint",HttpStatus.FORBIDDEN);            
		}        
	}
    //Sample PUT
    //$ curl -i -X PUT -HContent-Type:application/json -HAccept:application/json http://localhost:8080/blueprints/mack/plano1 -d '[{"x":67,"y":89}]'
    @RequestMapping(method = RequestMethod.PUT, value = "/{author}/{bpname}")	
	public ResponseEntity<?> manejadorPut(@PathVariable String author, @PathVariable String bpname,@RequestBody  ArrayList<Point> puntos){
		try {   
                        blueprintservices.getBlueprint(author, bpname).setPoints(puntos);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception ex) {
			Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No se pudo hacer el proceso Put de los planos",HttpStatus.FORBIDDEN);            
		}        
	
	}
    
}

