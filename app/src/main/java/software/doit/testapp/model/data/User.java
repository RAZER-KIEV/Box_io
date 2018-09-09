package software.doit.testapp.model.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class User {

    @NonNull
    @PrimaryKey
    private String email;

    private long timeStamp;

    private String name, password, userInfo;

    public User(long timestamp, @NonNull String email, String name, String password, String userInfo) {
        this.timeStamp = timestamp;
        this.email = email;
        this.name = name;
        this.password = password;
        this.userInfo = userInfo;
    }

    public User() {
    }

    public void setTimestamp(long timestamp) {
        this.timeStamp = timestamp;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "User{" +
                "timestamp=" + timeStamp +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", userInfo='" + userInfo + '\'' +
                '}';
    }
}
