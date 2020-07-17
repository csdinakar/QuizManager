package in.app.quizmanager.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.app.quizmanager.model.Answer;
import in.app.quizmanager.model.AnswerDto;
import in.app.quizmanager.model.LeaderBoard;
import in.app.quizmanager.model.LeaderList;
import in.app.quizmanager.model.Result;
import in.app.quizmanager.model.UserDto;
import in.app.quizmanager.service.AnswerService;
import in.app.quizmanager.service.CategoryService;
import in.app.quizmanager.service.LeaderBoardService;
import in.app.quizmanager.service.QuestionService;
import in.app.quizmanager.util.AnswerUtility;

@Controller
@PreAuthorize("isAuthenticated()")
public class QuizController {

    private static final Logger log = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    QuestionService questionService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    AnswerService answerService;
    
    @Autowired
    LeaderBoardService leaderBoardService;

    @PreAuthorize("permitAll()")
    @RequestMapping("/login")
    public String getLogin() {
        return "login";
    }
    
    @PreAuthorize("permitAll()")
    @RequestMapping("/validateLogin")
    public String getLogin(Model model, @ModelAttribute("userLogin") UserDto user) {
        log.debug("USER=======>" + ToStringBuilder.reflectionToString(user));
        log.debug("USERLLOGIN=======>" + user.getPassword());
        model.addAttribute("loginUser", user);
        return "login";
    }

    @PreAuthorize("permitAll()")
    @GetMapping({ "/", "/play" })
    public String getPlayQuiz(Model model, @RequestParam("category") Optional<Long> category,HttpServletRequest request) {
        model.addAttribute("categories", categoryService.findAll());
        
        if (category.isPresent()) {
            model.addAttribute("module", "play");
            List<Answer> answers = AnswerUtility.createAnswerList(questionService.findAll(category.get()));
            AnswerDto answerDto = new AnswerDto(answers, category.get());
            model.addAttribute("answerDto", answerDto);
            return "play";
        } else {
            model.addAttribute("module", "play");
            return "home";
        }
    }
    
    
    @PreAuthorize("permitAll()")
    @GetMapping({ "/leaderboard" })
    public String getLeaderBoard(Model model,HttpServletRequest request) {
    	List<LeaderList> leaderList = leaderBoardService.getLeaderList();
   
        
        if (!leaderList.isEmpty()) {
            model.addAttribute("leaderList", leaderList);
            model.addAttribute("module", "leaderBoard");
            return "leaderboard";
        } else {
            model.addAttribute("module", "play");
            return "home";
        }
    }

    @PostMapping("/play")
    public String checkQuiz(Model model, @ModelAttribute("answerDto") AnswerDto answerDto,HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        String user = principal.getName();
        log.debug("Principal=======>" + user);
        Boolean isUserAlreadyPlayed = false;
        if(user!=null)
        {
             isUserAlreadyPlayed = leaderBoardService.isUserAlreadyedPlayed(user, answerDto.getCategoryId());
        }
        log.debug("isUserAlreadyPlayed=======>" + isUserAlreadyPlayed);
        if(isUserAlreadyPlayed)
        {
            model.addAttribute("msg", "Hey "+user+" , you have already completed this quiz and attempted for retry..!!");
            return "error";
        }
        
  //      UserDto userDto = (UserDto) model.getAttribute("loginUser");
  //      log.debug("USER=======>" + ToStringBuilder.reflectionToString(userDto));

        answerDto.getAnswers().forEach(answer -> log.info(answer.toString()));
        List<Result> results = answerService.checkAnswer(answerDto);
        Long score = results.stream().filter(Result::isCorrect).count();
        
        
        model.addAttribute("results", results);
        model.addAttribute("score",score );
        
        
        LeaderBoard leader = new LeaderBoard();
        leader.setUserName(user);
        leader.setScore(score);
        leader.setCategoryId(answerDto.getCategoryId());;
        leaderBoardService.save(leader);
        
        List<LeaderList> leaderList = leaderBoardService.getLeaderList();
        model.addAttribute("leaderList", leaderList);
        
        return "result";
    }

}
