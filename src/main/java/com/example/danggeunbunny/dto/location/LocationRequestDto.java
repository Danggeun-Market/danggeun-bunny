package com.example.danggeunbunny.dto.location;

import com.example.danggeunbunny.model.address.Address;
import com.example.danggeunbunny.model.address.Location;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LocationRequestDto {

    private final String state;
    private final String city;
    private final String town;
    private final Double longitude;
    private final Double latitude;

    public Address toAddress() {
        return Address.builder()
                .state(state)
                .city(city)
                .town(town)
                .build();
    }

    public Location toLocation() {
        return Location.builder()
                .longitude(longitude)
                .latitude(latitude)
                .build();
    }

}
