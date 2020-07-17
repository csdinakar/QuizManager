/*
 * @author   : gbs03580 
 * Created on: 09-Jul-2020 5:52:53 pm
 * Project   : quizboot 
 * File Name : LeaderBoard.java
 */
package in.app.quizmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity(name = "LEADER_BOARD")
public class LeaderBoard implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,name = "USER")
    @NotNull
    @NotEmpty
    private String userName;
    
    @Column(nullable = false,name = "SCORE")
    @NotNull
    private Long score;
    
    
    @Column(nullable = false,name = "CATEGORY")
    @NotNull
    private Long categoryId;
    
    private Long totalScore;

    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getUserName() {
        return userName;
    }

    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    public Long getScore() {
        return score;
    }

    
    public void setScore(Long score) {
        this.score = score;
    }

    
    public Long getTotalScore() {
        return totalScore;
    }

    
    public void setTotalScore(Long totalScore) {
        this.totalScore = totalScore;
    }
    
    
    public LeaderBoard() {
    }


    
    public Long getCategoryId() {
        return categoryId;
    }


    
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }


   
    
    
    

}


