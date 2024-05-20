package com.mr.softwares.assignment.librarysystem.beans;
import java.math.*;
public class FineBean
{
private Long id;
private Long borrowingRecordId;
private BigDecimal amount;
private java.sql.Date issuedDate;
private String status;
public FineBean()
{
this.id=0l;
this.borrowingRecordId=0l;
this.amount=null;
this.issuedDate=null;
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
public void setBorrowingRecordId(Long borrowingRecordId)
{
this.borrowingRecordId=borrowingRecordId;
}
public Long getBorrowingRecordId()
{
return this.borrowingRecordId;
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