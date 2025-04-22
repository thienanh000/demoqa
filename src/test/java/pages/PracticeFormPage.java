package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PracticeFormPage {
    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    @FindBy(id = "firstName")
    private WebElement firstNameField;
    @FindBy(id = "lastName")
    private WebElement lastNameField;
    @FindBy(id = "userEmail")
    private WebElement emailField;
    @FindBy(xpath = "//label[text()='Male']")
    private WebElement maleGender;
    @FindBy(xpath = "//label[text()='Female']")
    private WebElement femaleGender;
    @FindBy(xpath = "//label[text()='Other']")
    private WebElement otherGender;
    @FindBy(id = "userNumber")
    private WebElement mobileField;
    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirthInput;
    @FindBy(className = "react-datepicker__month-select")
    private WebElement monthSelect;
    @FindBy(className = "react-datepicker__year-select")
    private WebElement yearSelect;
    @FindBy(xpath = "//div[contains(@class, 'react-datepicker__day--0')]")
    private WebElement daySelect;
    @FindBy(id = "subjectsInput")
    private WebElement subjectInput;
    @FindBy(xpath = "//label[text()='Sports']")
    private WebElement sportsHobby;
    @FindBy(xpath = "//label[text()='Reading']")
    private WebElement readingHobby;
    @FindBy(xpath = "//label[text()='Music']")
    private WebElement musicHobby;
    @FindBy(id = "uploadPicture")
    private WebElement uploadPictureButton;
    @FindBy(id = "currentAddress")
    private WebElement addressField;
    @FindBy(id = "react-select-3-input")
    private WebElement stateField;
    @FindBy(id = "react-select-4-input")
    private WebElement cityField;
    @FindBy(id = "submit")
    private WebElement submitButton;
    @FindBy(id = "example-modal-sizes-title-lg")
    private WebElement successModalTitle;
    @FindBy(id = "example-modal-sizes-title-lg")
    private List<WebElement> successModalTitleList;

    public PracticeFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void selectGender(String gender) {
        switch (gender.toLowerCase()) {
            case "male" -> maleGender.click();
            case "female" -> femaleGender.click();
            case "other" -> otherGender.click();
        }
    }

    public void enterMobile(String mobile) {
        mobileField.sendKeys(mobile);
    }

    public void selectDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        new Select(monthSelect).selectByVisibleText(month);
        new Select(yearSelect).selectByVisibleText(year);
        driver.findElement(By.xpath("//div[contains(@class, 'react-datepicker__day--0" + day + "')]")).click();
    }

    public void enterSubject(String subject) {
        subjectInput.sendKeys(subject);
        subjectInput.sendKeys(Keys.ENTER);
    }

    public void selectHobby(String hobby) {
        switch (hobby.toLowerCase()) {
            case "sports" -> sportsHobby.click();
            case "reading" -> readingHobby.click();
            case "music" -> musicHobby.click();
        }
    }

    public void uploadPicture(String filePath) {
        uploadPictureButton.sendKeys(filePath);
    }

    public void enterAddress(String address) {
        addressField.sendKeys(address);
    }

    public void selectState(String stateName) {
        stateField.sendKeys(stateName);
        stateField.sendKeys(Keys.ENTER);
    }

    public void selectCity(String cityName) {
        cityField.sendKeys(cityName);
        cityField.sendKeys(Keys.ENTER);
    }

    public void submitForm() {
        submitButton.click();
    }

    public boolean isSuccessModalShown() {
        return successModalTitleList.size() > 0;
    }

    public String getModalText() {
        return successModalTitle.getText();
    }

    public String getMobileFieldBorderColor() {
        wait.until(ExpectedConditions.attributeContains(mobileField, "border-color", "rgb(220, 53, 69"));
        return mobileField.getCssValue("border-color");
    }

    public void removeAds() {
        ((JavascriptExecutor) driver).executeScript("document.querySelector('footer')?.remove();");
        ((JavascriptExecutor) driver).executeScript("document.getElementById('fixedban')?.remove();");
    }
}
