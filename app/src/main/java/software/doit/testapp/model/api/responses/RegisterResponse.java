package software.doit.testapp.model.api.responses;

import software.doit.testapp.model.data.User;

public class RegisterResponse {
    private User user;

    public RegisterResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "RegisterResponse{" +
                "user=" + user +
                '}';
    }
}
