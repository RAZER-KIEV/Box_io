package software.doit.boxio.model.api.requests;

import software.doit.boxio.model.data.Box;
import software.doit.boxio.model.data.User;

public class OrderBoxRequest {
    private Box box;
    private User user;

    public OrderBoxRequest(Box box, User user) {
        this.box = box;
        this.user = user;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "OrderBoxRequest{" +
                "box=" + box +
                ", user=" + user +
                '}';
    }
}
