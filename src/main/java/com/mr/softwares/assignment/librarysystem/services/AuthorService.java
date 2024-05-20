package com.mr.softwares.assignment.librarysystem.services;
import com.mr.softwares.assignment.librarysystem.dl.pojo.*;
import com.mr.softwares.assignment.librarysystem.dl.*;
import com.mr.softwares.assignment.librarysystem.beans.*;
import com.mr.softwares.assignment.librarysystem.exception.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;
import java.util.*;
import jakarta.validation.*;
@Controller
public class AuthorService
{

@Autowired
private AuthorRepository authorRepository;


@Autowired
private BookRepository bookRepository;

@ResponseBody
@PostMapping("/author/add")
public AuthorBean add(@Valid @RequestBody AuthorBean authorBean) throws ServiceException
{
java.util.Date utilDateOfBirth=authorBean.getDateOfBirth();
java.sql.Date sqlDateOfBirth=new java.sql.Date(utilDateOfBirth.getYear(),utilDateOfBirth.getMonth(),utilDateOfBirth.getDate());
Author author=new Author(authorBean.getFirstName(),authorBean.getLastName(),sqlDateOfBirth,authorBean.getBiography());
authorRepository.save(author);
authorBean.setId(author.getId());
return authorBean;
}
@ResponseBody
@PostMapping("/author/update")
public AuthorBean update(@Valid @RequestBody AuthorBean authorBean) throws ServiceException
{
ServiceException serviceException=new ServiceException();
Author au;
Optional<Author> authorResult=authorRepository.findById(authorBean.getId());
if(!authorResult.isPresent()) 
{
serviceException.addException("id","Invalid id : "+authorBean.getId());
throw serviceException;
}
if(serviceException.hasExceptions()) throw serviceException;
java.util.Date utilDateOfBirth=authorBean.getDateOfBirth();
java.sql.Date sqlDateOfBirth=new java.sql.Date(utilDateOfBirth.getYear(),utilDateOfBirth.getMonth(),utilDateOfBirth.getDate());
Author author=new Author(authorBean.getFirstName(),authorBean.getLastName(),sqlDateOfBirth,authorBean.getBiography());
author.setId(authorBean.getId());
authorRepository.save(author);
return authorBean;
}
@ResponseBody
@PostMapping("/author/delete")
public Boolean delete(@Valid @RequestParam("id") Long id) throws ServiceException
{
ServiceException serviceException=new ServiceException();
if(!exists(id))
{
serviceException.addException("id","Invalid id : "+id);
throw serviceException;
}
Optional<Author> authorResult=authorRepository.findById(id);
Author author=null;
if(authorResult.isPresent()) author=authorResult.get();

List<Book> books=bookRepository.findByAuthor(author);
if(books.size()>0)
{
serviceException.setGenericException("Cannot delete author with Id : "+author.getId()+" as it is alloted to a book");
throw serviceException;
}

authorRepository.deleteById(id);
return true;
}
@ResponseBody
@GetMapping("/author/getAll")
public List<Author>getAll()
{
ArrayList<Author> authors=new ArrayList<>();
authorRepository.findAll().forEach(authors::add);
return authors;
}
@ResponseBody
@GetMapping("/author/getById")
public Author getById(@Valid @RequestParam("id") Long id) throws ServiceException
{
ServiceException serviceException=new ServiceException();
if(!exists(id))
{
serviceException.addException("id","Invalid id : "+id);
throw serviceException;
}
Author author=null;
Optional<Author> authorResult=authorRepository.findById(id);
if(authorResult.isPresent()) author=authorResult.get();
return author;
}


@ResponseBody
@GetMapping("/author/exists")
public Boolean exists(@Valid @RequestParam("id") Long id)
{
return authorRepository.existsById(id);
}
@ResponseBody
@GetMapping("/author/count")
public Long getCount()
{
return authorRepository.count();
}

}