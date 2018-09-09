package software.doit.boxio.other.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import software.doit.boxio.view.login.LoginActivity;
import software.doit.boxio.view.login.LoginModule;
import software.doit.boxio.view.main.MainActivity;
import software.doit.boxio.view.main.MainActivityModule;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

}
