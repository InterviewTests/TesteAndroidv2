package ssilvalucas.testeandroidv2.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StatementsResponse implements Parcelable {

    @SerializedName("statementList")
    private ArrayList<StatementHome> statementArrayList;

    protected StatementsResponse(Parcel parcel){
        statementArrayList = parcel.createTypedArrayList(StatementHome.CREATOR);
    }

    public static final Creator<StatementsResponse> CREATOR = new Creator<StatementsResponse>() {
        @Override
        public StatementsResponse createFromParcel(Parcel in) {
            return new StatementsResponse(in);
        }

        @Override
        public StatementsResponse[] newArray(int size) {
            return new StatementsResponse[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(statementArrayList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public ArrayList<StatementHome> getStatementArrayList(){
        return statementArrayList;
    }
}
