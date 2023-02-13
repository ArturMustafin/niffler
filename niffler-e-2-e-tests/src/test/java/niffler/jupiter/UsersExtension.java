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
import java.util.stream.Collectors;

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
        List<User> desiredUserType = Arrays.stream(context.getRequiredTestMethod()
                        .getParameters())
                .filter(p -> p.isAnnotationPresent(User.class))
                .map(p -> p.getAnnotation(User.class))
                .toList();


        Map<User.UserType, UserModel> map = new HashMap<>();
        desiredUserType.forEach(v -> {
            if (v.userType() == User.UserType.ADMIN) {
                map.put(v.userType(), USER_MODEL_ADMIN_QUEUE.poll());
            } else {
                map.put(v.userType(), USER_MODEL_COMMON_QUEUE.poll());
            }
        });


        Objects.requireNonNull(map);
        context.getStore(NAMESPACE).put(id, map);
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        String id = getTestId(context);
        final Map map = context.getStore(NAMESPACE).get(id, Map.class);
        if (map.containsKey(User.UserType.ADMIN)) {
            USER_MODEL_ADMIN_QUEUE.add((UserModel) map.get(User.UserType.ADMIN));
        } else {
            USER_MODEL_COMMON_QUEUE.add((UserModel) map.get(User.UserType.COMMON));
        }
    }

    private String getTestId(ExtensionContext context) {
        return Objects.requireNonNull(
                context.getRequiredTestMethod().getAnnotation(AllureId.class)
        ).value();
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(UserModel.class)
                && parameterContext.getParameter().isAnnotationPresent(User.class);
    }

    @Override
    public UserModel resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        String id = getTestId(extensionContext);
        return (UserModel) extensionContext.getStore(NAMESPACE).get(id, Map.class)
                .values()
                .iterator()
                .next();
    }
}
