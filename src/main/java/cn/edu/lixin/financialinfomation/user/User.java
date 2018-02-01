package cn.edu.lixin.financialinfomation.user;


import cn.edu.lixin.financialinfomation.config.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.Nullable;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.GregorianCalendar;

@Entity
public class User extends BaseEntity implements UserDetails{
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Column
    private String username;

    @Column
    @JsonIgnore
//    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")
    private String password;

    @Column
    private String fullname;

//    @JsonIgnore
    private String role;

    @Column
    @DateTimeFormat
    @Nullable
    private GregorianCalendar birthday;

    @Column
    @Nullable
    private Integer phoneNo;

    @Column
    @Email
    @Nullable
    private String email;

    @Column
    @Nullable
    private String city;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public GregorianCalendar getBirthday() {
        return birthday;
    }

    public void setBirthday(GregorianCalendar birthday) {
        this.birthday = birthday;
    }

    public Integer getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Integer phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User() {
        super();
    }

    public User( String username, String password, String fullname, String role) {
        this();
        this.username = username;
        setPassword(password);
        this.fullname = fullname;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoles() {
        return role;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
