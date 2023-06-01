
package server;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.*;
/**
 *
 * @author stanley and kevin
 */

@Entity
@Table(name="precincts")
public class Precincts {
    
    private Integer id;
    private String shape_geojson;
    private String name;
    private String origname;
    private String statefp;
    private String countyname;
    private String districtid;
    private Demographics demographic;
    private List<Elections> elections;
    private List<Neighbors> neighbors;
    private List<Neighbors> secondNeighbor;
    private Errors error;
    //need to add neighbors and errors like in repo
 

    //java bean standard
    public Precincts(){
    }
    
    //origname, countyname, not used in construction of precincts 
    public Precincts(String origname,String countyname,String statefp,String shape_geojson, String name) {
        this.statefp=statefp;
        this.shape_geojson=shape_geojson;
        this.name=name;
    }

    @OneToOne(cascade=CascadeType.ALL,mappedBy="precinct")
    @JsonManagedReference 
    public Errors getError(){
        return error;
    }

    //establish relationship of demographics and elections tables
    @OneToOne(cascade=CascadeType.ALL,mappedBy="precinct")
    @JsonManagedReference 
    public Demographics getDemographic(){
        return demographic;
    }
    
    @OneToMany(cascade=CascadeType.ALL,mappedBy="precinct",orphanRemoval=true)
    public List<Elections> getElections(){
        return elections;
    }
    
    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="firstPrecinct")
    public List<Neighbors> getNeighbors(){
        return neighbors;
    }
    
    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="secondPrecinct")
    @JsonBackReference 
    public List<Neighbors> getSecondNeighbor(){
        return secondNeighbor;
    }   

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId(){
        return id;
    }

    @Column(name="origname")
    public String getOrigname(){
        return origname;
    }
        
    @Column(name="countyname")
    public String getCountyname(){
        return countyname;
    }
    
    @Column(name="statefp")
    public String getStatefp(){
        return statefp;
    }  
    
    @Column(name="districtid")
    public String getDistrictid(){
        return districtid;
    }

    @Lob
    @Column(name="shape_geojson")
    public String getShape_geojson() {
        return shape_geojson;
    }
    
    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setShape_geojson(String shape_geojson) {
        this.shape_geojson = shape_geojson;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setOrigname(String origname) {
        this.origname = origname;
    }

    public void setStatefp(String statefp) {
        this.statefp = statefp;
    }

    public void setCountyname(String countyname) {
        this.countyname = countyname;
    }

    public void setDistrictid(String districtid) {
        this.districtid = districtid;
    }

    //added demographic and elections setters
    public void setDemographic(Demographics demographic) {
        this.demographic = demographic;
    }

    public void setElections(List<Elections> elections) {
        this.elections = elections;
    }

    public void setError(Errors error) {
        this.error = error;
    }
    
    public void setNeighbors(List<Neighbors> neighbors) {
        this.neighbors = neighbors;
    }

    public void setSecondNeighbor(List<Neighbors> secondNeighbor) {
        this.secondNeighbor = secondNeighbor;
    }

}
