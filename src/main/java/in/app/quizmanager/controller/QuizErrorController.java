package in.app.quizmanager.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class QuizErrorController implements ErrorController {
    
    
    private static final Logger log = LoggerFactory.getLogger(QuizErrorController.class);


  @GetMapping("/error")
  public String handleError(Model model, HttpServletRequest httpRequest){
    Object status = httpRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    String message = "Something went wrong. Please try again after sometime";
    if(status != null){
      int errorCode = Integer.parseInt(status.toString());
      log.info("Errorcode "+errorCode );
      switch (errorCode){
        case 403 : message = "Access denied to resource"; break;
        case 404 : message = "Resource not found"; break;
      }
    }
    model.addAttribute("msg", message);
    return "error";
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }
}
