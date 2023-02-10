package hvdloc.vn.doan_t3h.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user", schema = "doan_t3h", catalog = "")
@Data
public class UserEntity implements UserDetails {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "USER_NAME")
    private String userName;

    @Basic
    @Column(name = "STATUS")
    private Integer status;

    @Basic
    @Column(name = "PASSWORD")
    private String password;

    @Basic
    @Column(name = "FULL_NAME")
    private String fullName;

    @Basic
    @Column(name = "ADDRESS")
    private String address;

    @Basic
    @Column(name = "ROLE")
    private String role;

    @Basic
    @Column(name = "DATE")
    private String date;

    @Basic
    @Column(name = "PHONE")
    private Integer phone;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authoritySet = new HashSet<>();
        if (this.role != null){
            authoritySet.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return authoritySet;
    }

    @Override
    public String getUsername() {
        return this.userName;
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
        return this.status == 1 ? true : false;
    }
}
