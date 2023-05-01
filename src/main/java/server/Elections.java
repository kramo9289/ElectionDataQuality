
package server;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author stanley and kevin
 */
@Entity
@Table(name="elections")
public class Elections {
    
    private Integer id;
    private String year;
    private String type;
    private Integer dem;
    private Integer rep;
    private Integer tot;
    private Integer other;
    private Precincts precinct;
    
    //need empty class becuase @Entity annotation uses it
    public Elections(){
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }
    
    @ManyToOne()
    @JoinColumn(name="precinct_id")
    @JsonBackReference
    public Precincts getPrecinct(){
        return precinct;
    }

    @Column(name="year")
    public String getYear() {
        return year;
    }

    @Column(name="type")
    public String getType() {
        return type;
    }
    
    @Column(name="dem")
    public Integer getDem() {
        return dem;
    }

    @Column(name="rep")
    public Integer getRep() {
        return rep;
    }
    
    @Column(name="tot")
    public Integer getTot() {
        return tot;
    }
    @Column(name="other")
    public Integer getOther() {
        return other;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDem(Integer dem) {
        this.dem = dem;
    }

    public void setRep(Integer rep) {
        this.rep = rep;
    }

    public void setTot(Integer tot) {
        this.tot = tot;
    }

    public void setOther(Integer other) {
        this.other = other;
    }

    public void setPrecinct(Precincts precinct) {
        this.precinct = precinct;
    }
    
    
}
