package com.mr.softwares.assignment.librarysystem.beans;
import org.springframework.format.annotation.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
public class AuthorBean
{
private Long id;
@Valid
@NotNull(message="First name required") 
@NotBlank(message="First name required")
private String firstName;
@Valid
@NotNull(message="Last name required") 
@NotBlank(message="Last name required")
private String lastName;
@Valid
@NotNull(message="Date of birth required") 
@DateTimeFormat(pattern="yyyy-MM-dd")
private java.util.Date dateOfBirth;
private String biography;
public AuthorBean()
{
this.id=0l;
this.firstName="";
this.lastName="";
this.dateOfBirth=null;
this.biography="";
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