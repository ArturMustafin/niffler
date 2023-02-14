package niffler.test;


import io.qameta.allure.AllureId;
import niffler.jupiter.User;
import niffler.jupiter.UsersExtension;
import niffler.model.UserModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static niffler.jupiter.User.UserType.ADMIN;
import static niffler.jupiter.User.UserType.COMMON;

@ExtendWith({UsersExtension.class})
public class NifflerLoginTest {

    @AllureId("1")
    @Test
    void mainPageShouldBeDisplayedAfterSuccessLogin(@User(userType = ADMIN) UserModel user1,
                                                    @User(userType = COMMON) UserModel user2) {
        System.out.println("#### Test 1 " + user1.toString());
        System.out.println("#### Test 1 " + user2.toString());

    }

    @AllureId("2")
    @Test
    void mainPageShouldBeDisplayedAfterSuccessLogin0(@User(userType = ADMIN) UserModel user) {
        System.out.println("#### Test 2 " + user.toString());

    }

    @AllureId("3")
    @Test
    void mainPageShouldBeDisplayedAfterSuccessLogin1(@User UserModel user) {
        System.out.println("#### Test 3 " + user.toString());

    }

    @AllureId("4")
    @Test
    void mainPageShouldBeDisplayedAfterSuccessLogin2(@User UserModel user) {
        System.out.println("#### Test 4 " + user.toString());

    }

    @AllureId("5")
    @Test
    void mainPageShouldBeDisplayedAfterSuccessLogin3(@User UserModel user) {
        System.out.println("#### Test 5 " + user.toString());

    }
}
