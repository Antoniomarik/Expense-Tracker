package makelli.expensetracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "User name should not be blank!")
    private String name;

    @Email(message = "Enter valid email!")
    @NotBlank(message = "Please enter email!!")
    private String email;

    @NotBlank(message = "Password should not be blank!")
    @Size(min = 6, message = "Password hould be minimum {min} characters")
    private String password;


    private String confirmpassword;

}
