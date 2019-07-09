package ssilvalucas.testeandroidv2;

import org.junit.Assert;
import org.junit.Test;

import ssilvalucas.testeandroidv2.screen.login.LoginInteractor;

import static org.hamcrest.core.Is.is;

public class ScreenLoginTest {

    @Test
    public void testInputUserEmail() {
        /* Testa entrada de email válido */

        /**GIVEN**/
        LoginInteractor interactor = new LoginInteractor();
        String username = "lucass.silva@me.com";

        /**WHEN**/
        boolean isValid = interactor.isValidUsername(username);

        /**THEN**/
        Assert.assertThat(isValid, is(true));
    }

    @Test
    public void testInputUserCPFComplete() {
        /* Testa entrada de CPF com pontos e traço */

        /**GIVEN**/
        LoginInteractor interactor = new LoginInteractor();
        String username = "407.341.578-60";

        /**WHEN**/
        boolean isValid = interactor.isValidUsername(username);

        /**THEN**/
        Assert.assertThat(isValid, is(true));
    }

    @Test
    public void testInputUserCPF() {
        /* Testa entrada de CPF somente com números */

        /**GIVEN**/
        LoginInteractor interactor = new LoginInteractor();
        String username = "40734157860";

        /**WHEN**/
        boolean isValid = interactor.isValidUsername(username);

        /**THEN**/
        Assert.assertThat(isValid, is(true));
    }

    @Test
    public void testInputUserEmpty() {
        /* Testa entrada de usuário vazia */

        /**GIVEN**/
        LoginInteractor interactor = new LoginInteractor();
        String username = "";

        /**WHEN**/
        boolean isValid = interactor.isValidUsername(username);

        /**THEN**/
        Assert.assertThat(isValid, is(false));
    }

    @Test
    public void testInputUserInvalid() {
        /* Testa entrada de usuário inválido */

        /**GIVEN**/
        LoginInteractor interactor = new LoginInteractor();
        String username = "lucass";

        /**WHEN**/
        boolean isValid = interactor.isValidUsername(username);

        /**THEN**/
        Assert.assertThat(isValid, is(false));
    }

    @Test
    public void testInputUserInvalidWithNumbers() {
        /* Testa entrada de usuário inválido com números*/

        /**GIVEN**/
        LoginInteractor interactor = new LoginInteractor();
        String username = "12345678";

        /**WHEN**/
        boolean isValid = interactor.isValidUsername(username);

        /**THEN**/
        Assert.assertThat(isValid, is(false));
    }

    @Test
    public void testInputPasswordInvaid() {
        /* Testa entrada de senha inválida com números*/

        /**GIVEN**/
        LoginInteractor interactor = new LoginInteractor();
        String password = "12345678";

        /**WHEN**/
        boolean isValid = interactor.isValidPassword(password);

        /**THEN**/
        Assert.assertThat(isValid, is(false));
    }

    @Test
    public void testInputPasswordInvaidWithoutUppercase() {
        /* Testa entrada de senha sem letra maiúscula*/

        /**GIVEN**/
        LoginInteractor interactor = new LoginInteractor();
        String password = "l1!s@";

        /**WHEN**/
        boolean isValid = interactor.isValidPassword(password);

        /**THEN**/
        Assert.assertThat(isValid, is(false));
    }

    @Test
    public void testInputPasswordInvaidWithoutNumber() {
        /* Testa entrada de senha sem letra minúscula*/

        /**GIVEN**/
        LoginInteractor interactor = new LoginInteractor();
        String password = "Test@#";

        /**WHEN**/
        boolean isValid = interactor.isValidPassword(password);

        /**THEN**/
        Assert.assertThat(isValid, is(false));
    }

    @Test
    public void testInputPasswordInvaidWithoutSpecialChar() {
        /* Testa entrada de senha sem caracter especial*/

        /**GIVEN**/
        LoginInteractor interactor = new LoginInteractor();
        String password = "Test123";

        /**WHEN**/
        boolean isValid = interactor.isValidPassword(password);

        /**THEN**/
        Assert.assertThat(isValid, is(false));
    }

    @Test
    public void testInputPasswordEmpty() {
        /* Testa entrada de senha vazia*/

        /**GIVEN**/
        LoginInteractor interactor = new LoginInteractor();
        String password = "";

        /**WHEN**/
        boolean isValid = interactor.isValidPassword(password);

        /**THEN**/
        Assert.assertThat(isValid, is(false));
    }
}
