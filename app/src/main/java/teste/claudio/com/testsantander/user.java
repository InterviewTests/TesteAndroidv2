package teste.claudio.com.testsantander;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class user {
    @SerializedName("userAccount")
    @Expose
    private userId userAccount;

    @SerializedName("error")
    @Expose
    private Error error;


    public userId getUserAccount() {
        return  userAccount;
    }

    public void setUserAccount(userId userAccount) {
        this.userAccount = userAccount;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

}