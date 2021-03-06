package com.ulger.cloud.authenticationserver.api.user.data;

import com.ulger.usermanager.api.Role;

import javax.persistence.*;

@Entity
@Table(name = "role", indexes = @Index(name = "idx_1_unq_name", unique = true, columnList = "name"))
public class RoleEntity implements Role {

    @Id
    @GeneratedValue(generator = "seq_gen_role", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_gen_role", sequenceName = "seq_gen_role", allocationSize = 1)
    @Column(name="id", unique = true, nullable = false, precision = 10)
    private Long id;

    @Column(name="name", unique = true, nullable = false)
    private String name;

    public RoleEntity() {
    }

    public RoleEntity(Long id) {
        this.id = id;
    }

    public RoleEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}