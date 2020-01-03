package resource.estagio.testesantander.login;

import com.google.gson.annotations.SerializedName;

import resource.estagio.testesantander.model.Error;
import resource.estagio.testesantander.domain.User;

public class LoginResponse {
    private Error error;

    @SerializedName("userAccount")
    private User user;

    public LoginResponse(Error error, User user) {
        this.error = error;
        this.user = user;
    }

    public Error getError() {
        return error;
    }

    public User getUser() {
        return user;
    }
}
