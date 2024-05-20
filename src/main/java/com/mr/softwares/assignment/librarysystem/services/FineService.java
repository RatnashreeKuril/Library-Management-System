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
@Controller
public class FineService
{

public static final BigDecimal fineAmount=new BigDecimal(5);

@Autowired
private FineRepository fineRepository;

@Autowired
private BorrowingRecordRepository borrowingRecordRepository;


@ResponseBody
@PostMapping("/fine/pay")
public Fine payFine(@Valid @RequestParam("id") Long id) throws ServiceException
{
ServiceException serviceException=new ServiceException();
if(!exists(id))
{
serviceException.addException("id","Invalid id : "+id);
throw serviceException;
}
Optional<Fine> fineResult=fineRepository.findById(id);
Fine fine=fineResult.get();
fine.setStatus("PAID");
fineRepository.save(fine);
return fine;
}



@ResponseBody
@PostMapping("/fine/delete")
public Boolean delete(@Valid @RequestParam("id") Long id) throws ServiceException
{
ServiceException serviceException=new ServiceException();
if(!exists(id))
{
serviceException.addException("id","Invalid id : "+id);
throw serviceException;
}
fineRepository.deleteById(id);
return true;
}
@ResponseBody
@GetMapping("/fine/getAll")
public List<Fine>getAll()
{
ArrayList<Fine> fines=new ArrayList<>();
fineRepository.findAll().forEach(fines::add);
return fines;
}
@ResponseBody
@GetMapping("/fine/getById")
public Fine getById(@Valid @RequestParam("id") Long id) throws ServiceException
{
ServiceException serviceException=new ServiceException();
if(!exists(id))
{
serviceException.addException("id","Invalid id : "+id);
throw serviceException;
}
Fine fine=null;
Optional<Fine> fineResult=fineRepository.findById(id);
if(fineResult.isPresent()) fine=fineResult.get();
return fine;
}

@ResponseBody
@GetMapping("/fine/getByBorrowingRecord")
public Fine getByBorrowingRecord(@Valid @RequestParam("id") Long id) throws ServiceException
{
ServiceException serviceException=new ServiceException();

Optional<BorrowingRecord> borrowingRecordResult=borrowingRecordRepository.findById(id);
if(!borrowingRecordResult.isPresent())
{
serviceException.addException("borrowingRecordId","Invalid borrowingRecordId : "+id);
throw serviceException;
}
BorrowingRecord borrowingRecord=borrowingRecordResult.get();
Fine fine=fineRepository.findByBorrowingRecord(borrowingRecord);
if(fine==null) 
{
serviceException.setGenericException("No fine of borrowing record with id : "+id);
throw serviceException;
}

return fine;
}



@ResponseBody
@GetMapping("/fine/exists")
public Boolean exists(@Valid @RequestParam("id") Long id)
{
return fineRepository.existsById(id);
}
@ResponseBody
@GetMapping("/fine/count")
public Long getCount()
{
return fineRepository.count();
}

}