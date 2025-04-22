package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.PracticeFormPage;

public class PracticeFormTest {
    WebDriver driver;
    PracticeFormPage practiceFormPage;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/automation-practice-form");
        practiceFormPage = new PracticeFormPage(driver);
    }

    @Test
    public void testFormSubmissionWithValidData() {

        practiceFormPage.removeAds();
        practiceFormPage.enterFirstName("Demo");
        practiceFormPage.enterLastName("Qa");
        practiceFormPage.enterEmail("tester123@example.com");
        practiceFormPage.selectGender("Female");
        practiceFormPage.enterMobile("0123456789");
        practiceFormPage.selectDateOfBirth("15", "December", "1990");
        practiceFormPage.enterSubject("Computer Science");
        practiceFormPage.selectHobby("Music");
        practiceFormPage.uploadPicture(System.getProperty("user.dir") + "\\src\\test\\resources\\sample.jpg");
        practiceFormPage.enterAddress("123 First Street");
        practiceFormPage.selectState("NCR");
        practiceFormPage.selectCity("Delhi");
        practiceFormPage.submitForm();

        Assert.assertTrue(practiceFormPage.isSuccessModalShown(), "Submission modal should be displayed!");
        Assert.assertEquals(practiceFormPage.getModalText(), "Thanks for submitting the form");
    }

    @Test
    public void testFormSubmissionWithoutMobileNumber() {

        driver.navigate().refresh();
        practiceFormPage.removeAds();
        practiceFormPage.enterFirstName("Demo");
        practiceFormPage.enterLastName("Qa");
        practiceFormPage.enterEmail("tester456@example.com");
        practiceFormPage.selectGender("Male");
        practiceFormPage.selectDateOfBirth("20", "July", "1992");
        practiceFormPage.enterSubject("English");
        practiceFormPage.selectHobby("Reading");
        practiceFormPage.uploadPicture(System.getProperty("user.dir") + "\\src\\test\\resources\\sample.jpg");
        practiceFormPage.enterAddress("456 Second Street");
        practiceFormPage.selectState("Haryana");
        practiceFormPage.selectCity("Karnal");
        practiceFormPage.submitForm();

        String borderColor = practiceFormPage.getMobileFieldBorderColor();
        Assert.assertTrue(borderColor.contains("rgb(220, 53, 69"),
                "Mobile number field should be marked as invalid!");

        Assert.assertFalse(practiceFormPage.isSuccessModalShown(), "Practice Form should not be submitted without mobile number!");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
