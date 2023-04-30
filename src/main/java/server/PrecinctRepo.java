
package server;

/**
 *
 * @author stanley and kevin
 */
 
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//Defines pre
public interface PrecinctRepo extends JpaRepository<Precincts, Integer> {
    List<Precincts> findAll();
    List<Precincts> findByStatefp(String statefp);
    List<Precincts> findByStatefpAndDistrictidAndCountyname(String statefp,String districtid,String countyname);
    Precincts findByName(String name);
    List<Precincts> findByNameStartingWithIgnoreCaseAndStatefp(String partOfName, String statefp);
    List<Precincts> findByStatefpAndErrorIsNotNull(String statefp);
    @Query(value="SELECT countyname FROM precincts WHERE statefp=:statefp AND districtid=:districtid GROUP BY countyname;",nativeQuery=true)
    List<String> getAllCounties(String statefp,String districtid);
}