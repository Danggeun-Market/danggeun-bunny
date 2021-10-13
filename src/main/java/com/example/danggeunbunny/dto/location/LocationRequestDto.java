package com.example.danggeunbunny.dto.location;

import com.example.danggeunbunny.model.address.Address;
import com.example.danggeunbunny.model.address.Location;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@RequiredArgsConstructor
public class LocationRequestDto {

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
