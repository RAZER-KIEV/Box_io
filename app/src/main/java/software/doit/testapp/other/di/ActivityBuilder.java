package software.doit.testapp.other.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import software.doit.testapp.view.login.LoginActivity;
import software.doit.testapp.view.login.LoginModule;
import software.doit.testapp.view.main.MainActivity;
import software.doit.testapp.view.main.MainActivityModule;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

}
