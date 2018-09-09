package software.doit.testapp.model.api.retrofit;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import software.doit.testapp.model.api.requests.GetUserRequest;
import software.doit.testapp.model.api.requests.LoginRequest;
import software.doit.testapp.model.api.requests.OrderBoxRequest;
import software.doit.testapp.model.api.requests.RegisterRequest;
import software.doit.testapp.model.api.responses.GetUserResponse;
import software.doit.testapp.model.api.responses.LoginResponse;
import software.doit.testapp.model.api.responses.OrderBoxResponse;
import software.doit.testapp.model.api.responses.RegisterResponse;
import software.doit.testapp.model.data.User;
import software.doit.testapp.other.utils.Const;
import software.doit.testapp.other.utils.StringValidator;
import timber.log.Timber;

public class RetroServiceStub implements RetroService {

    // mock server
    private List<User> userList = new ArrayList<>();


    @Override
    public Single<LoginResponse> login(LoginRequest loginRequest) {
        sleep();
        for (User user : userList) {
            if (user.getEmail().equals(loginRequest.getEmail()) && user.getPassword().equals(loginRequest.getPassword()))
                return Single.just(new LoginResponse(Const.RANDOM_TOKEN, user));
        }
        return Single.error(new Exception("User not exists or password wrong"));
    }

    @Override
    public Single<RegisterResponse> register(RegisterRequest registerRequest) {
        sleep();
        Timber.d("register.registerRequest = 0" + registerRequest);
        StringBuilder errorMessage = new StringBuilder("User not registered. ");
        int startSize = errorMessage.length();
        if (!StringValidator.validateName(registerRequest.getUser().getName()))
            errorMessage.append("Invalid Name.");
        if (!StringValidator.validateEmail(registerRequest.getUser().getEmail()))
            errorMessage.append("Invalid Email.");
        if (!StringValidator.validatePass(registerRequest.getUser().getPassword()))
            errorMessage.append("Invalid Password.");
        if (startSize != errorMessage.length()) {
            return Single.error(new Exception(errorMessage.toString()));
        } else
            userList.add(registerRequest.getUser());
        return Single.just(new RegisterResponse(registerRequest.getUser()));
    }

    @Override
    public Single<OrderBoxResponse> orderBox(OrderBoxRequest orderBoxRequest) {
        Timber.d("orderBox. box =" + orderBoxRequest);
        return Single.just(new OrderBoxResponse(true));
    }

    @Override
    public Single<GetUserResponse> getUser(GetUserRequest getUserRequest) {
        sleep();
        for (User user : userList) {
            if (user.getEmail().equals(getUserRequest.getEmail()))
                return Single.just(new GetUserResponse(user));
        }
        return Single.error(new Throwable("No user found"));
    }

    private void sleep() {
        // emulating server work.
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
