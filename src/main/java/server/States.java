
package server;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author stanley and kevin
 */
@Entity
@Table(name="states")
public class States {
    private Integer ogrFID;
    private String statefp;
    private String state_geojson;
    private String name;
    
    //java bean standard
    public States(){
    }
    
    public States(Integer ogrFID, String statefp, String state_geojson, String name){
        this.ogrFID=ogrFID;
        this.statefp=statefp;
        this.state_geojson=state_geojson;
        this.name=name;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="OGR_FID")
    public Integer getOgrFID() {
        return ogrFID;
    }

    @Column(name="statefp")
    public String getStatefp() {
        return statefp;
    }

    @Lob
    @Column(name="state_geojson")
    public String getState_geojson() {
        return state_geojson;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }
    
    public void setOgrFID(Integer ogrFID) {
        this.ogrFID = ogrFID;
    }

    public void setStatefp(String statefp) {
        this.statefp = statefp;
    }

    public void setState_geojson(String state_geojson) {
        this.state_geojson = state_geojson;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
