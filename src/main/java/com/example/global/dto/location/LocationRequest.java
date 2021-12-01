package com.example.global.dto.location;

import com.example.global.domain.entity.address.Address;
import com.example.global.domain.entity.address.Location;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class LocationRequest {

    private  String state;
    private  String city;
    private  String town;
    private  Double longitude;
    private  Double latitude;

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
