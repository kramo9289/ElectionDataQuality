
package server;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author stanley and kevin
 */
public interface SourcesRepo extends JpaRepository<Sources, Integer> {
    //select * from sources where statefp = statefp
    Sources findByStatefp(String statefp);
}
