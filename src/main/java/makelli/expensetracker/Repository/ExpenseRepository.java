package makelli.expensetracker.Repository;

import makelli.expensetracker.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    Optional<Expense> findByExpenseId(String id);

    List<Expense> findByNameContainingAndDateBetweenAndUserId(String name, Date startdate, Date enddate,Long id);
    List<Expense> findByDateBetweenAndUserId(Date startdate, Date enddate,Long id);

    List<Expense> findByUserId(Long id);
}
