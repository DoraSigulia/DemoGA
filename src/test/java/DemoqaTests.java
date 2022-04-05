import com.codeborne.selenide.Configuration;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;


public class DemoqaTests {


    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void OpenPage() {
        open("https://demoqa.com/automation-practice-form");
    }

    @Test
    void SubmitWithoutValue() {
        $("#submit").click();
        $(".modal-content").shouldNotBe(visible);
    }

    @Test
    void FillFormsWithObligatoryFields() {
        String first_name = generatedString();
        String last_name = generatedString();
        String user_email = generatedString() + "@gmail.com";
        String mobile_number = generatedInt();
        $("#firstName").setValue(first_name);
        $("#lastName").setValue(last_name);
        $("#userEmail").setValue(user_email);
        $("#userNumber").setValue(mobile_number);
        $("#genterWrapper .custom-control-label").click();
        String gender = $("#genterWrapper .custom-control-label").getText();
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").find(byText("1999")).click();
        $(".react-datepicker__month .react-datepicker__day").click();
        String date_input = $("#dateOfBirthInput").getValue();
        String subjects = "English";
        $("#subjectsInput").sendKeys("eng");
        $(".subjects-auto-complete__option").click();
        $("#hobbiesWrapper .custom-control").click();
        $("#uploadPicture").sendKeys("C:\\Users\\22\\IdeaProjects\\QaGuruProject\\photo\\cute_cat.jpg");
        $("#submit").click();
        $(".modal-content").shouldBe(visible);
        $(byXpath("//td[contains(.,'Student Name')]/following-sibling::td")).shouldHave(text(first_name + " " + last_name));
        $(byXpath("//td[contains(.,'Student Email')]/following-sibling::td")).shouldHave(text(user_email));
        $(byXpath("//td[contains(.,'Gender')]/following-sibling::td")).shouldHave(text(gender));
        $(byXpath("//td[contains(.,'Mobile')]/following-sibling::td")).shouldHave(text(mobile_number));
        $("#closeLargeModal").click();
    }

    // Create random string
    public String generatedString() {
        int length = 6;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedString;
    }

    // Create random integer
    public String generatedInt() {
        int length = 10;
        boolean useLetters = false;
        boolean useNumbers = true;
        String generatedInt = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedInt;
    }
}
