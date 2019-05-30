package monteoliva.testbank.utils.http;

// imports JAVA API
import java.io.Serializable;

/**
 * Classe Bean de parametros HTTP
 *
 * @author Claudio Monteoliva
 * @version 1.0
 * @copyright 2019 Monteoliva Developer
 *
 */
public class HttpParams implements Serializable {
    private String nome;
    private String valor;

    /**
     * Metodos Getter's
     */
    public String getNome()  { return nome;  }
    public String getValor() { return valor; }

    /**
     * Metodos Setter's
     */
    public void setNome(String nome)   { this.nome  = nome;  }
    public void setValor(String valor) { this.valor = valor; }
}