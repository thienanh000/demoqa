package tests;

import data.FormDataProvider;
import data.TestData;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.PracticeFormPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.qameta.allure.Severity;

@Epic("Practice Form Testing")
@Feature("Form Submission")
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class PracticeFormTest {
    WebDriver driver;
    PracticeFormPage practiceFormPage;
    private static final Logger logger = LogManager.getLogger(PracticeFormTest.class);

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        logger.info("Initializing WebDriver...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        logger.info("Navigating to the practice form page.");
        driver.get("https://demoqa.com/automation-practice-form");
        practiceFormPage = new PracticeFormPage(driver);
    }

    @Test(dataProvider = "validFormData", dataProviderClass = FormDataProvider.class, description = "Submit form with valid data")
    @Story("Valid form submission")
    @Description("Ensure that the form is successfully submitted when valid data is provided.")
    public void testFormSubmissionWithValidData(TestData data) {
        practiceFormPage.removeAds();
        logger.info("Filling out form with valid data");
        practiceFormPage.enterFirstName(data.firstName);
        practiceFormPage.enterLastName(data.lastName);
        practiceFormPage.enterEmail(data.email);
        practiceFormPage.selectGender(data.gender);
        practiceFormPage.enterMobile(data.mobile);
        practiceFormPage.selectDateOfBirth(data.birthDay, data.birthMonth, data.birthYear);
        practiceFormPage.enterSubject(data.subject);
        practiceFormPage.selectHobby(data.hobby);
        practiceFormPage.uploadPicture(data.picturePath);
        practiceFormPage.enterAddress(data.address);
        practiceFormPage.selectState(data.state);
        practiceFormPage.selectCity(data.city);
        logger.info("Submitting the form...");
        practiceFormPage.submitForm();

        logger.info("Verifying success modal!");
        Assert.assertTrue(practiceFormPage.isSuccessModalShown(), "Submission modal should be displayed!");
        Assert.assertEquals(practiceFormPage.getModalText(), "Thanks for submitting the form");
    }

    @Test(dataProvider = "missingMobileData", dataProviderClass = FormDataProvider.class, description = "Submit form without mobile number")
    @Story("Invalid form submission")
    @Description("Ensure that the form is not submitted when mobile number is missing.")
    public void testFormSubmissionWithoutMobileNumber(TestData data) {
        driver.navigate().refresh();
        practiceFormPage.removeAds();
        practiceFormPage.enterFirstName(data.firstName);
        practiceFormPage.enterLastName(data.lastName);
        practiceFormPage.enterEmail(data.email);
        practiceFormPage.selectGender(data.gender);
        practiceFormPage.selectDateOfBirth(data.birthDay, data.birthMonth, data.birthYear);
        practiceFormPage.enterSubject(data.subject);
        practiceFormPage.selectHobby(data.hobby);
        practiceFormPage.uploadPicture(data.picturePath);
        practiceFormPage.enterAddress(data.address);
        practiceFormPage.selectState(data.state);
        practiceFormPage.selectCity(data.city);
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
