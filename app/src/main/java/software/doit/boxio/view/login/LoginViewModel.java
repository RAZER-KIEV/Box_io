package software.doit.boxio.view.login;

import android.arch.lifecycle.MutableLiveData;
import android.view.View;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import software.doit.boxio.model.data.User;
import software.doit.boxio.model.repository.Repository;
import software.doit.boxio.other.utils.Const;
import software.doit.boxio.other.utils.StringValidator;
import software.doit.boxio.view.BaseViewModel;
import timber.log.Timber;

public class LoginViewModel extends BaseViewModel {

    public String mail = "valid@mail.com";
    public String pass = "validPass";
    public String name = "validName";
    public MutableLiveData<Boolean> goToMainActivity = new MutableLiveData<>();
    public MutableLiveData<Boolean> enableRegisterMode = new MutableLiveData<>();
    public MutableLiveData<Boolean> showProgress = new MutableLiveData<>();

    private int userCounter = 0;
    private String errorMessage;
    private User currentUser;

    public LoginViewModel(Repository repository) {
        super(repository);
    }

    public void onMailChanged(CharSequence s, int start, int before, int count) {
        Timber.d("onMailChanged . " + s);
        mail = s.toString();
    }

    public void onNameChanged(CharSequence s, int start, int before, int count) {
        Timber.d("onNameChanged . " + s);
        name = s.toString();
    }

    public void onPasswordChanged(CharSequence s, int start, int before, int count) {
        Timber.d("onPasswordChanged . " + s);
        pass = s.toString();
    }

    private boolean validateData() {
        Timber.d("validateData");
        if (!StringValidator.validateEmail(mail)) {
            toastMessage.setValue("Invalid Mail");
            return false;
        }
        if (!StringValidator.validatePass(pass)) {
            toastMessage.setValue("Invalid Password");
            return false;
        }
        return true;
    }

    private void tryToLogin() {
        Timber.d("tryToLogin.validateData()" + validateData());
        if (!validateData()) return;
        showProgress.setValue(true);
        Timber.d("tryToLogin. mail = " + mail + "\npass = " + pass);
        if (StringValidator.validateEmail(mail) && StringValidator.validatePass(pass)) {
            Disposable disposable = repository
                    .login(mail, pass)
                    .takeUntil(new Predicate<User>() {
                        @Override
                        public boolean test(User user) throws Exception {
                            return user != null;
                        }
                    })
                    .subscribe(user -> {
                        Timber.d("tryToLogin.onNext.user = " + user);
                        currentUser = user;
                        userCounter++;
                    }, throwable -> {
                        Timber.d(errorMessage = "tryToLogin.onError  = " + throwable.getMessage());
                        toastMessage.postValue("User not found. Register new one.");
                        enableRegisterMode.postValue(true);
                        showProgress.setValue(false);
                    }, () -> {
                        Timber.d("tryToLogin.onComplete");
                        if (userCounter < 1) {
                            toastMessage.postValue(errorMessage);
                            enableRegisterMode.postValue(true);
                        } else if (currentUser != null) {
                            goToMainActivity.setValue(true);
                        }

                    });
            disposables.add(disposable);
        } else
            toastMessage.setValue("Invalid data");

    }

    public void onLogInClick(View view) {
        Timber.d("onLogInClick");
        if (enableRegisterMode.getValue() != null && enableRegisterMode.getValue()) {
            tryToRegister();
        } else
            tryToLogin();
    }

    private void tryToRegister() {
        Timber.d("tryToRegister");
        if (!validateData()) return;
        if (!StringValidator.validateName(name)) return;
        showProgress.setValue(true);
        Timber.d("onRegisterClick.mail =" + mail + "\npass = " + pass + "\nname = " + name);
        if (StringValidator.validateEmail(mail) && StringValidator.validatePass(pass) && StringValidator.validateName(name)) {
            Disposable disposable = repository
                    .register(new User(System.currentTimeMillis(), mail, name, pass, Const.DEFAULT_USER_INFO))   // todo: server should set timestamp
                    .subscribe(loginResponse -> {
                        repository.saveUserInDb(loginResponse.getUser());
                        goToMainActivity.postValue(true);
                    }, throwable -> {
                        Timber.d(throwable.getMessage());
                        toastMessage.postValue(throwable.getMessage());
                        enableRegisterMode.postValue(true);
                    });
            disposables.add(disposable);
        } else
            toastMessage.setValue("Invalid data");
    }
}
