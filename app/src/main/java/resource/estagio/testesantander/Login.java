package resource.estagio.testesantander;

public class Login {

    public IRepository repository;

    private String user;
    private String password;


    private long userId;
    private String name;
    private String bankAccount;
    private String agency;
    private float balance;

    public Login(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public Login(long userId, String name, String bankAccount, String agency, float balance) {
        this.userId = userId;
        this.name = name;
        this.bankAccount = bankAccount;
        this.agency = agency;
        this.balance = balance;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void login(final BaseCallback<Login> onResult){
        repository.login(user, password, new BaseCallback<Login>() {
            @Override
            public void onSucessful(Login value) {
                onResult.onSucessful(value);
            }

            @Override
            public void onUnsucessful(String error) {
                onResult.onUnsucessful(error);
            }

        });
    }





}
