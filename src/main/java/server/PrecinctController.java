
package server;

/**
 *
 * @author stanley and kevin
 */
import java.sql.Timestamp;
import java.util.*;
 
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
 
import org.springframework.web.bind.annotation.*;
 
//This annotation marks the class as a request handler and maps the request sent from the client to a method in the server
@RestController
public class PrecinctController {
    //The Autowired annotation injects the constructor of PrecinctRepo into this class and that object has the methods from PrecinctRepo and JPA Repo
    @Autowired
    private PrecinctRepo precinctService;
    @Autowired
    private NeighborsRepo neighborService;
    @Autowired
    private DistrictsRepo districtService;
    @Autowired
    private FixedErrorsRepo fixedErrorsService;
    @Autowired
    private ErrorRepo unfixedErrorsService;
    @Autowired
    private SourcesRepo sourcesService;
    
    // RESTful API methods for Retrieval operations
    @GetMapping("/precincts")
    public List<Precincts> findAllPrecincts() {
        //maybe faster by getting limiting query to just id and geojson
        List<Precincts> allPrecincts=precinctService.findAll();
        for(Precincts p: allPrecincts){
            //Loading in all information is very slow, setting null makes it slightly faster to load, we just need geo json when loading in
            p.setDemographic(null);
            p.setError(null);
            p.setElections(null);
            p.setNeighbors(null);
        }
        return allPrecincts;
    }
    @GetMapping("/precincts/search/{partOfName}/{statefp}")
    public ResponseEntity<List<Precincts>> findAllNamesForSearch(@PathVariable String partOfName,@PathVariable String statefp){
        List<Precincts> precincts=precinctService.findByNameStartingWithIgnoreCaseAndStatefp(partOfName,statefp);
        List<Precincts> smallPrecincts=new ArrayList<Precincts>();
        int maxCount=0;
        for(Precincts p: precincts){
            if(maxCount<5){
                p.setShape_geojson(null);
                p.setDemographic(null);
                p.setElections(null);
                p.setNeighbors(null);
                p.setError(null);
                smallPrecincts.add(p);
                maxCount++;
            }
        }
        if(smallPrecincts.size()==0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(smallPrecincts,HttpStatus.FOUND);
    }

    @GetMapping("/precincts/{id}")
    public Precincts findPrecinct(@PathVariable String id) {
        Precincts precinct=precinctService.findById(Integer.parseInt(id)).get();
        return precinct;
    }
    
    @GetMapping("/cong/{statefp}")
    public List<Districts> getAllCongsForState(@PathVariable String statefp){
        return districtService.findByStatefp(statefp);
    }

    @GetMapping("/precincts/error/{statefp}")
    public List<Precincts> findErrorsForPrecincts(@PathVariable String statefp){
        List<Precincts> precincts=precinctService.findByStatefpAndErrorIsNotNull(statefp);
        for(Precincts p:precincts){
            p.setShape_geojson(null);
            p.setDemographic(null);
            p.setElections(null);
            p.setNeighbors(null);
        }
        return precincts;
    }
    
    @PostMapping("/fixederrors")
    public void setFixedError(@RequestBody FixedErrors error){
        error.setCommentTime(new Timestamp(System.currentTimeMillis()));
        fixedErrorsService.save(error);
    }
    
    @GetMapping("/sources/{statefp}")
    public Sources getSourcesForStatate(@PathVariable String statefp){
        return sourcesService.findByStatefp(statefp);
    }
    
    @GetMapping("/fixederrors")
    public List<FixedErrors> getAllFixedErrors(){
        return fixedErrorsService.findAll();
    }
    
    @GetMapping("/precincts/neighbors/{id}")
    public List<Neighbors> findNeighborsForPrecincts(@PathVariable String id){
        Precincts precinct=precinctService.findById(Integer.parseInt(id)).get();
        List<Neighbors> neighbors=precinct.getNeighbors();
        //check if the precinct has no neighbor
        if(neighbors.size()<1){
            //if there is no neighbors find all precincts that said that our precinct is a neighbor
            List<Neighbors> findNeighbors=neighborService.findBySecondPrecinct(precinct);
            //for all those neighbors switch the edge and set everything unimportant to null
            for(Neighbors findNeighbor:findNeighbors){
                Precincts firstPrecinct=findNeighbor.getFirstPrecinct();
                firstPrecinct.setDemographic(null);
                firstPrecinct.setError(null);
                firstPrecinct.setElections(null);
                firstPrecinct.setNeighbors(null);
                firstPrecinct.setShape_geojson("");
                findNeighbor.setSecondPrecinct(firstPrecinct);
            }
            return findNeighbors;
        }
        else{
            //if there are neighbors, for all neighbors set everything unimportant to null
            for(Neighbors neighbor:neighbors){
                Precincts neighborPrecinct=neighbor.getSecondPrecinct();              
                neighborPrecinct.setDemographic(null);
                neighborPrecinct.setError(null);
                neighborPrecinct.setElections(null);
                neighborPrecinct.setNeighbors(null);
                neighborPrecinct.setShape_geojson("");
            }
            List<Neighbors> findNeighbors=neighborService.findBySecondPrecinct(precinct);
            //for all those neighbors switch the edge and set everything unimportant to null
            for(Neighbors findNeighbor:findNeighbors){
                Precincts firstPrecinct=findNeighbor.getFirstPrecinct();
                firstPrecinct.setDemographic(null);
                firstPrecinct.setError(null);
                firstPrecinct.setElections(null);
                firstPrecinct.setNeighbors(null);
                firstPrecinct.setShape_geojson("");
                findNeighbor.setSecondPrecinct(firstPrecinct);
                neighbors.add(findNeighbor);
            }
        }

        return neighbors;
    }
    
    @GetMapping("/precincts/{statefp}/{districtid}/{countyname}")
    public List<Precincts> findPrecinctsInCong(@PathVariable String statefp,@PathVariable String districtid,@PathVariable String countyname){
        List<Precincts> allPrecincts=precinctService.findByStatefpAndDistrictidAndCountyname(statefp,districtid,countyname);        
        for(Precincts p: allPrecincts){
            p.setError(null);
            p.setNeighbors(null);
        }
        return allPrecincts;
    }

    @GetMapping("/precincts/counties/{statefp}/{districtid}")
    public List<String> findCountiesInCong(@PathVariable String statefp,@PathVariable String districtid){
        return precinctService.getAllCounties(statefp,districtid);
    }
   // RESTful API method for Update operation
    @PutMapping("/precincts/{id}")
    public void update(@RequestBody Precincts precinct, @PathVariable String id) {
        try {
            //function handles multiple put functionality ( modular :) )
            //not using but we try{} to get a findById
            Precincts existPrecinct = precinctService.findById(Integer.parseInt(id)).get();
            precinct.setId(Integer.parseInt(id));
            for(Elections e:precinct.getElections()){
                e.setPrecinct(precinct);
            }
            for(Neighbors n:precinct.getNeighbors()){
                if(n.getFirstPrecinct()==null){
                    n.setFirstPrecinct(precinct);
                }
                else{
                    n.setSecondPrecinct(precinct);
                }
            }
            precinctService.save(precinct);
            //if update is needed for error, then we resolve error type
            if(precinct.getError()!=null){
                if(precinct.getError().getErrorType().equals("RESOLVED")){
                    unfixedErrorsService.deleteById(precinct.getId());
                }
            }
        } catch (NoSuchElementException e) {
            //bad way
        }      
    }
    
    @PostMapping("/addNeighbors/{id1}/{id2}")
    public void addNeighbor(@PathVariable String id1, @PathVariable String id2){
        Precincts firstPrecinct=precinctService.findById(Integer.parseInt(id1)).get();
        Precincts secondPrecinct=precinctService.findById(Integer.parseInt(id2)).get();
        Neighbors newNeighbor=new Neighbors();
        newNeighbor.setFirstPrecinct(firstPrecinct);
        newNeighbor.setSecondPrecinct(secondPrecinct);
        neighborService.save(newNeighbor);
    }
    
    // RESTful API method for Delete operation
    @DeleteMapping("/deleteNeighbors/{id1}/{id2}")
    public void deleteNeighbor(@PathVariable String id1, @PathVariable String id2) {
        Precincts firstPrecinct=precinctService.findById(Integer.parseInt(id1)).get();
        Precincts secondPrecinct=precinctService.findById(Integer.parseInt(id2)).get();
        Neighbors toDelete=neighborService.findByFirstPrecinctAndSecondPrecinct(firstPrecinct, secondPrecinct);
        if(toDelete==null){
            toDelete=neighborService.findByFirstPrecinctAndSecondPrecinct(secondPrecinct, firstPrecinct);
            neighborService.deleteById(toDelete.getId());
        }
        else{
            neighborService.deleteById(toDelete.getId());
        }
    }
    
    // RESTful API method for Create operation
    @PostMapping("/precincts")
    public void add(@RequestBody Precincts precinct) {
        System.out.println("hi from java");
        System.out.println(precinct);
        //service.save(precinct);
    }
    
    // RESTful API method for Delete operation
    @DeleteMapping("/precincts/{id}")
    public void delete(@PathVariable Integer id) {
        precinctService.deleteById(id);
    }

    // Transactional because we update and delete neighbors and delete precincts, should anything go wrong we will rollback
    @Transactional
    @PutMapping("/merge/{id1}/{id2}")
    public ResponseEntity<?> merge(@RequestBody Precincts newPrecinct, @PathVariable String id1, @PathVariable String id2){
        try {
            update(newPrecinct, id2);
            deleteNeighbor(id1, id2);
            precinctService.deleteById(Integer.parseInt(id1));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }  
    }
}