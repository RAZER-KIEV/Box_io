package software.doit.boxio.model.api.requests;

import software.doit.boxio.model.data.User;

public class RegisterRequest {
     private User user;

    public RegisterRequest(User user) {
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
        return "RegisterRequest{" +
                "user=" + user +
                '}';
    }
}
