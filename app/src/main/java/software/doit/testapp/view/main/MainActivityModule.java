package software.doit.testapp.view.main;

import dagger.Module;
import dagger.Provides;
import software.doit.testapp.model.repository.Repository;

@Module
public class MainActivityModule {

    @Provides
    MainActivityViewModel provide(Repository repository) {
        return new MainActivityViewModel(repository);
    }
}
