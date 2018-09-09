package software.doit.testapp.other.di;


import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import software.doit.testapp.App;

@Singleton
@Component(modules = {AppModule.class, AndroidSupportInjectionModule.class, ActivityBuilder.class })
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(App app);

        AppComponent build();
    }

    void inject (App app);
}
