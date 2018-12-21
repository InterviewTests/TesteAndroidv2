package teste.claudio.com.testsantander;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Statement {

        @SerializedName("statementList")
        @Expose
        private List<statementList> statementList = null;
        @SerializedName("error")
        @Expose
        private Error error;

        public List<statementList> getStatementList() {
            return statementList;
        }

        public void setStatementList(List<statementList> statementList) {
            this.statementList = statementList;
        }

        public Error getError() {
            return error;
        }

        public void setError(Error error) {
            this.error = error;
        }
}
