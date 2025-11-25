package org.example.usermanagementsystem.Repository;

import org.example.usermanagementsystem.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>
{
    public Users findUsersById(Integer id);

    @Query("select u from Users u where u.username = :username")
    public Users findByUsername(String username);

    Users findUsersByEmail(String email);

    @Query("select u from Users u where u.role = :role")
    List<Users> findUsersByRole(String role);

    @Query("select u from Users u where u.age >= :age")
    List<Users> findUsersByAge(Integer age);
}
