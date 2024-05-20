package com.mr.softwares.assignment.librarysystem.beans;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.*;
public class BookBean
{
private Long id;
@Valid
@NotNull(message="Title is required")
@NotBlank(message="Title is required")
private String title;
@Valid
@NotNull(message="ISBN is required")
@NotBlank(message="ISBN is required")
private String isbn;
@Valid
@NotNull(message="Author id is required")
@Min(1)
private Long authorId;
@Valid
@NotNull(message="Published date is required")
@DateTimeFormat(pattern="yyyy-MM-dd")
private java.util.Date publishedDate;
private Integer availableCopies;
@Valid
@NotNull(message="Library branch Id is required")
@Min(1)
private Long libraryBranchId;
public BookBean()
{
this.id=0l;
this.title="";
this.isbn="";
this.authorId=0l;
this.publishedDate=null;
this.availableCopies=0;
this.libraryBranchId=0l;
}
public void setId(java.lang.Long id)
{
this.id=id;
}
public java.lang.Long getId()
{
return this.id;
}
public void setTitle(java.lang.String title)
{
this.title=title;
}
public java.lang.String getTitle()
{
return this.title;
}
public void setIsbn(java.lang.String isbn)
{
this.isbn=isbn;
}
public java.lang.String getIsbn()
{
return this.isbn;
}
public void setAuthorId(Long authorId)
{
this.authorId=authorId;
}
public Long getAuthorId()
{
return this.authorId;
}
public void setPublishedDate(java.util.Date publishedDate)
{
this.publishedDate=publishedDate;
}
public java.util.Date getPublishedDate()
{
return this.publishedDate;
}
public void setAvailableCopies(java.lang.Integer availableCopies)
{
this.availableCopies=availableCopies;
}
public java.lang.Integer getAvailableCopies()
{
return this.availableCopies;
}
public void setLibraryBranchId(Long libraryBranchId)
{
this.libraryBranchId=libraryBranchId;
}
public Long getLibraryBranchId()
{
return this.libraryBranchId;
}

}