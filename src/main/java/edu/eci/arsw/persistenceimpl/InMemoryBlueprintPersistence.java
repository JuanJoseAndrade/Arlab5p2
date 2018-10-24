/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.persistenceimpl;

import edu.eci.arsw.model.Blueprint;
import edu.eci.arsw.model.Point;
import edu.eci.arsw.persistence.BlueprintNotFoundException;
import edu.eci.arsw.persistence.BlueprintPersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author hcadavid
 */
@Service("InMemoryBlue")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final ConcurrentHashMap<Tuple<String,String>,Blueprint> blueprints=new ConcurrentHashMap<>();
    public InMemoryBlueprintPersistence() {
        Point[] pts0=new Point[]{new Point(30, 40),new Point(15, 15),new Point(16, 17),new Point(22, 21),new Point(45, 60),new Point(90, 40)};
        Blueprint bp0=new Blueprint("mack", "plano1",pts0);
        addNewBlueprint(bp0);
        Point[] pts3=new Point[]{new Point(60, 40),new Point(15, 15),new Point(16, 17),new Point(22, 21),new Point(45, 60),new Point(90, 40)};
        Blueprint bp3=new Blueprint("mack", "plano2",pts3);
        addNewBlueprint(bp3);
        Point[] pts1=new Point[]{new Point(67, 89),new Point(47, 15),new Point(23, 12),new Point(85, 23),new Point(95, 40)};
        Blueprint bp1=new Blueprint("jhon", "plano1",pts1);
        addNewBlueprint(bp1);
        Point[] pts2=new Point[]{new Point(87, 109),new Point(47, 15),new Point(23, 12),new Point(85, 23),new Point(95, 40)};
        Blueprint bp2=new Blueprint("jhon", "plano2",pts2);
        addNewBlueprint(bp2);
    }    
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }
    @Override
    public List<Blueprint> getBlueprintsByAuthor(String autor){
        List<Blueprint> authorblueprint=new ArrayList();
        for (Map.Entry<Tuple<String, String>,Blueprint> tupla:blueprints.entrySet()) {            
            if(tupla.getKey().getElem1().contentEquals(autor)){
                authorblueprint.add(tupla.getValue());
            }
        }
        return authorblueprint;
    }

    @Override
    public void addNewBlueprint(Blueprint bp) {
        blueprints.putIfAbsent(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        //blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
    }

    @Override
    public List<Blueprint> getAllBluePrints() throws BlueprintNotFoundException {
        List<Blueprint> allblueprints=new ArrayList();
        for (Map.Entry<Tuple<String, String>,Blueprint> tupla:blueprints.entrySet()) {            
                allblueprints.add(tupla.getValue());
        }
        return allblueprints;
    }


    
    
}
