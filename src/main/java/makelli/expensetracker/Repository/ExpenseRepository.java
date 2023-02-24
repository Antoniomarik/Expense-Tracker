package makelli.expensetracker.Repository;

import makelli.expensetracker.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    Optional<Expense> findByExpenseId(String id);
}
