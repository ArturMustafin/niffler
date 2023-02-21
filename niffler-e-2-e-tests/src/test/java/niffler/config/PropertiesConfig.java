package niffler.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface PropertiesConfig extends Config {

    @Key("auth.url")
    String authUrl();

    @Key("auth.user")
    String login();

    @Key("auth.password")
    String password();

    @Key("configuration.browserSize")
    String browserSize();

}
