package com.sigulia.test;
import com.sigulia.pages.RegistationFormPage;
import com.sigulia.pages.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.sigulia.utils.GenerateFakerData;

import static io.qameta.allure.Allure.step;

@Tag("demoqa")
public class DemoQaTestsFakerData extends TestBase {


    RegistationFormPage registationFormPage = new RegistationFormPage();
    GenerateFakerData faker = new GenerateFakerData();

    @Test
    @DisplayName("Fill registration form")
    void fillRegistrationForm() {
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
