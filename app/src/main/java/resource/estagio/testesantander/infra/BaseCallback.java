package resource.estagio.testesantander.infra;

public interface BaseCallback <T>{
    void onSuccessful(T value);
    void onUnsucessful (String error);
}
