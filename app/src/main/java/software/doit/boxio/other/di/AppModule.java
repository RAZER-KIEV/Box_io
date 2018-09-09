package software.doit.boxio.other.di;

import android.arch.persistence.room.Room;
import android.content.res.Resources;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import software.doit.boxio.App;
import software.doit.boxio.model.api.retrofit.RetroService;
import software.doit.boxio.model.api.retrofit.RetroServiceStub;
import software.doit.boxio.model.db.AppDatabase;

@Module
public class AppModule {

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(App app) {
        return new OkHttpClient.Builder().build();
    }

    @Provides
    @Singleton
    RetroService provideRetroService(App app) {
        return new RetroServiceStub();
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(App app) {
        return Room.databaseBuilder(app,
                AppDatabase.class, "database-name").build();
    }

    @Provides
    @Singleton
    Resources provideResources(App app) {
        return app.getResources();
    }

    @Provides
    @Singleton
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }
}
