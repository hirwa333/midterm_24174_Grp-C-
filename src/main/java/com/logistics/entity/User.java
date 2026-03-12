package com.logistics.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String phone;
    
    @OneToOne
    @JoinColumn(name = "village_id", unique = true, nullable = false)
    private Village village;
    
    public User() {}
    
    public User(String username, String email, String phone, Village village) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.village = village;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
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
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public Village getVillage() {
        return village;
    }
    
    public void setVillage(Village village) {
        this.village = village;
    }
}
