package software.doit.boxio.model.api.responses;

public class OrderBoxResponse {
    private Boolean isSuccessful;

    public OrderBoxResponse(Boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(Boolean successful) {
        isSuccessful = successful;
    }

    @Override
    public String toString() {
        return "OrderBoxResponse{" +
                "isSuccessful=" + isSuccessful +
                '}';
    }
}
