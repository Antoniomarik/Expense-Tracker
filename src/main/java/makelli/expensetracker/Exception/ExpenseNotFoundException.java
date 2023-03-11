package makelli.expensetracker.Exception;

import lombok.Getter;

@Getter
public class ExpenseNotFoundException extends RuntimeException{
    private String message;

    public ExpenseNotFoundException(String message){
        this.message = message;
    }
}
