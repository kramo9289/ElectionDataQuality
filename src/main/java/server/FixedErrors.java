
package server;

import java.sql.Timestamp;
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
@Table(name="errors_fixed")
public class FixedErrors {
    private Integer id;
    private String precinctID;
    private String comment;
    private String errorType;
    private Timestamp commentTime;
    private String relevantInfo;
    private String precinctName;
    
    public FixedErrors(){
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @Column(name="precinctid")
    public String getPrecinctID() {
        return precinctID;
    }

    @Column(name="comment")
    public String getComment() {
        return comment;
    }
    
    @Column(name="precinctname")
    public String getPrecinctName() {
        return precinctName;
    }


    @Column(name="error_type")
    public String getErrorType() {
        return errorType;
    }

    @Column(name="comment_time")
    public Timestamp getCommentTime() {
        return commentTime;
    }

    @Lob
    @Column(name="relevant_info")
    public String getRelevantInfo() {
        return relevantInfo;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setPrecinctName(String precinctName) {
        this.precinctName = precinctName;
    }


    public void setPrecinctID(String precinctID) {
        this.precinctID = precinctID;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public void setRelevantInfo(String relevantInfo) {
        this.relevantInfo = relevantInfo;
    }
    
    
}
