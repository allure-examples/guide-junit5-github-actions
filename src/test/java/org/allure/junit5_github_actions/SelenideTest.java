package org.allure.junit5_github_actions;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.logevents.SelenideLogger;

import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;

public class SelenideTest {


    @BeforeAll
    static void setupAllureReports() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeAll
    static void setupSelenoid() {
        String selenideUrl = System.getenv("SELENIDE_URL");
        if (selenideUrl != null && !selenideUrl.isEmpty()) {
            Configuration.remote = selenideUrl;
        }

        String selenideHeadless = System.getenv("SELENIDE_HEADLESS");
        if (selenideHeadless != null && selenideHeadless.equals("true")) {
            Configuration.headless = true;
        }
    }

    @Test
    public void testMainPage() {
        Allure.step("Open main page", (step) -> {
            open("https://www.saucedemo.com");
        });

        Allure.step("The login logo should be visible", (step) -> {
            $(".login_logo").shouldBe(Condition.visible);
        });
    }

}
