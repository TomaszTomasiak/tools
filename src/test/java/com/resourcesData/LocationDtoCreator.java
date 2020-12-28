package com.resourcesData;


import com.dto.LocationDto;

public class LocationDtoCreator {

    public static long ID = 1L;
    public static String COUNTRY = "Poland";
    public static String CITY = "Warszawa";
    public static String ZIPCODE = "00-950";
    public static String ADRESS = "Miła 7";
    public static String EMAIL = "biuro@toolrent.pl";
    public static String PHONE = "225846697";

    public static long ID_UPDATED = 1L;
    public static String COUNTRY_UPDATED = "Slovakia";
    public static String CITY_UPDATED = "Bratislava";
    public static String ZIPCODE_UPDATED = "744441";
    public static String ADRESS_UPDATED = "Zatorska 24";
    public static String EMAIL_UPDATED = "office@toolrent.sk";
    public static String PHONE_UPDATED = "555521332";

    public static LocationDto locationDtoCreator() {
        return LocationDto.builder()
                //.id(ID)
                .country(COUNTRY)
                .city(CITY)
                .zipCode(ZIPCODE)
                .addres(ADRESS)
                .email(EMAIL)
                .phone(PHONE)
                .build();
    }

    public static LocationDto updatedLocationDtoCreator() {
        return LocationDto.builder()
                //.id(ID_UPDATED)
                .country(COUNTRY_UPDATED)
                .city(CITY_UPDATED)
                .zipCode(ZIPCODE_UPDATED)
                .addres(ADRESS_UPDATED)
                .email(EMAIL_UPDATED)
                .phone(PHONE_UPDATED)
                .build();
    }
}
/*
private long id;
    private String country;
    private String city;
    private String zipCode;
    private String addres;
    private String email;
    private String phone;
 */