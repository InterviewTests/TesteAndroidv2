package com.renanferrari.testeandroidv2.application.ui.login;

import androidx.annotation.NonNull;
import java.util.Objects;

public class LoginState {

  private final boolean isLoading;
  private final boolean isLoggedIn;
  private final String username;
  private final String password;
  private final String usernameError;
  private final String passwordError;

  private LoginState(final Builder builder) {
    isLoading = builder.isLoading;
    isLoggedIn = builder.isLoggedIn;
    username = builder.username;
    password = builder.password;
    usernameError = builder.usernameError;
    passwordError = builder.passwordError;
  }

  public static LoginState createDefault() {
    return LoginState.builder()
        .isLoading(false)
        .isLoggedIn(false)
        .username("")
        .password("")
        .usernameError(null)
        .passwordError(null)
        .build();
  }

  public boolean isLoading() {
    return isLoading;
  }

  public LoginState withLoading(final boolean loading) {
    return toBuilder().isLoading(loading).build();
  }

  public boolean isLoggedIn() {
    return isLoggedIn;
  }

  public LoginState withLoggedIn(final boolean loggedIn) {
    return toBuilder().isLoggedIn(loggedIn).build();
  }

  public String getUsername() {
    return username;
  }

  public LoginState withUsername(final String username) {
    return toBuilder().username(username).build();
  }

  public String getPassword() {
    return password;
  }

  public LoginState withPassword(final String password) {
    return toBuilder().password(password).build();
  }

  public String getUsernameError() {
    return usernameError;
  }

  public LoginState withUsernameError(final String usernameError) {
    return toBuilder().usernameError(usernameError).passwordError(null).build();
  }

  public String getPasswordError() {
    return passwordError;
  }

  public LoginState withPasswordError(final String passwordError) {
    return toBuilder().usernameError(null).passwordError(passwordError).build();
  }

  public LoginState withoutErrors() {
    return toBuilder().usernameError(null).passwordError(null).build();
  }

  public Builder toBuilder() {
    return new Builder(this);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private boolean isLoading;
    private boolean isLoggedIn;
    private String username;
    private String password;
    private String usernameError;
    private String passwordError;

    private Builder() {}

    private Builder(final LoginState loginState) {
      this.isLoading = loginState.isLoading;
      this.isLoggedIn = loginState.isLoggedIn;
      this.username = loginState.username;
      this.password = loginState.password;
      this.usernameError = loginState.usernameError;
      this.passwordError = loginState.passwordError;
    }

    public Builder isLoading(final boolean isLoading) {
      this.isLoading = isLoading;
      return this;
    }

    public Builder isLoggedIn(final boolean isLoggedIn) {
      this.isLoggedIn = isLoggedIn;
      return this;
    }

    public Builder username(final String username) {
      this.username = username;
      return this;
    }

    public Builder password(final String password) {
      this.password = password;
      return this;
    }

    public Builder usernameError(final String usernameError) {
      this.usernameError = usernameError;
      return this;
    }

    public Builder passwordError(final String passwordError) {
      this.passwordError = passwordError;
      return this;
    }

    public LoginState build() {
      return new LoginState(this);
    }
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