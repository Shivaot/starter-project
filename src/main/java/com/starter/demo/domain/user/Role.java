package com.starter.demo.domain.user;

import com.starter.demo.audit.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Log4j2
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Role extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 4767758322281967028L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;

    @Version
    private Long version;

    public Role() {
    }

    public Role(RoleName roleName) {
        this.name = roleName;
    }

    @Getter
    public enum RoleName {
        ROLE_USER("User"),
        ROLE_ADMIN("Admin");

        private String displayName;

        RoleName(String displayName) {
            this.displayName = displayName;
        }

        public static RoleName forValue(String value) {
            return Arrays.stream(RoleName.values()).filter(roleName -> roleName.name().equals(value)).findFirst().orElse(null);
        }

        public static List<RoleName> getRolesList() {
            return Arrays.asList(ROLE_USER, ROLE_ADMIN);
        }
    }

}
