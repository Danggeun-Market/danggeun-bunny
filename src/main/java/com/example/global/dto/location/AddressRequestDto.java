package com.example.global.dto.location;

import com.example.global.domain.entity.address.Address;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressRequestDto {

    @NotEmpty
    private String state;

    @NotEmpty
    private String city;

    @NotEmpty
    private String town;

    public AddressRequestDto(String state, String city, String town) {
        this.state = state;
        this.city = city;
        this.town = town;
    }

    public static Address toEntity(AddressRequestDto address) {
        return Address.builder()
                .state(address.getState())
                .city(address.getCity())
                .town(address.getTown())
                .build();
    }

}
