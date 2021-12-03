package com.example.danggeunbunny.domain.user.domain.entity;

import com.example.danggeunbunny.global.dto.Request.LocationRequest;
import com.example.danggeunbunny.global.domain.entity.address.Address;
import com.example.danggeunbunny.global.domain.entity.address.Location;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "Danggeun_User")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "char(36)", nullable = false, unique = true)
    private String email;

    @Column(columnDefinition = "char(60)", nullable = false)
    private String password;

    @Column(columnDefinition = "string(12)", nullable = false)
    private String nickname;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Embedded
    private Address address;

    @Embedded
    private Location location;


    @Builder
    public User(String email, String password, String nickname, Role role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }

    public void updateProfile(String nickname) {
        this.nickname = nickname;

    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void setUserLocationAddress(LocationRequest locationAddress) {
        this.address = locationAddress.toAddress();
        this.location = locationAddress.toLocation();
    }



}
