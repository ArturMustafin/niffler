package niffler.api;

import niffler.model.UserJson;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class NifflerUserDataClient {

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(NifflerUserDataService.nifflerUserdataUri)
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    private final NifflerUserDataService nifflerUserdataService = retrofit.create(NifflerUserDataService.class);

    public UserJson updateUserInfo(UserJson user) throws Exception {
        return nifflerUserdataService.updateUserInfo(user).execute().body();
    }
}
