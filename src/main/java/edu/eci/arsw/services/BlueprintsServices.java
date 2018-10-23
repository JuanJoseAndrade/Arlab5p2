/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.services;
import edu.eci.arsw.model.Blueprint;
import edu.eci.arsw.persistence.BlueprintNotFoundException;
import edu.eci.arsw.persistenceimpl.BlueprintsPersistence;
import edu.eci.arsw.filtros.Filtro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author juan
 */
public interface BlueprintsServices {
    public void addNewBlueprint(Blueprint bp) throws BlueprintNotFoundException;
    public List<Blueprint> getAllBlueprints() throws BlueprintNotFoundException;
    public void useFilter(Blueprint bp) throws BlueprintNotFoundException;
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException;
    public List<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException;
    
}
