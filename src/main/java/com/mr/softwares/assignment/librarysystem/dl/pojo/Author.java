package com.mr.softwares.assignment.librarysystem.dl.pojo;
import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
@Entity(name="author")
public class Author
{
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="id")
private Long id;
@Valid
@Column(name="first_name",nullable=false)
@NotNull(message="First name required") 
@NotBlank(message="First name required") 
private String firstName;
@Valid
@Column(name="last_name",nullable=false)
@NotNull(message="Last name required") 
@NotBlank(message="Last name required")
private String lastName;
@Valid
@Column(name="date_of_birth",nullable=false)
@NotNull(message="Date of birth required") 
private java.util.Date dateOfBirth;
@Valid
@Column(name="biography",nullable=false)
private String biography;
public Author()
{
}
public Author(String firstName,String lastName,java.sql.Date dateOfBirth,String biography)
{
this.firstName=firstName;
this.lastName=lastName;
this.dateOfBirth=dateOfBirth;
this.biography=biography;
}
public void setId(java.lang.Long id)
{
this.id=id;
}
public java.lang.Long getId()
{
return this.id;
}
public void setFirstName(java.lang.String firstName)
{
this.firstName=firstName;
}
public java.lang.String getFirstName()
{
return this.firstName;
}
public void setLastName(java.lang.String lastName)
{
this.lastName=lastName;
}
public java.lang.String getLastName()
{
return this.lastName;
}
public void setDateOfBirth(java.util.Date dateOfBirth)
{
this.dateOfBirth=dateOfBirth;
}
public java.util.Date getDateOfBirth()
{
return this.dateOfBirth;
}
public void setBiography(java.lang.String biography)
{
this.biography=biography;
}
public java.lang.String getBiography()
{
return this.biography;
}

}