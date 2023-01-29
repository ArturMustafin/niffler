package niffler.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static niffler.config.ConfigReader.PROPERTIES_CONFIG;

public class SpendingTest extends BaseTest {

    @Test
    void successfulCheckVisibleCategory() {
        open(PROPERTIES_CONFIG.authUrl());
        $("a[href*='redirect']").click();
        $("input[name='username']").setValue(PROPERTIES_CONFIG.login());
        $("input[name='password']").setValue(PROPERTIES_CONFIG.password());
        $("button[type='submit']").click();
        $(".header__title").shouldBe(Condition.visible)
                .shouldHave(Condition.text("Niffler. The coin keeper."));
    }

}
