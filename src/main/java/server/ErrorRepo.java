
package server;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author stanley and kevin
 */
public interface ErrorRepo extends JpaRepository<Errors, Integer> {
    //A Native Query means it is a SQL statement, normally by default it is JPQL Queries which can only perform CRUD queries.
    //The : binds the variable stateFP to the param stateFP to dynamically load errors from specific states

}
