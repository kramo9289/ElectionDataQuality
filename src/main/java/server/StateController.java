
package server;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author stanley and kevin
 */
@RestController
public class StateController {
    @Autowired
    private StateRepo service;
    
    //only restful method needed for states
    @GetMapping("/states")
    public List<States> findAllStates() {
        return service.findAll();
    }
}
