package software.doit.testapp.view.login;

import dagger.Module;
import dagger.Provides;
import software.doit.testapp.model.repository.Repository;

@Module
public class LoginModule {

    @Provides
    LoginViewModel provideViewModel(Repository repository) {
        return new LoginViewModel(repository);
    }
}
