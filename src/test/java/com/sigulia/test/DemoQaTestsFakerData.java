package com.sigulia.test;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.sigulia.pages.RegistationFormPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.sigulia.utils.GenerateFakerData;

import static io.qameta.allure.Allure.step;

@Tag("for_jenkins")
public class DemoQaTestsFakerData {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    RegistationFormPage registationFormPage = new RegistationFormPage();
    GenerateFakerData faker = new GenerateFakerData();

    @Test
    void fillFormsWithObligatoryFields() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Open registration form", () -> {
            registationFormPage.openPage();
        });
        step("Fill registration form", () -> {
            registationFormPage.setFirstName(faker.firstName)
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
                    .clickOnSubmitButton();
        });
        step("Verify registered user", () -> {
            registationFormPage.checkResult("Student Name", faker.expectedFullName)
                    .checkResult("Student Email", faker.userEmail)
                    .checkResult("Gender", faker.gender)
                    .checkResult("Mobile", faker.mobileNumber)
                    .checkResult("Date of Birth", faker.expectedDateBirth)
                    .checkResult("Subjects", faker.subjects)
                    .checkResult("Hobbies", faker.hobbies)
                    .checkResult("Picture", faker.namePhoto)
                    .checkResult("Address", faker.address)
                    .checkResult("State and City", faker.state + " " + faker.city);
        });
    }
}
