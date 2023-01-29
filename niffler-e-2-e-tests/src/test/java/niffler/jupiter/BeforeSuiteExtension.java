package niffler.jupiter;

import niffler.config.ConfigReader;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.extension.ExtensionContext;

public class BeforeSuiteExtension implements AroundAllTestsExtension {
    @Override
    public void beforeAllTests(ExtensionContext context) {
        System.out.println("BEFORE SUITE!");

        Configuration.browserSize = ConfigReader.PROPERTIES_CONFIG.browserSize();
    }

    @Override
    public void afterAllTests() {
        System.out.println("AFTER SUITE!");
    }
}
