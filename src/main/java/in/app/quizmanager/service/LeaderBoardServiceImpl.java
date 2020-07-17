/*
 * @author   : gbs03580 
 * Created on: 09-Jul-2020 6:01:38 pm
 * Project   : quizboot 
 * File Name : LeaderBoardServiceImpl.java
 */
package in.app.quizmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.app.quizmanager.model.LeaderBoard;
import in.app.quizmanager.model.LeaderList;
import in.app.quizmanager.repository.LeaderBoardRepository;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardService{

    
    @Autowired
    LeaderBoardRepository leaderBoardRepository;

    @Override
    public List<LeaderBoard> findAll() {
        return leaderBoardRepository.findAll();
    }

    @Override
    public LeaderBoard findByUserName(String user) {
      
        return leaderBoardRepository.findByUserName(user);
    }

    @Override
    public LeaderBoard save(LeaderBoard leaderBoard) {
        return leaderBoardRepository.saveAndFlush(leaderBoard);
    }

    @Override
    public Boolean isUserAlreadyedPlayed(String user, Long categoryId) {
        return leaderBoardRepository.findByUserNameAndCategoryId(user, categoryId).size()>0;
    }

	@Override
	public List<LeaderList> getLeaderList() {
		return leaderBoardRepository.getLeaderList();
	}

}


