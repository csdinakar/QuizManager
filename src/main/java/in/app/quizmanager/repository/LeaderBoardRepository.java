/*
 * @author   : gbs03580 
 * Created on: 09-Jul-2020 5:56:27 pm
 * Project   : quizboot 
 * File Name : LeaderBoardRepository.java
 */
package in.app.quizmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.app.quizmanager.model.LeaderBoard;
import in.app.quizmanager.model.LeaderList;

@Repository
public interface LeaderBoardRepository extends JpaRepository<LeaderBoard, Long> {

    LeaderBoard findByUserName(String userName);
    
    List<LeaderBoard> findByUserNameAndCategoryId(String userName,Long categoryId);
    
    @Query(value = "select userName as userName, sum(score) as totalScore from LEADER_BOARD group by userName order by totalScore desc")
    List<LeaderList> getLeaderList();

}


