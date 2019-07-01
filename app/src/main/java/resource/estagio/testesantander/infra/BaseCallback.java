package resource.estagio.testesantander.infra;

public interface BaseCallback <T>{
    void onSucessful(T value);
    void onUnsucessful (String error);
}
