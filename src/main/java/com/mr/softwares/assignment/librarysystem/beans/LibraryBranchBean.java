package com.mr.softwares.assignment.librarysystem.beans;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
public class LibraryBranchBean
{
private Long id;
@Valid
@NotNull(message="Name is required")
@NotBlank(message="Name is required")
private String name;
private String location;
private String contactInfo;
public LibraryBranchBean()
{
}
public LibraryBranchBean(String name,String location,String contactInfo)
{
this.name=name;
this.location=location;
this.contactInfo=contactInfo;
}
public void setId(java.lang.Long id)
{
this.id=id;
}
public java.lang.Long getId()
{
return this.id;
}
public void setName(java.lang.String name)
{
this.name=name;
}
public java.lang.String getName()
{
return this.name;
}
public void setLocation(java.lang.String location)
{
this.location=location;
}
public java.lang.String getLocation()
{
return this.location;
}
public void setContactInfo(java.lang.String contactInfo)
{
this.contactInfo=contactInfo;
}
public java.lang.String getContactInfo()
{
return this.contactInfo;
}

}