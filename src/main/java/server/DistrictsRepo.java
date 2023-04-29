
package server;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author stanley and kevin
 */
public interface DistrictsRepo extends JpaRepository<Districts, Integer> {
    //select * from districts where statefp = statefp
    List<Districts> findByStatefp(String statefp);
}
