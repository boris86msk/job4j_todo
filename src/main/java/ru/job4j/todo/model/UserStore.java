package ru.job4j.todo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "todo_user")
@Data
public class UserStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "u_name")
    private String name;
    @Column(name = "u_login")
    private String login;
    @Column(name = "u_password")
    private String password;
}
