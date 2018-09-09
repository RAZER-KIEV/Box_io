package software.doit.boxio.view.main;

import dagger.Module;
import dagger.Provides;
import software.doit.boxio.model.repository.Repository;

@Module
public class MainActivityModule {

    @Provides
    MainActivityViewModel provide(Repository repository) {
        return new MainActivityViewModel(repository);
    }
}
