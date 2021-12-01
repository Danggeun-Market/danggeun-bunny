package com.example.danggeunbunny.domain.user.domain.entity;

import com.example.global.dto.location.LocationRequestDto;
import com.example.global.domain.entity.address.Address;
import com.example.global.domain.entity.address.Location;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String email;

    private String password;

    private String nickname;

    @Embedded
    private Address address;

    @Embedded
    private Location location;


    @Builder
    public User(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public void updateProfile(String nickname) {
        this.nickname = nickname;

    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void setUserLocationAddress(LocationRequestDto locationAddress) {
        this.address = locationAddress.toAddress();
        this.location = locationAddress.toLocation();
    }



}
