package com.james.alten_shop.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Entity
@Data
@EqualsAndHashCode(callSuper = false) // because we have mapped superclass @EntityBase
@Accessors(chain = true)
@Table(name = "users")
public class User extends EntityBase {

    private String firstname;
    private String username;
    private String password;

    @Column(unique = true)
    private String email;
}
