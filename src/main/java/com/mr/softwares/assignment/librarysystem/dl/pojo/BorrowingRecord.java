package com.mr.softwares.assignment.librarysystem.dl.pojo;
import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
@Entity(name="borrowing_record")
public class BorrowingRecord implements java.io.Serializable
{
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="id")
private Long id;
@Valid
@NotNull(message="User is required")
@ManyToOne()
@JoinColumn(name="user_id",referencedColumnName="id")
private User user;
@Valid
@NotNull(message="Book is required")
@ManyToOne()
@JoinColumn(name="book_id",referencedColumnName="id")
private Book book;

@Column(name="borrow_date")
private java.sql.Date borrowDate;
@Column(name="due_date")
private java.sql.Date dueDate;
@Column(name="return_date")
private java.sql.Date returnDate;
@Column(name="status")
private String status;

public BorrowingRecord()
{
}
public BorrowingRecord(User user,Book book,java.sql.Date borrowDate,java.sql.Date dueDate,java.sql.Date returnDate,String status)
{
this.user=user;
this.book=book;
this.borrowDate=borrowDate;
this.dueDate=dueDate;
this.returnDate=returnDate;
this.status=status;
}
public void setId(java.lang.Long id)
{
this.id=id;
}
public java.lang.Long getId()
{
return this.id;
}
public void setUser(User user)
{
this.user=user;
}
public User getUser()
{
return this.user;
}
public void setBook(Book book)
{
this.book=book;
}
public Book getBook()
{
return this.book;
}
public void setBorrowDate(java.sql.Date borrowDate)
{
this.borrowDate=borrowDate;
}
public java.sql.Date getBorrowDate()
{
return this.borrowDate;
}
public void setDueDate(java.sql.Date dueDate)
{
this.dueDate=dueDate;
}
public java.sql.Date getDueDate()
{
return this.dueDate;
}
public void setReturnDate(java.sql.Date returnDate)
{
this.returnDate=returnDate;
}
public java.sql.Date getReturnDate()
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


}