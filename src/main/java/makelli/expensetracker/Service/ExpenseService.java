package makelli.expensetracker.Service;

import lombok.AllArgsConstructor;
import makelli.expensetracker.DTO.ExpenseDTO;
import makelli.expensetracker.DTO.ExpenseFilterDto;
import makelli.expensetracker.Entity.Expense;
import makelli.expensetracker.Entity.User;
import makelli.expensetracker.Repository.ExpenseRepository;
import makelli.expensetracker.Util.DateTimeUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final ModelMapper modelMapper;

    @Autowired
    UserService userService;

    public List<ExpenseDTO> GetAllExpenses () throws ParseException {
        User user = userService.getLoggedInUser();
        //List<Expense> list =  expenseRepository.findByUserId(user.getId());

        
        Date endDate =  DateTimeUtil.convertStringToDate(DateTimeUtil.getCurrentMonthDate());
        Date startDate = DateTimeUtil.convertStringToDate(DateTimeUtil.getCurrentMonthStartDate());

        List<Expense> list = expenseRepository.findByDateBetweenAndUserId(startDate,endDate,user.getId());
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
        //add logged in user to expanse entity
        expense.setUser(userService.getLoggedInUser());
        //handle exception for future dates
        if(!expense.getDate().before(new java.util.Date())){
            throw new RuntimeException("Future date is not allowed!");
        }
        //save entity to db
        expenseRepository.save(expense);

        //map entiti to dto
        return mapToDto(expense);
    }

    private Expense mapToEntity(ExpenseDTO expenseDTO) throws ParseException {
       // map the dto to entity
        Expense expense = modelMapper.map(expenseDTO,Expense.class);
        //generate expense id
        if(expense.getId() == null){
        expense.setExpenseId(UUID.randomUUID().toString());
        }
        //set expanse date
        expense.setDate(DateTimeUtil.convertStringToDate(expenseDTO.getDateString()));

        //return expanse entity 
        return expense;
    }

    public void deleteExpense (String id){
        Expense existingexpense = expenseRepository.findByExpenseId(id).orElseThrow(() -> new RuntimeException("not found"));
        expenseRepository.delete(existingexpense);
    }

    public ExpenseDTO getExpenseById(String id){
        Expense existingexpense = expenseRepository.findByExpenseId(id).orElseThrow(() -> new RuntimeException("not found"));
        return mapToDto(existingexpense);

    }

    public List<ExpenseDTO> getFilteredExpenses (ExpenseFilterDto expenseFilterDto) throws ParseException {
        User user = userService.getLoggedInUser();

        String keyword = expenseFilterDto.getKeyword();
        String sortby = expenseFilterDto.getSortBy();
        String startdateString = expenseFilterDto.getStartDate();
        String enddateString = expenseFilterDto.getEndDate();

        Date startdate = !startdateString.isEmpty() ? DateTimeUtil.convertStringToDate(startdateString) : new Date(0);
        Date enddate = !enddateString.isEmpty() ? DateTimeUtil.convertStringToDate(enddateString) : new Date(System.currentTimeMillis());

        List<Expense> list = !keyword.isEmpty() ? expenseRepository.findByNameContainingAndDateBetweenAndUserId(keyword,startdate,enddate,user.getId()) : expenseRepository.findByDateBetweenAndUserId(startdate,enddate,user.getId());
        List<ExpenseDTO> dtoList = new java.util.ArrayList<>(list.stream().map(this::mapToDto).toList());

        if(sortby.equals("date")){
            dtoList.sort( (ob1,ob2) -> ob2.getDate().compareTo(ob1.getDate()));
        }else
            dtoList.sort( (ob1,ob2) -> ob2.getAmount().compareTo(ob1.getAmount()));
        return dtoList;
    }

    public BigDecimal totalExpenses (List<ExpenseDTO> expenses){
        BigDecimal sum = new BigDecimal(0);
        return expenses.stream().map(x -> x.getAmount().add(sum)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
