package com.vti.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "`account`")
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", length = 50, nullable = false, unique = true, updatable = false)
    private String username;

    @Column(name = "password", length = 800, nullable = false)
    private String password;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Formula(" concat(first_name, ' ', last_name) ")
    private String fullName;

    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @PrePersist
    public void prePersist() {
        password = new BCryptPasswordEncoder().encode("123456");
    }

    @ManyToOne()
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
    private Department department;


    public Account(String username, Department department) {
        this.username = username;
        this.department = department;
    }

    public Account(int id, String username, Department department) {
        this.id = id;
        this.username = username;
        this.department = department;
    }
}
