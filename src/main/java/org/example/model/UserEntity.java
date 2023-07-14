package org.example.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;
    @Column(name = "log", unique = true)
    @NotNull
    private String log;
    @Column(name = "password")
    @NotNull
    private String password;
    @Column(name = "name", length = 45)
    private String name;
    @Column(name = "surname", length = 45)
    private String surname;
    @Column(name = "telephone", length = 45)
    private String telephone;
    @Column(name = "email", length = 45)
    private String eMail;
    @Column(name = "gender")
    private Boolean gender;
    @Column
    private String address;
    @Column
    private String city;
    @Column
    private Date birthday;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<SessionEntity> sessions;


    public UserEntity(String log, String password, String name, String surname, String telephone, String eMail) {
        this.log = log;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.eMail = eMail;
    }

    public UserEntity(String log, String password) {
        this.log = log;
        this.password = password;
    }
    //    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return getRoles();
//    }
//
//    @Override
//    public String getUsername() {
//        return log;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return isActive();
//    }
}
