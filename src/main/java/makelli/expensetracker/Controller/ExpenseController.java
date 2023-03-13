package makelli.expensetracker.Controller;

import makelli.expensetracker.DTO.ExpenseDTO;
import makelli.expensetracker.DTO.ExpenseFilterDto;
import makelli.expensetracker.Entity.Expense;
import makelli.expensetracker.Service.ExpenseService;
import makelli.expensetracker.Util.DateTimeUtil;
import makelli.expensetracker.validator.ExpenseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;


@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public String showExpenseList(Model model) throws ParseException {
        List<ExpenseDTO> list = expenseService.GetAllExpenses();
        BigDecimal totalexpenses = expenseService.totalExpenses(list);

        model.addAttribute("expenses", list);
        model.addAttribute("filter", new ExpenseFilterDto(DateTimeUtil.getCurrentMonthStartDate(),DateTimeUtil.getCurrentMonthDate()));
        model.addAttribute("sum", totalexpenses);
        return "expense-list";
    }

    @GetMapping("/createExpense")
    public String expenseForm(Model model){
        model.addAttribute("expense", new ExpenseDTO());

        return "expense-form";
    }

    @PostMapping("/saveorupdateexpense")
    public String expenseFormPOST(@Valid @ModelAttribute("expense")ExpenseDTO expenseDTO, BindingResult result) throws ParseException {
        new ExpenseValidator().validate(expenseDTO,result);

        if(result.hasErrors()){
            return "expense-form";
        }
        expenseService.saveExpanseDetails(expenseDTO);

        return "redirect:/expenses";
    }

    @GetMapping("/deleteExpense")
    public String deleteExpense(@RequestParam String id){

        expenseService.deleteExpense(id);

        return "redirect:/expenses";
    }

    @GetMapping("/updateExpense")
    public String updateExpense (@RequestParam String id, Model model){
        ExpenseDTO expense =  expenseService.getExpenseById(id);

        model.addAttribute("expense", expense);
        return "expense-form";
    }
}
