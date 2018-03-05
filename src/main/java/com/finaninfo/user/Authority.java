package com.finaninfo.user;

import com.finaninfo.config.BaseEntity;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

public class Authority extends BaseEntity implements GrantedAuthority  {
    public Authority() {
    }

    public Authority(User user, Role authority) {
        this.user = user;
        this.authority = authority;
    }

    public Authority(String string) {
        this.authority = Role.valueOf(string);
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role authority;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority.toString();
    }

    public void setAuthority(Role authority) {
        this.authority = authority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(authority, user);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Authority other = (Authority) obj;
        return Objects.equals(this.authority, other.authority)
                && Objects.equals(this.user, other.user);
    }

    //Avoid fetching lazy collections at this stage (session may be closed)
    @Override
    public String toString() {
        return "Authority [id="+id+", authority=" + authority + "]";
    }
}
