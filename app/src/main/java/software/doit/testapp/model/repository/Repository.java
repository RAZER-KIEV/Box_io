package software.doit.testapp.model.repository;

import android.annotation.SuppressLint;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import software.doit.testapp.R;
import software.doit.testapp.model.api.requests.LoginRequest;
import software.doit.testapp.model.api.requests.OrderBoxRequest;
import software.doit.testapp.model.api.requests.RegisterRequest;
import software.doit.testapp.model.api.responses.LoginResponse;
import software.doit.testapp.model.api.responses.OrderBoxResponse;
import software.doit.testapp.model.api.responses.RegisterResponse;
import software.doit.testapp.model.api.retrofit.RetroService;
import software.doit.testapp.model.data.Box;
import software.doit.testapp.model.data.Color;
import software.doit.testapp.model.data.User;
import software.doit.testapp.model.db.AppDatabase;
import timber.log.Timber;

@Singleton
public class Repository {

    @Inject
    public Repository(RetroService retroService, AppDatabase appDatabase, Resources resources, Executor executor) {
        this.retroService = retroService;
        this.appDatabase = appDatabase;
        this.resources = resources;
        this.executor = executor;
    }

    private RetroService retroService;
    private AppDatabase appDatabase;
    private Resources resources;
    private Executor executor;

    //cache
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    @SuppressLint("CheckResult")
    public void saveUserInDb(User user) {
        Timber.d("saveUserInDb.user = " + user);
        executor.execute(() ->
                Timber.d("Usersave. id" + appDatabase.userDao()
                        .insert(user)));
    }

    public Observable<User> login(String userMail, String userPassword) {
        Observable<User> databaseUser =
                appDatabase.userDao()
                        .findByEmail(userMail)
                        .toObservable();

        Observable<User> networkUser =
                retroService.login(new LoginRequest(userMail, userPassword))
                        .map(LoginResponse::getUser)
                        .toObservable();

        return Observable.concat(databaseUser, networkUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), true)
                .doOnNext(
                        user -> {
                            if (user != null) Repository.this.currentUser = user;
                            Timber.d("login.accept. user" + user);
                        }
                );
    }

    public Single<RegisterResponse> register(User user) {
        return retroService
                .register(new RegisterRequest(user))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(registerResponse -> Repository.this.currentUser = registerResponse.getUser());
    }

    public Single<OrderBoxResponse> orderTheBox(Box box) {
        Timber.d("orderTheBox.box = " + box);
        executor.execute(() -> {
            appDatabase.boxDao().insert(box);
        });
        return retroService
                .orderBox(new OrderBoxRequest(box, currentUser))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public List<Integer> getSmallBoxSizes() {
        return getSizeList(R.array.small_box_size);
    }

    public List<Integer> getMediumBoxSizes() {
        return getSizeList(R.array.medium_box_size);
    }

    public List<Integer> getLargeBoxSizes() {
        return getSizeList(R.array.large_box_size);
    }

    public List<Color> getSmallBoxColors() {
        return getColors(R.array.small_box_colors);
    }

    public List<Color> getMediumBoxColors() {
        return getColors(R.array.medium_box_colors);
    }

    public List<Color> getLargeBoxColors() {
        return getColors(R.array.large_box_colors);
    }

    private List<Color> getColors(int id) {
        String[] size = resources.getStringArray(id);
        List<Color> list = new ArrayList<>();
        for (String color : size) {
            list.add(new Color(color));
        }
        Timber.d("getColors." + list.toString());
        return list;
    }

    private List<Integer> getSizeList(int id) {
        int[] size = resources.getIntArray(id);
        List<Integer> list = new ArrayList<>();
        for (int val : size) {
            list.add(val);
        }
        Timber.d("getSizeList." + list.toString());
        return list;
    }
}
