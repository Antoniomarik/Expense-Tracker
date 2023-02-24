package makelli.expensetracker.Controller;

import makelli.expensetracker.DTO.ExpenseDTO;
import makelli.expensetracker.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.text.ParseException;


@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public String showExpenseList(Model model){
        model.addAttribute("expenses", expenseService.GetAllExpenses());
        return "expense-list";
    }

    @GetMapping("/createExpense")
    public String expenseForm(Model model){
        model.addAttribute("expense", new ExpenseDTO());

        return "expense-form";
    }

    @PostMapping("/saveorupdateexpense")
    public String expenseFormPOST(@ModelAttribute("expense")ExpenseDTO expenseDTO) throws ParseException {

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
