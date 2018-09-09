package software.doit.testapp.view;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import software.doit.testapp.model.repository.Repository;

public abstract class BaseViewModel extends ViewModel {

    protected final CompositeDisposable disposables = new CompositeDisposable();

    protected Repository repository;
    public MutableLiveData<String> toastMessage = new MutableLiveData<>();

    public BaseViewModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
