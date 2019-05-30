package monteoliva.testbank.utils.http;

// imports JAVA API
import java.io.Serializable;

/**
 * Bean with return for Http Post
 *
 * @author Claudio Monteoliva
 * @version 1.0
 * @copyright 2019 Monteoliva Developer
 *
 */
public class HttpResultBean implements Serializable {
    private int code;
    private String message;

    /**
     * Methods Getter´s
     */
    public int getCode()       { return code;    }
    public String getMessage() { return message; }

    /**
     * Methods Setter´s
     */
    public void setCode(int code)          { this.code    = code;    }
    public void setMessage(String message) { this.message = message; }
}