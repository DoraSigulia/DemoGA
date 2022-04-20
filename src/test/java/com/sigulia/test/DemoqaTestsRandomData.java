package com.sigulia.test;
import com.codeborne.selenide.Configuration;
import com.sigulia.pages.RegistationFormPage;
import com.sigulia.utils.GenerateFakerData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.sigulia.utils.GenerateRandomData.generatedString;
import static com.sigulia.utils.GenerateRandomData.generatedInt;
import static java.lang.String.format;


public class DemoqaTestsRandomData {


    GenerateFakerData faker = new GenerateFakerData();
    String first_name = generatedString(),
            last_name = generatedString(),
            user_email = generatedString() + "@gmail.com",
            mobile_number = generatedInt(),
            address = generatedString(),
            gender = faker.gender,
            day = faker.day,
            month = faker.month,
            year = faker.year,
            hobbies = faker.hobbies,
            namePhoto = faker.namePhoto,
            subjects = faker.subjects,
            state = faker.state,
            city = faker.city,
            expectedFullName = format("" + first_name + " " + last_name + ""),
            expectedDateBirth = format("" + day + " " + month + "," + year + "");


    RegistationFormPage registationFormPage = new RegistationFormPage();

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

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