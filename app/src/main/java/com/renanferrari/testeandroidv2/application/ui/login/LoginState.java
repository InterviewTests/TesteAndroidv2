package com.renanferrari.testeandroidv2.application.ui.login;

import androidx.annotation.NonNull;
import java.util.Objects;

public class LoginState {
  private boolean isLoading;
  private boolean isLoggedIn;
  private String username;
  private String password;
  private String usernameError;
  private String passwordError;

  private LoginState(final boolean isLoading, final boolean isLoggedIn, final String username,
      final String password, final String usernameError, final String passwordError) {
    this.isLoading = isLoading;
    this.isLoggedIn = isLoggedIn;
    this.username = username;
    this.password = password;
    this.usernameError = usernameError;
    this.passwordError = passwordError;
  }

  public static LoginState createDefault() {
    return new LoginState(false, false, "", "", null, null);
  }

  public static LoginState copyOf(final LoginState loginState) {
    return new LoginState(loginState.isLoading, loginState.isLoggedIn, loginState.username,
        loginState.password, loginState.usernameError, loginState.passwordError);
  }

  public boolean isLoading() {
    return isLoading;
  }

  public void setLoading(final boolean loading) {
    isLoading = loading;
  }

  public boolean isLoggedIn() {
    return isLoggedIn;
  }

  public void setLoggedIn(final boolean loggedIn) {
    isLoggedIn = loggedIn;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public String getUsernameError() {
    return usernameError;
  }

  public void setUsernameError(final String usernameError) {
    this.usernameError = usernameError;
    this.passwordError = null;
  }

  public String getPasswordError() {
    return passwordError;
  }

  public void setPasswordError(final String passwordError) {
    this.usernameError = null;
    this.passwordError = passwordError;
  }

  public void clearErrors() {
    usernameError = null;
    passwordError = null;
  }

  @Override public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final LoginState that = (LoginState) o;
    return isLoading == that.isLoading &&
        isLoggedIn == that.isLoggedIn &&
        Objects.equals(username, that.username) &&
        Objects.equals(password, that.password) &&
        Objects.equals(usernameError, that.usernameError) &&
        Objects.equals(passwordError, that.passwordError);
  }

  @Override public int hashCode() {
    return Objects.hash(isLoading, isLoggedIn, username, password, usernameError, passwordError);
  }

  @Override @NonNull public String toString() {
    return "LoginState{" +
        "isLoading=" + isLoading +
        ", isLoggedIn=" + isLoggedIn +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", usernameError='" + usernameError + '\'' +
        ", passwordError='" + passwordError + '\'' +
        '}';
  }
}