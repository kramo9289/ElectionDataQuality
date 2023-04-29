
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
@Table(name="districts")
public class Districts {
    
    private Integer id;
    private String congid;
    private String shape_geojson;
    private String statefp;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }
    
    public String getCongid(){
        return congid;
    }
    
    @Lob
    @Column(name="shape_geojson")
    public String getShape_geojson() {
        return shape_geojson;
    }

    @Column(name="statefp")
    public String getStatefp() {
        return statefp;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCongid(String congid){
        this.congid=congid;
    }
    
    public void setShape_geojson(String shape_geojson) {
        this.shape_geojson = shape_geojson;
    }

    public void setStatefp(String statefp) {
        this.statefp = statefp;
    }
    
    
}
