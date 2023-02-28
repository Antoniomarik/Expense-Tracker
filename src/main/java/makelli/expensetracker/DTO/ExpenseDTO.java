package makelli.expensetracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {

    private Long id;

    private String expenseId;

    @NotBlank(message = "Expanse name cannot be empty")
    @Size(min=3, message = "Expanse name should be atleast {min} characters")
    private String name;


    private String description;

    @NotNull(message="Expanse amount cannot be empty!")
    @Min(value = 1, message = "Expense amount can not be less than 1")
    private BigDecimal amount;

    private Date date;

    private String dateString;

    // day/month/year
}
