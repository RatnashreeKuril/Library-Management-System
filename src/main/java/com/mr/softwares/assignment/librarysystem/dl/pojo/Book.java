package com.mr.softwares.assignment.librarysystem.dl.pojo;
import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
@Entity(name="book")
public class Book
{
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="id")
private Long id;
@Valid
@NotNull(message="Title is required")
@NotBlank(message="Title is required")
@Column(name="title")
private String title;
@Column(name="isbn",unique=true)
@Valid
@NotNull(message="ISBN is required")
@NotBlank(message="ISBN is required")
private String isbn;
@ManyToOne()
@JoinColumn(name="author_id",referencedColumnName="id")
@Valid
@NotNull(message="Author is required")
private Author author;
@Column(name="published_date")
@Valid
@NotNull(message="Published date is required")
private java.sql.Date publishedDate;
@Column(name="available_copies")
private Integer availableCopies;
@ManyToOne()
@JoinColumn(name="library_branch_id",referencedColumnName="id")
@Valid
@NotNull(message="Library branch is required")
private LibraryBranch libraryBranch;
public Book()
{
}
public Book(String title,String isbn,Author author,java.sql.Date publishedDate,Integer availableCopies,LibraryBranch libraryBranch)
{
this.title=title;
this.isbn=isbn;
this.author=author;
this.publishedDate=publishedDate;
this.availableCopies=availableCopies;
this.libraryBranch=libraryBranch;
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
public void setAuthor(Author author)
{
this.author=author;
}
public Author getAuthor()
{
return this.author;
}
public void setPublishedDate(java.sql.Date publishedDate)
{
this.publishedDate=publishedDate;
}
public java.sql.Date getPublishedDate()
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
public void setLibraryBranch(LibraryBranch libraryBranch)
{
this.libraryBranch=libraryBranch;
}
public LibraryBranch getLibraryBranch()
{
return this.libraryBranch;
}

}