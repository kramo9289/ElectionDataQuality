
package server;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author stanley and kevin
 * Entity class for neighbors in db
 */


@Entity
@Table(name="neighbors")
public class Neighbors {
    private Integer id;
    private Precincts firstPrecinct;
    private Precincts secondPrecinct;
    
    //java bean standard
    public Neighbors(){
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="first_precinct_id")
    @JsonBackReference
    public Precincts getFirstPrecinct(){
        return firstPrecinct;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="second_precinct_id")
    public Precincts getSecondPrecinct(){
        return secondPrecinct;
    }
    
    public void setFirstPrecinct(Precincts firstPrecinct){
        this.firstPrecinct=firstPrecinct;
    }
    
    public void setSecondPrecinct(Precincts secondPrecinct){
        this.secondPrecinct=secondPrecinct;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
}
