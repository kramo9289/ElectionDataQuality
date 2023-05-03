
package server;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author stanley and kevin
 */
@Entity
@Table(name="demographics")
public class Demographics {
    
    private Integer aminov18;
    private Integer asianov18;
    private Integer blackov18;
    private Integer hawov18;
    private Integer hisov18;
    private Integer whiteov18;
    private Integer otherov18;
    private Integer pop100;
    private Integer id;
    private Precincts precinct;
    
    public Demographics(){
    }
    
    public Demographics(Integer aminov18, Integer asiannov18,Integer blackov18,Integer hawov18, Integer whiteov18, Integer otherov18, Integer pop100){
        this.aminov18=aminov18;
        this.asianov18=asiannov18;
        this.blackov18=blackov18;
        this.hawov18=hawov18;
        this.whiteov18=whiteov18;
        this.otherov18=otherov18;
        this.pop100=pop100;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }
    
    @OneToOne()
    @MapsId
    @JoinColumn(name="id")
    @JsonBackReference
    public Precincts getPrecinct(){
        return precinct;
    }
    
    @Column(name="aminov18")
    public Integer getAminov18() {
        return aminov18;
    }

    @Column(name="asianov18")
    public Integer getAsianov18() {
        return asianov18;
    }

    @Column(name="blackov18")
    public Integer getBlackov18() {
        return blackov18;
    }

    @Column(name="hawov18")
    public Integer getHawov18() {
        return hawov18;
    }

    @Column(name="hisov18")
    public Integer getHisov18() {
        return hisov18;
    }

    @Column(name="whiteov18")
    public Integer getWhiteov18() {
        return whiteov18;
    }

    @Column(name="otherov18")
    public Integer getOtherov18() {
        return otherov18;
    }

    @Column(name="pop100")
    public Integer getPop100() {
        return pop100;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setPrecinct(Precincts precinct){
        this.precinct=precinct;
    }
    
    public void setAminov18(Integer aminov18) {
        this.aminov18 = aminov18;
    }

    public void setAsianov18(Integer asianov18) {
        this.asianov18 = asianov18;
    }

    public void setBlackov18(Integer blackov18) {
        this.blackov18 = blackov18;
    }

    public void setHawov18(Integer hawov18) {
        this.hawov18 = hawov18;
    }

    public void setHisov18(Integer hisov18) {
        this.hisov18 = hisov18;
    }

    public void setWhiteov18(Integer whiteov18) {
        this.whiteov18 = whiteov18;
    }

    public void setOtherov18(Integer otherov18) {
        this.otherov18 = otherov18;
    }

    public void setPop100(Integer pop100) {
        this.pop100 = pop100;
    }
}
