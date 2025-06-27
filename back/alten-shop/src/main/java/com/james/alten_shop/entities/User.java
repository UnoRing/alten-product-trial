package com.james.alten_shop.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

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

    /** TODO: Finish implementing
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_favourite_products", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {
            @JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> favouriteProducts = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_cart", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {
            @JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> cart= new ArrayList<>();
    **/
}
