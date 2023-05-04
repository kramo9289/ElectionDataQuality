
package server;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author stanley and kevin
 * simple entity class for sources used for the project 
 */
@Entity
@Table(name="sources")
public class Sources {
    
    private Integer id;
    private String statefp;
    private String state;
    private String district;
    private String precinct;
    private String voting;
    private String demographic;
    
    public Sources(){
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @Column(name="statefp")
    public String getStatefp() {
        return statefp;
    }

    @Column(name="state")
    public String getState() {
        return state;
    }

    @Column(name="district")
    public String getDistrict() {
        return district;
    }

    @Column(name="precinct")
    public String getPrecinct() {
        return precinct;
    }

    @Column(name="voting")
    public String getVoting() {
        return voting;
    }

    @Column(name="demographic")
    public String getDemographic() {
        return demographic;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStatefp(String statefp) {
        this.statefp = statefp;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setPrecinct(String precinct) {
        this.precinct = precinct;
    }

    public void setVoting(String voting) {
        this.voting = voting;
    }

    public void setDemographic(String demographic) {
        this.demographic = demographic;
    }
    
    
}
