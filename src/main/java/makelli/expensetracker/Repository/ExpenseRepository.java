package makelli.expensetracker.Repository;

import makelli.expensetracker.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {


}
