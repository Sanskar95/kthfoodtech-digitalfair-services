package com.kth.foodtech.digitalfair.model;

import javax.persistence.*;


@Entity(name="fair_user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;


    @Column(unique = true)
    private String username;
    private String email;
    private Integer points;
    private Integer snakePoints;


    public User() {
        this.points = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getSnakePoints() {
        return snakePoints;
    }

    public void setSnakePoints(Integer snakePoints) {
        this.snakePoints = snakePoints;
    }
}
