package software.doit.boxio.model.api.responses;

import software.doit.boxio.model.data.User;

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
