package resource.estagio.testesantander.domain.contract;

import resource.estagio.testesantander.infra.BaseCallback;
import resource.estagio.testesantander.login.LoginResponse;

public class UserContract {
    public interface IUserRepository{
        void login(String username, String password, BaseCallback<LoginResponse> onResult);
    }
}
