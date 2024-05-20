package com.mr.softwares.assignment.librarysystem.dl.pojo;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
@Entity(name="user")
public class User implements UserDetails
{
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="id")
private Long id;
@Column(name="username",unique=true,nullable=false)
@NotEmpty(message="Username is required")
private String username;
@Column(name="email",unique=true,nullable=false)
@NotEmpty(message="Email is required")
@Email
private String email;
@Column(name="first_name",nullable=false)
@NotEmpty(message="First name is required")
private String firstName;
@Column(name="last_name",nullable=false)
@NotEmpty(message="Last name is required")
private String lastName;
@Column(name="is_staff",nullable=false)
private Boolean isStaff;
@Column(name="is_active",nullable=false)
private Boolean isActive;
@Column(name="membership_type",nullable=false)
@NotEmpty(message="Membership type is required")
private String membershipType;
public User()
{
}
public User(String username,String email,String firstName,String lastName,Boolean isStaff,Boolean isActive,String membershipType)
{
this.username=username;
this.email=email;
this.firstName=firstName;
this.lastName=lastName;
this.isStaff=isStaff;
this.isActive=isActive;
this.membershipType=membershipType;
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


@Override
public boolean isAccountNonLocked()
{
    return true;    
}
@Override
public boolean isCredentialsNonExpired()
{
    return true;
}
@Override
public boolean isEnabled()
{
    return true;
}
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {

    return List.of(new SimpleGrantedAuthority(this.isStaff?"STAFF":"USER"));
}
@Override
public String getPassword() {
    // TODO Auto-generated method stub
    return this.username;
}
@Override
public boolean isAccountNonExpired() 
{
    // TODO Auto-generated method stub

    return this.isActive;
}
}

