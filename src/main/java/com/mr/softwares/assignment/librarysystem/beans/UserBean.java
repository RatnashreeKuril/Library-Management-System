package com.mr.softwares.assignment.librarysystem.beans;
import jakarta.validation.constraints.*;
import jakarta.validation.*;
public class UserBean
{
@Valid
private Long id;
@Valid
@NotNull(message="Username is required")
@NotBlank(message="Username is required")
private String username;
@Valid
@NotNull(message="Email is required")
@NotBlank(message="Email is required")
@Email(message="Invalid email")
private String email;
@Valid
@NotNull(message="First name is required")
@NotBlank(message="First name is required")
private String firstName;
@Valid
@NotNull(message="Last name is required")
@NotBlank(message="Last name is required")
private String lastName;
@Valid
@NotNull(message="Is staff is required")
private Boolean isStaff;
@Valid
@NotNull(message="Is active is required")
private Boolean isActive;
@Valid
@NotNull(message="Membership type is required")
@NotBlank(message="Membership type is required")
private String membershipType;
public UserBean()
{
this.id=0l;
this.username="";
this.email="";
this.firstName="";
this.lastName="";
this.isStaff=false;
this.isActive=false;
this.membershipType="";
}
public void setId(java.lang.Long id)
{
this.id=id;
}
public java.lang.Long getId()
{
return this.id;
}
public void setUsername(java.lang.String username)
{
this.username=username;
}
public java.lang.String getUsername()
{
return this.username;
}
public void setEmail(java.lang.String email)
{
this.email=email;
}
public java.lang.String getEmail()
{
return this.email;
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
public void setIsStaff(java.lang.Boolean isStaff)
{
this.isStaff=isStaff;
}
public java.lang.Boolean getIsStaff()
{
return this.isStaff;
}
public void setIsActive(java.lang.Boolean isActive)
{
this.isActive=isActive;
}
public java.lang.Boolean getIsActive()
{
return this.isActive;
}
public void setMembershipType(java.lang.String membershipType)
{
this.membershipType=membershipType;
}
public java.lang.String getMembershipType()
{
return this.membershipType;
}

}