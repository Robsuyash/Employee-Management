package com.suyash.Employee.Management.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.suyash.Employee.Management.Model.User;

public interface UserRepo extends JpaRepository<User, Long> {

    // Custom query methods can be defined here if needed
    
}
