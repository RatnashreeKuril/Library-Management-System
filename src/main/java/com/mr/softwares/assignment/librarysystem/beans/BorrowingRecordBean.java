package com.mr.softwares.assignment.librarysystem.beans;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.*;
import com.mr.softwares.assignment.librarysystem.dl.pojo.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.*;
public class BorrowingRecordBean
{
@Valid
@Min(1)
private Long id;
@Valid
@NotNull(message="User Id is required")
@Min(1)
private Long userId;
@Valid
@NotNull(message="Book Id is required")
@Min(1)
private Long bookId;
@DateTimeFormat(pattern="yyyy-MM-dd")
@Valid
@NotNull(message="Borrow date is required")
private java.util.Date borrowDate;
@DateTimeFormat(pattern="yyyy-MM-dd")
private java.util.Date dueDate;
@DateTimeFormat(pattern="yyyy-MM-dd")
private java.util.Date returnDate;
private String status;
@JsonInclude(Include.NON_NULL)
private Fine fine;
public BorrowingRecordBean()
{
this.id=0l;
this.userId=0l;
this.bookId=0l;
this.borrowDate=null;
this.dueDate=null;
this.returnDate=null;
this.status="";
}
public void setId(java.lang.Long id)
{
this.id=id;
}
public java.lang.Long getId()
{
return this.id;
}
public void setUserId(Long userId)
{
this.userId=userId;
}
public Long getUserId()
{
return this.userId;
}
public void setBookId(Long bookId)
{
this.bookId=bookId;
}
public Long getBookId()
{
return this.bookId;
}
public void setBorrowDate(java.util.Date borrowDate)
{
this.borrowDate=borrowDate;
}
public java.util.Date getBorrowDate()
{
return this.borrowDate;
}
public void setDueDate(java.util.Date dueDate)
{
this.dueDate=dueDate;
}
public java.util.Date getDueDate()
{
return this.dueDate;
}
public void setReturnDate(java.util.Date returnDate)
{
this.returnDate=returnDate;
}
public java.util.Date getReturnDate()
{
return this.returnDate;
}
public void setStatus(java.lang.String status)
{
this.status=status;
}
public java.lang.String getStatus()
{
return this.status;
}
public void setFine(Fine fine)
{
this.fine=fine;
}
public Fine getFine()
{
return this.fine;
}

}