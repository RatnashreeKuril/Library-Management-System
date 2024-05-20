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
public class LibraryBranchService
{


@Autowired
private LibraryBranchRepository libraryBranchRepository;

@Autowired
private BookRepository bookRepository;


@ResponseBody
@PostMapping("/libraryBranch/add")
public LibraryBranchBean add(@Valid @RequestBody LibraryBranchBean libraryBranchBean) throws ServiceException
{
ServiceException serviceException=new ServiceException();
LibraryBranch libraryBranch=new LibraryBranch(libraryBranchBean.getName(),libraryBranchBean.getLocation(),libraryBranchBean.getContactInfo());
libraryBranchRepository.save(libraryBranch);
libraryBranchBean.setId(libraryBranch.getId());
return libraryBranchBean;
}
@ResponseBody
@PostMapping("/libraryBranch/update")
public LibraryBranchBean update(@Valid @RequestBody LibraryBranchBean libraryBranchBean) throws ServiceException
{
ServiceException serviceException=new ServiceException();
LibraryBranch lb;
Optional<LibraryBranch> libraryBranchResult=libraryBranchRepository.findById(libraryBranchBean.getId());
if(!libraryBranchResult.isPresent()) 
{
serviceException.addException("id","Invalid id : "+libraryBranchBean.getId());
throw serviceException;
}
LibraryBranch libraryBranch=new LibraryBranch(libraryBranchBean.getName(),libraryBranchBean.getLocation(),libraryBranchBean.getContactInfo());
libraryBranch.setId(libraryBranchBean.getId());
libraryBranchRepository.save(libraryBranch);
return libraryBranchBean;
}
@ResponseBody
@PostMapping("/libraryBranch/delete")
public Boolean delete(@Valid @RequestParam("id") Long id) throws ServiceException
{
ServiceException serviceException=new ServiceException();
if(!exists(id))
{
serviceException.addException("id","Invalid id : "+id);
throw serviceException;
}

Optional<LibraryBranch> libraryBranchResult=libraryBranchRepository.findById(id);
LibraryBranch libraryBranch=null;
if(libraryBranchResult.isPresent()) libraryBranch=libraryBranchResult.get();

List<Book> books=bookRepository.findByLibraryBranch(libraryBranch);
if(books.size()>0)
{
serviceException.setGenericException("Cannot delete library branch with Id : "+libraryBranch.getId()+" as it is alloted to a book");
throw serviceException;
}

libraryBranchRepository.deleteById(id);
return true;
}
@ResponseBody
@GetMapping("/libraryBranch/getAll")
public List<LibraryBranch>getAll()
{
ArrayList<LibraryBranch> libraryBranchs=new ArrayList<>();
libraryBranchRepository.findAll().forEach(libraryBranchs::add);
return libraryBranchs;
}
@ResponseBody
@GetMapping("/libraryBranch/getById")
public LibraryBranch getById(@Valid @RequestParam("id") Long id) throws ServiceException
{
ServiceException serviceException=new ServiceException();
if(!exists(id))
{
serviceException.addException("id","Invalid id : "+id);
throw serviceException;
}
LibraryBranch libraryBranch=null;
Optional<LibraryBranch> libraryBranchResult=libraryBranchRepository.findById(id);
if(libraryBranchResult.isPresent()) libraryBranch=libraryBranchResult.get();
return libraryBranch;
}

@ResponseBody
@GetMapping("/libraryBranch/exists")
public Boolean exists(@Valid @RequestParam("id") Long id)
{
return libraryBranchRepository.existsById(id);
}
@ResponseBody
@GetMapping("/libraryBranch/count")
public Long getCount()
{
return libraryBranchRepository.count();
}

}