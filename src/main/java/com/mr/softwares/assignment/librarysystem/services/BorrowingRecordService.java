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
import java.math.*;
import jakarta.validation.*;
import java.util.concurrent.*;
import org.springframework.format.annotation.*;
@Controller
public class BorrowingRecordService
{
private static final String[] statusArray={"BORROWED","RETURNED","OVERDUE"};



@Autowired
private BorrowingRecordRepository borrowingRecordRepository;

@Autowired
private UserRepository userRepository;

@Autowired
private BookRepository bookRepository;


@Autowired
private FineRepository fineRepository;


@ResponseBody
@PostMapping("/borrowingRecord/add")
public BorrowingRecordBean add(@Valid @RequestBody BorrowingRecordBean borrowingRecordBean) throws ServiceException
{

ServiceException serviceException=new ServiceException();

Optional<User> userResult=userRepository.findById(borrowingRecordBean.getUserId());

if(userResult.isPresent()==false) serviceException.addException("userId","Invalid user Id : "+borrowingRecordBean.getUserId());
User user=null;
if(userResult.isPresent()) user=userResult.get();

Optional<Book> bookResult=bookRepository.findById(borrowingRecordBean.getBookId());

if(bookResult.isPresent()==false) serviceException.addException("bookId","Invalid book Id : "+borrowingRecordBean.getBookId());
Book book=null;
if(bookResult.isPresent()) book=bookResult.get();
String status=borrowingRecordBean.getStatus().trim();
int i;
for(i=0;i<statusArray.length;i++) if(statusArray[i].equals(status.toUpperCase())) break;
if(i==statusArray.length) serviceException.addException("status","Invalid status : "+borrowingRecordBean.getStatus());

if(serviceException.hasExceptions()) throw serviceException;

java.util.Date utilBorrowDate=borrowingRecordBean.getBorrowDate();
java.sql.Date sqlBorrowDate=new java.sql.Date(utilBorrowDate.getYear(),utilBorrowDate.getMonth(),utilBorrowDate.getDate());

java.util.Date utilDueDate=borrowingRecordBean.getDueDate();
java.sql.Date sqlDueDate=new java.sql.Date(utilDueDate.getYear(),utilDueDate.getMonth(),utilDueDate.getDate());


BorrowingRecord borrowingRecord=new BorrowingRecord();
borrowingRecord.setUser(user);
borrowingRecord.setBook(book);
borrowingRecord.setBorrowDate(sqlBorrowDate);
borrowingRecord.setDueDate(sqlDueDate);
borrowingRecord.setStatus(status);
borrowingRecordRepository.save(borrowingRecord);
borrowingRecordBean.setId(borrowingRecord.getId());

return borrowingRecordBean;
}


@ResponseBody
@PostMapping("/borrowingRecord/delete")
public Boolean delete(@Valid @RequestParam("id") Long id) throws ServiceException
{
ServiceException serviceException=new ServiceException();
if(!exists(id))
{
serviceException.addException("id","Invalid id : "+id);
throw serviceException;
}
borrowingRecordRepository.deleteById(id);
return true;
}
@ResponseBody
@GetMapping("/borrowingRecord/getAll")
public List<BorrowingRecord>getAll()
{
ArrayList<BorrowingRecord> borrowingRecords=new ArrayList<>();
borrowingRecordRepository.findAll().forEach(borrowingRecords::add);
return borrowingRecords;
}
@ResponseBody
@GetMapping("/borrowingRecord/getById")
public BorrowingRecord getById(@Valid @RequestParam("id") Long id) throws ServiceException
{
ServiceException serviceException=new ServiceException();
if(!exists(id))
{
serviceException.addException("id","Invalid id : "+id);
throw serviceException;
}
BorrowingRecord borrowingRecord=null;
Optional<BorrowingRecord> borrowingRecordResult=borrowingRecordRepository.findById(id);
if(borrowingRecordResult.isPresent()) borrowingRecord=borrowingRecordResult.get();
return borrowingRecord;
}

@ResponseBody
@GetMapping("/borrowingRecord/exists")
public Boolean exists(@Valid @RequestParam("id") Long id)
{
return borrowingRecordRepository.existsById(id);
}
@ResponseBody
@GetMapping("/borrowingRecord/count")
public Long getCount()
{
return borrowingRecordRepository.count();
}

@ResponseBody
@PostMapping("/borrowingRecord/borrowBook")
public BorrowingRecordBean borrowBook(@Valid @RequestBody BorrowingRecordBean borrowingRecordBean) throws ServiceException
{
ServiceException serviceException=new ServiceException();
Optional<User> userResult=userRepository.findById(borrowingRecordBean.getUserId());

if(userResult.isPresent()==false) serviceException.addException("userId","Invalid user Id : "+borrowingRecordBean.getUserId());
User user=null;
if(userResult.isPresent()) user=userResult.get();
if(user.getIsActive()==false)
{
serviceException.setGenericException("User with Id : "+user.getId()+" is not an active user");
throw serviceException;
}


String membershipType=user.getMembershipType();

Optional<Book> bookResult=bookRepository.findById(borrowingRecordBean.getBookId());

if(bookResult.isPresent()==false) serviceException.addException("bookId","Invalid book Id : "+borrowingRecordBean.getBookId());
Book book=null;
if(bookResult.isPresent()) book=bookResult.get();

if(book.getAvailableCopies()==0) 
{
serviceException.setGenericException("Book with Id : "+book.getId()+" is not available");
throw serviceException;
}

if(serviceException.hasExceptions()) throw serviceException;


java.util.Date borrowDate=borrowingRecordBean.getBorrowDate();
Calendar calendar=Calendar.getInstance();
calendar.setTime(borrowDate);
calendar.add(Calendar.DATE,7);
if(membershipType.toUpperCase().equals("PREMIUM")) calendar.add(Calendar.DATE,7);

java.util.Date dueDate=calendar.getTime();
borrowingRecordBean.setDueDate(dueDate);
borrowingRecordBean.setStatus("Borrowed");
add(borrowingRecordBean);

book.setAvailableCopies(book.getAvailableCopies()-1);
bookRepository.save(book);

return borrowingRecordBean;

}

@ResponseBody
@PostMapping("/borrowingRecord/returnBook")
public BorrowingRecord returnBook(@Valid @RequestParam("id") Long id, @Valid @RequestParam("returnDate") @DateTimeFormat(pattern="yyyy-MM-dd") java.util.Date returnDate) throws ServiceException
{
ServiceException serviceException=new ServiceException();
Optional<BorrowingRecord> borrowingRecordResult=borrowingRecordRepository.findById(id);
if(borrowingRecordResult.isPresent()==false) 
{
serviceException.addException("id","Invalid Id : "+id);
throw serviceException;
}

BorrowingRecord borrowingRecord=borrowingRecordResult.get();
if(borrowingRecord.getStatus().toUpperCase().equals("BORROWED")==false) 
{
serviceException.setGenericException("This book is already returned");
throw serviceException;
}


User user=borrowingRecord.getUser();
String membershipType=user.getMembershipType();


Book book=borrowingRecord.getBook();




java.util.Date utilDueDate=borrowingRecord.getDueDate();
java.util.Date utilReturnDate=returnDate;
java.sql.Date sqlReturnDate=new java.sql.Date(utilReturnDate.getYear(),utilReturnDate.getMonth(),utilReturnDate.getDate());
String status="Returned";
if(utilReturnDate.after(utilDueDate))
{
status="Overdue";
}
System.out.println("Status : "+status);
borrowingRecord.setReturnDate(sqlReturnDate);
borrowingRecord.setStatus(status);
borrowingRecordRepository.save(borrowingRecord);
if(status.equals("Overdue"))
{
long returnDateInMs=utilReturnDate.getTime();
long dueDateInMs=utilDueDate.getTime();
long timeDiff=Math.abs(returnDateInMs-dueDateInMs);
long daysDiff=TimeUnit.DAYS.convert(timeDiff,TimeUnit.MILLISECONDS);

BigDecimal fineAmount=FineService.fineAmount.multiply(new BigDecimal(daysDiff));
Fine fine=new Fine(borrowingRecord,fineAmount,sqlReturnDate,"UNPAID");
fineRepository.save(fine);
}

book.setAvailableCopies(book.getAvailableCopies()+1);
bookRepository.save(book);


return borrowingRecord;

}





}