package resource.estagio.testesantander;

public interface IRepository {
    void login(String user, String password, BaseCallback<Login> onResult);
}
