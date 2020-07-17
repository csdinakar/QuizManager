/*
 * @author   : gbs03580 
 * Created on: 09-Jul-2020 5:59:11 pm
 * Project   : quizboot 
 * File Name : LeaderBoardService.java
 */
package in.app.quizmanager.service;

import java.util.List;

import in.app.quizmanager.model.LeaderBoard;
import in.app.quizmanager.model.LeaderList;

public interface LeaderBoardService {

    List<LeaderBoard> findAll();
    
    LeaderBoard findByUserName(String user);
    
    Boolean isUserAlreadyedPlayed(String user,Long categoryId);
    
    LeaderBoard save(LeaderBoard leaderBoard);
    
    List<LeaderList> getLeaderList();

}


