package software.doit.boxio.view.login;

import dagger.Module;
import dagger.Provides;
import software.doit.boxio.model.repository.Repository;

@Module
public class LoginModule {

    @Provides
    LoginViewModel provideViewModel(Repository repository) {
        return new LoginViewModel(repository);
    }
}
