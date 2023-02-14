package niffler.test;

import niffler.api.NifflerSpendClient;
import niffler.api.NifflerUserDataClient;
import niffler.jupiter.Spend;
import niffler.jupiter.UserData;
import niffler.model.SpendJson;
import niffler.model.UserJson;
import niffler.api.NifflerUserDataServiceRestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SimpleApiTest {

    private NifflerSpendClient nsc = new NifflerSpendClient();
    private final NifflerUserDataClient nifflerUserdataClient = new NifflerUserDataClient();
    private final NifflerUserDataServiceRestAssured nifflerUserDataServiceRestAssured = new NifflerUserDataServiceRestAssured();

    @ValueSource(strings = {
            "data/spend0.json",
            "data/spend1.json"
    })
    @ParameterizedTest
    void addSpend(@Spend SpendJson spend) throws Exception {
        SpendJson created = nsc.createSpend(spend);
        Assertions.assertNotNull(created.getId());
    }

    @ValueSource(strings = {
            "data/user0.json",
            "data//user1.json"
    })
    @ParameterizedTest
    void updateUserInfo(@UserData UserJson user) throws Exception {
        UserJson updatedInfo = nifflerUserdataClient.updateUserInfo(user);
        Assertions.assertNotNull(updatedInfo.getId());
    }

    @ValueSource(strings = {
            "data/user0.json",
            "data//user1.json"
    })
    @ParameterizedTest
    void updateUserInfoWithRestAssured(@UserData UserJson user) {
        UserJson updatedInfo = nifflerUserDataServiceRestAssured.updateUser(user).as(UserJson.class);
        Assertions.assertNotNull(updatedInfo.getId());
    }
}
