package com.sigulia.utils;

import com.github.javafaker.Faker;

import static java.lang.String.format;

public class GenerateFakerData {
    Faker faker = new Faker();

    public String first_name = faker.name().firstName(),
            last_name = faker.name().lastName(),
            user_email = faker.internet().emailAddress(),
            mobile_number = faker.phoneNumber().subscriberNumber(10),
            address = faker.address().fullAddress(),
            gender = "Other",
            subjects = "English",
            state = "NCR",
            city = "Delhi",
            namePhoto = "cute_cat.jpg",
            hobbies = "Sports",
            day = String.valueOf(faker.number().numberBetween(10, 30)),
            month = "March",
            year = String.valueOf(faker.number().numberBetween(1980, 2000)),
            expectedFullName = format("" + first_name + " " + last_name + ""),
            expectedDateBirth = format("" + day + " " + month + "," + year + "");

}
