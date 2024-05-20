package com.mr.softwares.assignment.librarysystem.dl.pojo;
import jakarta.persistence.*;
import java.math.*;
@Entity(name="fine")

public class Fine
{
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="id")
private Long id;
@ManyToOne()
@JoinColumn(name="borrowing_record_id",referencedColumnName="id")
private BorrowingRecord borrowingRecord;
@Column(name="amount")
private BigDecimal amount;
@Column(name="issued_date")
private java.sql.Date issuedDate;
@Column(name="status")
private String status;

public Fine()
{
}
public Fine(BorrowingRecord borrowingRecord,BigDecimal amount,java.sql.Date issuedDate,String status)
{
this.borrowingRecord=borrowingRecord;
this.amount=amount;
this.issuedDate=issuedDate;
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
public void setBorrowingRecord(BorrowingRecord borrowingRecord)
{
this.borrowingRecord=borrowingRecord;
}
public BorrowingRecord getBorrowingRecord()
{
return this.borrowingRecord;
}
public void setAmount(java.math.BigDecimal amount)
{
this.amount=amount;
}
public java.math.BigDecimal getAmount()
{
return this.amount;
}
public void setIssuedDate(java.sql.Date issuedDate)
{
this.issuedDate=issuedDate;
}
public java.sql.Date getIssuedDate()
{
return this.issuedDate;
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