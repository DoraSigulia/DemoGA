package com.sigulia.test;
import com.codeborne.selenide.Configuration;
import com.sigulia.pages.RegistationFormPage;
import com.sigulia.utils.GenerateFakerData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;


@DisplayName("Параметризованные тесты для формы регистрации")
public class DemoQaJunit {


    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }


    RegistationFormPage registationFormPage = new RegistationFormPage();
    static GenerateFakerData faker = new GenerateFakerData();

    @ValueSource(strings = {
            "John",
            "Дима"
    })

    @Disabled
    @ParameterizedTest(name = "Имя нового польователя {0}")
    void fillFormsWithObligatoryFieldsValueSource(String testData) {
        registationFormPage.openPage()
                .setFirstName(testData)
                .setLastName(faker.lastName)
                .setUserEmail(faker.userEmail)
                .setMobileNumber(faker.mobileNumber)
                .setAddress(faker.address)
                .setGender(faker.gender)
                .setDateCalendar(faker.day, faker.month, faker.year)
                .setSubjects(faker.subjects)
                .setHobbies(faker.hobbies)
                .setPicture(faker.namePhoto)
                .setState(faker.state)
                .setCity(faker.city)
                .clickOnSubmitButton()
                .checkResult("Student Name", "" + testData + " " + faker.lastName + "")
                .checkResult("Student Email", faker.userEmail)
                .checkResult("Gender", faker.gender)
                .checkResult("Mobile", faker.mobileNumber)
                .checkResult("Date of Birth", faker.expectedDateBirth)
                .checkResult("Subjects", faker.subjects)
                .checkResult("Hobbies", faker.hobbies)
                .checkResult("Picture", faker.namePhoto)
                .checkResult("Address", faker.address)
                .checkResult("State and City", faker.state + " " + faker.city);
    }



    @CsvSource(value = {
            "Sports | Sports",
            "Reading | Reading",
            "Music | Music"
    },
            delimiter = '|'
    )


    @ParameterizedTest(name = "Хобби нового польователя {0}, ожидаемый результат {1}")
    void fillFormsWithObligatoryFieldsCsvSource(String testData, String expectedResult) {
        registationFormPage.openPage()
                .setFirstName(faker.firstName)
                .setLastName(faker.lastName)
                .setUserEmail(faker.userEmail)
                .setMobileNumber(faker.mobileNumber)
                .setAddress(faker.address)
                .setGender(faker.gender)
                .setDateCalendar(faker.day, faker.month, faker.year)
                .setSubjects(faker.subjects)
                .setHobbies(testData)
                .setPicture(faker.namePhoto)
                .setState(faker.state)
                .setCity(faker.city)
                .clickOnSubmitButton()
                .checkResult("Student Name", faker.expectedFullName)
                .checkResult("Student Email", faker.userEmail)
                .checkResult("Gender", faker.gender)
                .checkResult("Mobile", faker.mobileNumber)
                .checkResult("Date of Birth", faker.expectedDateBirth)
                .checkResult("Subjects", faker.subjects)
                .checkResult("Hobbies", expectedResult)
                .checkResult("Picture", faker.namePhoto)
                .checkResult("Address", faker.address)
                .checkResult("State and City", faker.state + " " + faker.city);
    }


    static Stream<Arguments> fillFormsWithObligatoryFieldsMethodSource () {
        return Stream.of(
                Arguments.of("Иван", "Иванов", "Male", "Большая Садовая, 1"),
                Arguments.of(faker.firstName, faker.lastName, faker.gender, faker.address)
        );
    }

    @MethodSource
    @ParameterizedTest
    void fillFormsWithObligatoryFieldsMethodSource(String name, String lastname, String gender, String address) {
        registationFormPage.openPage()
                .setFirstName(name)
                .setLastName(lastname)
                .setUserEmail(faker.userEmail)
                .setMobileNumber(faker.mobileNumber)
                .setAddress(address)
                .setGender(gender)
                .setDateCalendar(faker.day, faker.month, faker.year)
                .setSubjects(faker.subjects)
                .setHobbies(faker.hobbies)
                .setPicture(faker.namePhoto)
                .setState(faker.state)
                .setCity(faker.city)
                .clickOnSubmitButton()
                .checkResult("Student Name", "" + name + " " + lastname + "")
                .checkResult("Student Email", faker.userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", faker.mobileNumber)
                .checkResult("Date of Birth", faker.expectedDateBirth)
                .checkResult("Subjects", faker.subjects)
                .checkResult("Hobbies", faker.hobbies)
                .checkResult("Picture", faker.namePhoto)
                .checkResult("Address", address)
                .checkResult("State and City", faker.state + " " + faker.city);
    }

}
