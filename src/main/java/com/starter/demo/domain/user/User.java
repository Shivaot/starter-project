package com.starter.demo.domain.user;

import com.starter.demo.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Log4j2
@EntityListeners(AuditingEntityListener.class)
@Entity
public class User extends Auditable<String> implements Serializable {


    private static final long serialVersionUID = 2345617982195740956L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Size(max = 100)
    @Column(unique = true, nullable = false)
    private String username;

    @NotEmpty
    private String password;

    private boolean enabled = true;

    @ManyToMany
    private Set<Role> roles = new HashSet<>();

    @Version
    private Long version;


}
