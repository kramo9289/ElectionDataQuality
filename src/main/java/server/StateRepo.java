
package server;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author stanley and kevin
 */
public interface StateRepo extends JpaRepository<States, Integer> {
    //assume basic CRUD functions
}
