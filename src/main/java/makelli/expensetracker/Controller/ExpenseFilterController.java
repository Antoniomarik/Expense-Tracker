package makelli.expensetracker.Controller;

import makelli.expensetracker.DTO.ExpenseDTO;
import makelli.expensetracker.DTO.ExpenseFilterDto;
import makelli.expensetracker.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


import java.util.List;

@Controller
public class ExpenseFilterController {

    @Autowired
    ExpenseService expenseService;

    @GetMapping("/filterExpenses")
    public String filterExpenses(@ModelAttribute("filter") ExpenseFilterDto expenseFilterDto, Model model){

        List<ExpenseDTO> list = expenseService.getFilteredExpenses(expenseFilterDto.getKeyword());

        model.addAttribute("expenses", list);
        return "expense-list";
    }
}
