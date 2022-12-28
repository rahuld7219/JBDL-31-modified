package com.example.L10springjpademo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true,nullable = false)
    private String name;

    @Column
    private Double salary;

    // One employee can have one address (employee is referring address)
    @OneToOne // here, JPA(hibernate) will automatically create a field(f.k.) in the employee table to refer to the p.k. of the address table
    // we can use @JoinColumn("addressId") to specify f.k. field name in the Employee entity,
    // the specified field need not be explicitly defined in the entity
    private Address address; // must save the address(as it is referred) before saving the employee(as it is referring) otherwise get exception

    // many employees have one branch (employee is referring branch)
    @ManyToOne
    @JsonIgnoreProperties("employees")
    private Branch branch; // here, JPA(hibernate) will automatically create a field(f.k.) in the employee table(many side) to refer to the p.k. of the branch(one-side) table

}
