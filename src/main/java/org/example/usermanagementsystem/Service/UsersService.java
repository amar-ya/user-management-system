package org.example.usermanagementsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.usermanagementsystem.Model.Users;
import org.example.usermanagementsystem.Repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService
{
    private final UsersRepository usersRepository;

    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

    public void addNewUser(Users user){
        usersRepository.save(user);
    }

    public boolean updateUser(Integer id, Users user){
        Users u = usersRepository.findUsersById(id);
        if(u != null){
            u.setAge(user.getAge());
            u.setEmail(user.getEmail());
            u.setName(user.getName());
            u.setPassword(user.getPassword());
            u.setUsername(user.getUsername());
            u.setRole(user.getRole());
            usersRepository.save(u);
            return true;
        }
        return false;
    }

    public boolean deleteUser(Integer id){
        Users u = usersRepository.findUsersById(id);
        if(u != null){
            usersRepository.delete(u);
            return true;
        }
        return false;
    }

    public String Login(String username, String password){
        Users u = usersRepository.findByUsername(username);
        if(u != null){
            if(u.getPassword().equals(password)){
                return null;
            }
        } else if(u == null){
            return "invalid username";
        }
            return "invalid password";

    }

    public Users findUsersByEmail(String email){
        return usersRepository.findUsersByEmail(email);
    }

    public List<Users> findUsersByRole(String role){
        return usersRepository.findUsersByRole(role);
    }

    public List<Users> findUsersOlderThan(Integer age){
        return usersRepository.findUsersByAge(age);
    }
}
