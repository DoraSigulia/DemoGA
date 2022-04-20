package com.sigulia.test;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import com.sigulia.pages.RegistationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static java.lang.String.format;


public class DemoQaTestsFakerData {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    Faker faker = new Faker();

    String first_name = faker.name().firstName(),
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

    RegistationFormPage registationFormPage = new RegistationFormPage();

    @Test
    void fillFormsWithObligatoryFields() {
        registationFormPage.openPage()
                .setFirstName(first_name)
                .setLastName(last_name)
                .setUserEmail(user_email)
                .setMobileNumber(mobile_number)
                .setAddress(address)
                .setGender(gender)
                .setDateCalendar(day, month, year)
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .setPicture(namePhoto)
                .setState(state)
                .setCity(city)
                .clickOnSubmitButton()
                .checkResult("Student Name", expectedFullName)
                .checkResult("Student Email", user_email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobile_number)
                .checkResult("Date of Birth", expectedDateBirth)
                .checkResult("Subjects", subjects)
                .checkResult("Hobbies", hobbies)
                .checkResult("Picture", namePhoto)
                .checkResult("Address", address)
                .checkResult("State and City", state + " " + city);
    }
}
