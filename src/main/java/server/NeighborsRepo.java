
package server;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author stanley and kevin
 */
public interface NeighborsRepo extends JpaRepository<Neighbors, Integer> {
    List<Neighbors> findBySecondPrecinct(Precincts precinct);
    Neighbors findByFirstPrecinctAndSecondPrecinct(Precincts first, Precincts second);
}
