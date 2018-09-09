package software.doit.testapp.model.api.retrofit;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import software.doit.testapp.model.api.requests.GetUserRequest;
import software.doit.testapp.model.api.requests.LoginRequest;
import software.doit.testapp.model.api.requests.OrderBoxRequest;
import software.doit.testapp.model.api.requests.RegisterRequest;
import software.doit.testapp.model.api.responses.GetUserResponse;
import software.doit.testapp.model.api.responses.LoginResponse;
import software.doit.testapp.model.api.responses.OrderBoxResponse;
import software.doit.testapp.model.api.responses.RegisterResponse;

public interface RetroService {

    String LOGIN_URL = "LOGIN_URL";
    String REGISTER_URL = "REGISTER_URL";
    String ORDER_BOX_URL = "ORDER_BOX_URL";
    String GET_USER_URL = "GET_USER_URL";

    @GET(LOGIN_URL)
    Single<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST(REGISTER_URL)
    Single<RegisterResponse> register(@Body RegisterRequest registerRequest);

    @POST(ORDER_BOX_URL)
    Single<OrderBoxResponse> orderBox(@Body OrderBoxRequest orderBoxRequest);

    @GET(GET_USER_URL)
    Single<GetUserResponse> getUser(@Body GetUserRequest getUserRequest);

}
