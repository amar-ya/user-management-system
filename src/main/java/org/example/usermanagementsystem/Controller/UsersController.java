package org.example.usermanagementsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.usermanagementsystem.Api.ApiResponse;
import org.example.usermanagementsystem.Model.Users;
import org.example.usermanagementsystem.Service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsersController
{
    private final UsersService usersService;

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllUsers(){
        List<Users> users = usersService.getAllUsers();
        if(users.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("there are no registered users yet"));
        }else {
            return ResponseEntity.status(200).body(users);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid Users user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }else {
            usersService.addNewUser(user);
            return ResponseEntity.status(201).body(new ApiResponse("User added successfully"));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody @Valid Users user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }else {
            boolean updated = usersService.updateUser(id, user);
            if(updated){
                return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
            }else {
                return ResponseEntity.status(400).body(new ApiResponse("User not found"));
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        boolean deleted = usersService.deleteUser(id);
        if(deleted){
            return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("User not found"));
        }
    }

    @GetMapping("/login/{username}/{password}")
    public ResponseEntity<?> login(@PathVariable String username, @PathVariable String password){
        String login = usersService.Login(username, password);
        if (login != null) {
            return ResponseEntity.status(400).body(new ApiResponse(login));
        }else {
            return ResponseEntity.status(200).body(new ApiResponse("login successfully"));
        }
    }

    @GetMapping("/get/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        Users u = usersService.findUsersByEmail(email);
        if(u == null){
            return ResponseEntity.status(404).body(new ApiResponse("there are no registered user with this email: " + email));
        }else {
            return ResponseEntity.status(200).body(u);
        }
    }

    @GetMapping("/get/role/{role}")
    public ResponseEntity<?> getUserByRole(@PathVariable String role){
        if (!role.equals("admin")&&!role.equals("user")) {
            return ResponseEntity.status(400).body(new ApiResponse("role not valid it can be only admin or user"));
        }
        List<Users> u = usersService.findUsersByRole(role);
        if(u.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("there are no registered users with this role: " + role));
        }else {
            return ResponseEntity.status(200).body(u);
        }
    }

    @GetMapping("/get/age/{age}")
    public ResponseEntity<?> getUserByAge(@PathVariable Integer age){
        List<Users> u = usersService.findUsersOlderThan(age);
        if(u.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("there are no users that is older than " + age + " years"));
        }else {
            return ResponseEntity.status(200).body(u);
        }
    }


}
