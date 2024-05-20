package com.mr.softwares.assignment.librarysystem.dl.pojo;
import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
@Entity(name="library_branch")
public class LibraryBranch
{
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
@Valid
@NotNull(message="Name is required")
@NotBlank(message="Name is required")
@Column(name="name")
private String name;
@Column(name="location")
private String location;
@Column(name="contact_info")
private String contactInfo;
public LibraryBranch()
{
}
public LibraryBranch(String name,String location,String contactInfo)
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
public void setLocation(String location)
{
this.location=location;
}
public String getLocation()
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