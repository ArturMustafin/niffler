package niffler.jupiter;

import io.qameta.allure.AllureId;
import niffler.model.UserModel;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import static niffler.jupiter.User.UserType.ADMIN;

public class UsersExtension implements
        BeforeTestExecutionCallback,
        AfterTestExecutionCallback,
        ParameterResolver {

    public static final ExtensionContext.Namespace NAMESPACE
            = ExtensionContext.Namespace.create(UsersExtension.class);

    private static final Queue<UserModel> USER_MODEL_ADMIN_QUEUE = new ConcurrentLinkedQueue<>();
    private static final Queue<UserModel> USER_MODEL_COMMON_QUEUE = new ConcurrentLinkedQueue<>();

    static {
        USER_MODEL_ADMIN_QUEUE.add(new UserModel("dima", "dima"));
        USER_MODEL_COMMON_QUEUE.add(new UserModel("bill", "12345"));
        USER_MODEL_COMMON_QUEUE.add(new UserModel("test", "test"));
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        String id = getTestId(context);
        List<User.UserType> desiredUserType = Arrays.stream(context.getRequiredTestMethod().getParameters())
                .filter(p -> p.isAnnotationPresent(User.class))
                .map(p -> p.getAnnotation(User.class))
                .map(User::userType).toList();

        Map<UserModel, User.UserType> map = new HashMap<>();

        for (User.UserType type : desiredUserType) {
            UserModel user = null;
            while (user == null) {
                user = type == ADMIN
                        ? USER_MODEL_ADMIN_QUEUE.poll()
                        : USER_MODEL_COMMON_QUEUE.poll();
            }
            map.put(user, type);
            context.getStore(NAMESPACE).put(id, map);
        }
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        getUsersStorage(context).forEach((userModel, userType) -> {
            if (userType == ADMIN) {
                USER_MODEL_ADMIN_QUEUE.add(userModel);
            } else {
                USER_MODEL_COMMON_QUEUE.add(userModel);
            }
        });
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(UserModel.class)
                && parameterContext.getParameter().isAnnotationPresent(User.class);
    }

    @Override
    public UserModel resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return getUsersStorage(extensionContext).entrySet().stream()
                .filter(entry -> parameterContext.getParameter().getAnnotation(User.class).userType().equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst().orElseThrow();
    }

    private String getTestId(ExtensionContext context) {
        return Objects.requireNonNull(
                context.getRequiredTestMethod().getAnnotation(AllureId.class)
        ).value();
    }

    @SuppressWarnings("unchecked")
    private Map<UserModel, User.UserType> getUsersStorage(final ExtensionContext context) {
        return context.getStore(NAMESPACE).get(getTestId(context), Map.class);
    }
}
