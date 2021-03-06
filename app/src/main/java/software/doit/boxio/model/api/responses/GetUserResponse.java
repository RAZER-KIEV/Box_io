package software.doit.boxio.model.api.responses;

import software.doit.boxio.model.data.User;

public class GetUserResponse {
    private User user;

    public GetUserResponse(User user) {
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
        return "GetUserResponse{" +
                "user=" + user +
                '}';
    }
}
