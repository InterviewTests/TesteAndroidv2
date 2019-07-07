package ssilvalucas.testeandroidv2.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UserAccount implements Parcelable {

    @SerializedName("userId")
    private long userId;
    @SerializedName("name")
    private String name;
    @SerializedName("bankAccount")
    private String bankAccount;
    @SerializedName("agency")
    private String agency;
    @SerializedName("balance")
    private String balance;

    protected UserAccount(Parcel parcel) {
        this.userId      = parcel.readLong();
        this.name        = parcel.readString();
        this.bankAccount = parcel.readString();
        this.agency      = parcel.readString();
        this.balance     = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(userId);
        dest.writeString(name);
        dest.writeString(bankAccount);
        dest.writeString(agency);
        dest.writeString(balance);
    }

    public static final Creator<UserAccount> CREATOR = new Creator<UserAccount>() {
        @Override
        public UserAccount createFromParcel(Parcel in) {
            return new UserAccount(in);
        }

        @Override
        public UserAccount[] newArray(int size) {
            return new UserAccount[size];
        }
    };

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getAgency() {
        return agency;
    }

    public String getBalance() {
        return balance;
    }
}
