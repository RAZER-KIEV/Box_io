package software.doit.boxio.model.api.requests;

public class GetUserRequest {
    private String email;

    public GetUserRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "GetUserRequest{" +
                "email='" + email + '\'' +
                '}';
    }
}
