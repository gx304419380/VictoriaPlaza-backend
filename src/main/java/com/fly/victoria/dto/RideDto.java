package com.fly.victoria.dto;

import com.fly.victoria.entity.Ride;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Data
public class RideDto {

    private Long id;
    private String content;
    private String destination;
    private String location;
    private Integer direction;
    private String startPoint;
    private Integer type;
    private String username;
    private Long openId;
    private String phone;
    private OffsetDateTime rideTime;
    private StartAddress startAddress;
    private EndAddress endAddress;

    @Data
    private static class StartAddress {
        private Double latitude;
        private Double longitude;
        private String name;
        private String address;
    }

    @Data
    private static class EndAddress {
        private Double latitude;
        private Double longitude;
        private String name;
        private String address;
    }


    public RideDto() {
    }

    public RideDto(Ride ride) {
        BeanUtils.copyProperties(ride, this);

        this.rideTime = ride.getRideTime().atZone(ZoneId.systemDefault()).toOffsetDateTime();

        startAddress = new StartAddress();
        endAddress = new EndAddress();

        startAddress.setAddress(ride.getStartAddressAddress());
        startAddress.setLatitude(ride.getStartAddressLatitude());
        startAddress.setLongitude(ride.getStartAddressLongitude());
        startAddress.setName(ride.getStartAddressName());

        endAddress.setAddress(ride.getEndAddressAddress());
        endAddress.setLatitude(ride.getEndAddressLatitude());
        endAddress.setLongitude(ride.getEndAddressLongitude());
        endAddress.setName(ride.getEndAddressName());
    }

    public Ride convertTo() {
        Ride ride = new Ride();
        BeanUtils.copyProperties(this, ride);

        ride.setRideTime(this.rideTime.atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime());

        if (startAddress != null) {
            ride.setStartAddressAddress(startAddress.address)
                    .setStartAddressLatitude(startAddress.latitude)
                    .setStartAddressLongitude(startAddress.longitude)
                    .setStartAddressName(startAddress.name);
        }

        if (endAddress != null) {
            ride.setEndAddressAddress(endAddress.address)
                    .setEndAddressLatitude(endAddress.latitude)
                    .setEndAddressLongitude(endAddress.longitude)
                    .setEndAddressName(endAddress.name);
        }
        return ride;

    }
}
