package resource.estagio.testesantander;

public interface BaseCallback <T>{
    void onSucessful(T value);
    void onUnsucessful (String error);
}
