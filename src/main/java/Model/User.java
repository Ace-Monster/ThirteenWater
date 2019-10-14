package Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("user_id")
    private String UID;
    private String token;
    private String username;
    private String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public String getUID(){
        return UID;
    }
}
