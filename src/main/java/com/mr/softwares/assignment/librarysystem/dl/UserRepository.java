package com.mr.softwares.assignment.librarysystem.dl;
import com.mr.softwares.assignment.librarysystem.dl.pojo.*;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
public interface UserRepository extends CrudRepository<User,Long>
{
public User findByEmail(String email);
public User findByUsername(String username);
@Query("SELECT n FROM com.mr.softwares.assignment.librarysystem.dl.pojo.User n WHERE n.isActive = true")
public List<User> findActiveUsers();
}