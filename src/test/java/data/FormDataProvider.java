package data;

import org.testng.annotations.DataProvider;

public class FormDataProvider {

    @DataProvider(name = "validFormData")
    public static Object[][] validFormData() {
        return new Object[][] {
                {
                        new TestData(
                                "Demo", "Qa", "tester123@example.com", "Female", "0123456789",
                                "15", "December", "1990", "Computer Science", "Music",
                                System.getProperty("user.dir") + "\\src\\test\\resources\\sample.jpg",
                                "123 First Street", "NCR", "Delhi"
                        )
                }
        };
    }

    @DataProvider(name = "missingMobileData")
    public static Object[][] missingMobileData() {
        return new Object[][] {
                {
                        new TestData(
                                "Demo", "Qa", "tester456@example.com", "Male", "",
                                "20", "July", "1992", "English", "Reading",
                                System.getProperty("user.dir") + "\\src\\test\\resources\\sample.jpg",
                                "456 Second Street", "Haryana", "Karnal"
                        )
                }
        };
    }
}
