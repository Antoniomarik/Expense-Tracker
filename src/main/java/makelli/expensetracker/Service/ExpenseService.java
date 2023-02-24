package makelli.expensetracker.Service;

import lombok.AllArgsConstructor;
import makelli.expensetracker.DTO.ExpenseDTO;
import makelli.expensetracker.Entity.Expense;
import makelli.expensetracker.Repository.ExpenseRepository;
import makelli.expensetracker.Util.DateTimeUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final ModelMapper modelMapper;

    public List<ExpenseDTO> GetAllExpenses (){
        List<Expense> list =  expenseRepository.findAll();
        List<ExpenseDTO> dtoList =  list.stream().map(this::mapToDto).collect(Collectors.toList());

        return dtoList;
    }

    private ExpenseDTO mapToDto(Expense expense){
        ExpenseDTO expenseDTO = modelMapper.map(expense,ExpenseDTO.class);
        expenseDTO.setDateString(DateTimeUtil.convertDateToString(expenseDTO.getDate()));
        return expenseDTO;
    }

    public ExpenseDTO saveExpanseDetails(ExpenseDTO expenseDTO) throws ParseException {
        //map dto to entity
        Expense expense = mapToEntity(expenseDTO);

        //save entity to db
        expenseRepository.save(expense);

        //map entiti to dto
        return mapToDto(expense);
    }

    private Expense mapToEntity(ExpenseDTO expenseDTO) throws ParseException {
       // nap the dto to entity
        Expense expense = modelMapper.map(expenseDTO,Expense.class);

        //generate expense id
        expense.setExpenseId(UUID.randomUUID().toString());

        //set expanse date
        expense.setDate(DateTimeUtil.convertStringToDate(expenseDTO.getDateString()));

        //return expanse entity
        return expense;
    }

}
