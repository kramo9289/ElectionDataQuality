
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
    public DistrictsRepo districtService;
    //to-do: need database for neighbors of a precinct, for errors and sources?
    
    // RESTful API methods for Retrieval operations
    @GetMapping("/precincts")
    public List<Precincts> findAllPrecincts() {
        //maybe faster by getting limiting query to just id and geojson
        List<Precincts> allPrecincts=precinctService.findAll();
        return allPrecincts;
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

    
    @GetMapping("/precincts/counties/{statefp}/{districtid}")
    public List<String> findCountiesInCong(@PathVariable String statefp,@PathVariable String districtid){
        return precinctService.getAllCounties(statefp,districtid);
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
}