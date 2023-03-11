package makelli.expensetracker.Exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(ExpenseNotFoundException.class)
    public String handleExpenseNotFoundExpeciton(HttpServletRequest request, ExpenseNotFoundException exception, Model model){
        model.addAttribute("notFound",true);
        model.addAttribute("message", exception.getMessage());

        return "response";
    }

    @ExceptionHandler(Exception.class)
    public String handleGlobalException(HttpServletRequest request, Exception exception, Model model){
        model.addAttribute("serverError",true);
        model.addAttribute("message", exception.getMessage());
        model.addAttribute("stackTrace",exception.getStackTrace());

        return "response";
    }
}
