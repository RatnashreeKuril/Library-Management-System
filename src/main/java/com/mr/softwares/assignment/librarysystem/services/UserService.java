package com.mr.softwares.assignment.librarysystem.services;
import com.mr.softwares.assignment.librarysystem.dl.pojo.*;
import com.mr.softwares.assignment.librarysystem.dl.*;
import com.mr.softwares.assignment.librarysystem.beans.*;
import com.mr.softwares.assignment.librarysystem.exception.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;
import jakarta.validation.*;
@Controller
public class UserService
{

private static final String[] membershipTypes={"REGULAR","PREMIUM"};

@Autowired
private UserRepository userRepository;
@ResponseBody
@PostMapping("/user/add")
public UserBean add(@Valid @RequestBody UserBean userBean) throws ServiceException
{
ServiceException serviceException=new ServiceException();
if(userRepository.findByEmail(userBean.getEmail())!=null) serviceException.addException("email","Email : "+userBean.getEmail()+" already exists");
if(userRepository.findByUsername(userBean.getUsername())!=null) serviceException.addException("username","Username : "+userBean.getUsername()+" already exists");
int i;
for(i=0;i<membershipTypes.length;i++) if(membershipTypes[i].equals(userBean.getMembershipType().trim().toUpperCase())) break;
if(i==membershipTypes.length) serviceException.addException("membershipType","Invalid membership type : "+userBean.getMembershipType()+" should be Regular/Premium only");
if(serviceException.hasExceptions()) throw serviceException;
User user=new User(userBean.getUsername(),userBean.getEmail(),userBean.getFirstName(),userBean.getLastName(),userBean.getIsStaff(),userBean.getIsActive(),userBean.getMembershipType());
userRepository.save(user);
userBean.setId(user.getId());
return userBean;
}
@ResponseBody
@PostMapping("/user/update")
public UserBean update(@Valid @RequestBody UserBean userBean) throws ServiceException
{
ServiceException serviceException=new ServiceException();
User u;
Optional<User> userResult=userRepository.findById(userBean.getId());
if(!userResult.isPresent()) 
{
serviceException.addException("id","Invalid id : "+userBean.getId());
throw serviceException;
}
u=userRepository.findByEmail(userBean.getEmail());
if(u!=null && u.getId()!=userBean.getId()) serviceException.addException("email","Email : "+userBean.getEmail()+" already exists");
u=userRepository.findByUsername(userBean.getUsername());
if(u!=null && u.getId()!=userBean.getId()) serviceException.addException("username","Username : "+userBean.getUsername()+" already exists");
int i;
for(i=0;i<membershipTypes.length;i++) if(membershipTypes[i].equals(userBean.getMembershipType().trim().toUpperCase())) break;
if(i==membershipTypes.length) serviceException.addException("membershipType","Invalid membership type : "+userBean.getMembershipType()+" should be Regular/Premium only");
if(serviceException.hasExceptions()) throw serviceException;
User user=new User(userBean.getUsername(),userBean.getEmail(),userBean.getFirstName(),userBean.getLastName(),userBean.getIsStaff(),userBean.getIsActive(),userBean.getMembershipType());
user.setId(userBean.getId());
userRepository.save(user);
return userBean;
}
@ResponseBody
@PostMapping("/user/delete")
public Boolean delete(@Valid @RequestParam("id") Long id) throws ServiceException
{
ServiceException serviceException=new ServiceException();
if(!exists(id))
{
serviceException.addException("id","Invalid id : "+id);
throw serviceException;
}
userRepository.deleteById(id);
return true;
}
@ResponseBody
@GetMapping("/user/getAll")
public List<User>getAll()
{
ArrayList<User> users=new ArrayList<>();
userRepository.findAll().forEach(users::add);
return users;
}
@ResponseBody
@GetMapping("/user/getById")
public User getById(@Valid @RequestParam("id") Long id) throws ServiceException
{
ServiceException serviceException=new ServiceException();
if(!exists(id))
{
serviceException.addException("id","Invalid id : "+id);
throw serviceException;
}
User user=null;
Optional<User> userResult=userRepository.findById(id);
if(userResult.isPresent()) user=userResult.get();
return user;
}
@ResponseBody
@GetMapping("/user/getByEmail")
public User getByEmail(@Valid @RequestParam("email") String email) throws ServiceException
{
ServiceException serviceException=new ServiceException();
User user=userRepository.findByEmail(email);
if(user==null)
{
serviceException.addException("email","Invalid email : "+email);
throw serviceException;
}
return user;
}
@ResponseBody
@GetMapping("/user/getByUsername")
public User getByUsername(@Valid @RequestParam("username") String username) throws ServiceException
{
ServiceException serviceException=new ServiceException();
User user=userRepository.findByUsername(username);
if(user==null)
{
serviceException.addException("username","Invalid username : "+username);
throw serviceException;
}
return user;
}
@ResponseBody
@GetMapping("/user/exists")
public Boolean exists(@Valid @RequestParam("id") Long id)
{
return userRepository.existsById(id);
}
@ResponseBody
@GetMapping("/user/count")
public Long getCount()
{
return userRepository.count();
}

public UserDetailsService userDetailsService()
{
    return new UserDetailsService(){

        @Override
        public UserDetails loadUserByUsername(String username)
        {
            User user=userRepository.findByUsername(username);
            if(user==null) throw new UsernameNotFoundException("Invalid username : "+username);
            return user;
        }
    };
    
}


}