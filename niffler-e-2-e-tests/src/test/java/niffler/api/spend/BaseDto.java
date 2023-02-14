package niffler.api.spend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class BaseDto {

    protected final static Gson GSON = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS")
            .create();
}
