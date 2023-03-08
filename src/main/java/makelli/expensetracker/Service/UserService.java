package makelli.expensetracker.Service;

import lombok.RequiredArgsConstructor;
import makelli.expensetracker.DTO.UserDTO;
import makelli.expensetracker.Entity.User;
import makelli.expensetracker.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    UserRepository userRepository;

    private final ModelMapper modelMapper;

    public void saveUser(UserDTO userDTO){
        User user = mapToEntity(userDTO);
        user.setUserId(UUID.randomUUID().toString());

        userRepository.save(user);
    }

    private User mapToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO,User.class);
    }
}
