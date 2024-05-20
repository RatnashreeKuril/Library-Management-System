package com.mr.softwares.assignment.librarysystem.services;
import com.mr.softwares.assignment.librarysystem.dl.pojo.*;
import com.mr.softwares.assignment.librarysystem.dl.*;
import com.mr.softwares.assignment.librarysystem.beans.*;
import com.mr.softwares.assignment.librarysystem.exception.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;
import org.springframework.security.access.prepost.*;
import java.util.*;
import jakarta.validation.*;
@Controller
public class BookService
{


@Autowired
private BookRepository bookRepository;

@Autowired
private AuthorRepository authorRepository;

@Autowired
private LibraryBranchRepository libraryBranchRepository;


@ResponseBody
@PostMapping("/book/add")
@PreAuthorize("hasAuthority('USER')")
public BookBean add(@Valid @RequestBody BookBean bookBean) throws ServiceException
{

ServiceException serviceException=new ServiceException();
if(bookRepository.findByIsbn(bookBean.getIsbn())!=null) serviceException.addException("isbn","ISBN : "+bookBean.getIsbn()+" already exists");

Optional<Author> authorResult=authorRepository.findById(bookBean.getAuthorId());

if(authorResult.isPresent()==false) serviceException.addException("authorId","Invalid authorId : "+bookBean.getAuthorId());;
Author author=null;
if(authorResult.isPresent()) author=authorResult.get();

Optional<LibraryBranch> libraryBranchResult=libraryBranchRepository.findById(bookBean.getLibraryBranchId());

if(libraryBranchResult.isPresent()==false) serviceException.addException("libraryBranchId","Invalid libraryBranchId : "+bookBean.getLibraryBranchId());;
LibraryBranch libraryBranch=null;
if(libraryBranchResult.isPresent()) libraryBranch=libraryBranchResult.get();
if(serviceException.hasExceptions()) throw serviceException;

java.util.Date utilPublishedDate=bookBean.getPublishedDate();
java.sql.Date sqlPublishedDate=new java.sql.Date(utilPublishedDate.getYear(),utilPublishedDate.getMonth(),utilPublishedDate.getDate());

Book book=new Book(bookBean.getTitle(),bookBean.getIsbn(),author,sqlPublishedDate,bookBean.getAvailableCopies(),libraryBranch);
bookRepository.save(book);
bookBean.setId(book.getId());
return bookBean;
}

@ResponseBody
@PostMapping("/book/update")
@PreAuthorize("hasAuthority('USER')")
public BookBean update(@Valid @RequestBody BookBean bookBean) throws ServiceException
{
ServiceException serviceException=new ServiceException();
Optional<Book> bookResult=bookRepository.findById(bookBean.getId());
if(!bookResult.isPresent()) 
{
serviceException.addException("id","Invalid id : "+bookBean.getId());
throw serviceException;
}
Book b=bookRepository.findByIsbn(bookBean.getIsbn());
if(b!=null && b.getId()!=bookBean.getId()) serviceException.addException("isbn","ISBN : "+bookBean.getIsbn()+" already exists");

Optional<Author> authorResult=authorRepository.findById(bookBean.getAuthorId());

if(authorResult.isPresent()==false) serviceException.addException("authorId","Invalid authorId : "+bookBean.getAuthorId());;
Author author=null;
if(authorResult.isPresent()) author=authorResult.get();

Optional<LibraryBranch> libraryBranchResult=libraryBranchRepository.findById(bookBean.getLibraryBranchId());

if(libraryBranchResult.isPresent()==false) serviceException.addException("libraryBranchId","Invalid libraryBranchId : "+bookBean.getLibraryBranchId());;
LibraryBranch libraryBranch=null;
if(libraryBranchResult.isPresent()) libraryBranch=libraryBranchResult.get();
if(serviceException.hasExceptions()) throw serviceException;

java.util.Date utilPublishedDate=bookBean.getPublishedDate();
java.sql.Date sqlPublishedDate=new java.sql.Date(utilPublishedDate.getYear(),utilPublishedDate.getMonth(),utilPublishedDate.getDate());

Book book=new Book(bookBean.getTitle(),bookBean.getIsbn(),author,sqlPublishedDate,bookBean.getAvailableCopies(),libraryBranch);

book.setId(bookBean.getId());
bookRepository.save(book);
return bookBean;
}
@ResponseBody
@PostMapping("/book/delete")
public Boolean delete(@Valid @RequestParam("id") Long id) throws ServiceException
{
ServiceException serviceException=new ServiceException();
if(!exists(id))
{
serviceException.addException("id","Invalid id : "+id);
throw serviceException;
}
bookRepository.deleteById(id);
return true;
}
@ResponseBody
@GetMapping("/book/getAll")
public List<Book>getAll()
{
ArrayList<Book> books=new ArrayList<>();
bookRepository.findAll().forEach(books::add);
return books;
}
@ResponseBody
@GetMapping("/book/getById")
public Book getById(@Valid @RequestParam("id") Long id) throws ServiceException
{
ServiceException serviceException=new ServiceException();
if(!exists(id))
{
serviceException.addException("id","Invalid id : "+id);
throw serviceException;
}
Book book=null;
Optional<Book> bookResult=bookRepository.findById(id);
if(bookResult.isPresent()) book=bookResult.get();
return book;
}

@ResponseBody
@GetMapping("/book/exists")
public Boolean exists(@Valid @RequestParam("id") Long id)
{
return bookRepository.existsById(id);
}
@ResponseBody
@GetMapping("/book/count")
public Long getCount()
{
return bookRepository.count();
}



}