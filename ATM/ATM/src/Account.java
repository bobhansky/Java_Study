public class Account {
    private String CardId;    //card id
    private String UserName;
    private String Password;
    private double money;
    private double quota;  // the amount of money that the user manage at one time

    public Account(){

    }

    public Account(String cardId, String userName, String password, double quota) {
        CardId = cardId;
        UserName = userName;
        Password = password;
        this.money = money;
        this.quota = quota;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getQuota() {
        return quota;
    }

    public void setQuota(double quota) {
        this.quota = quota;
    }

    public String getCardId() {
        return CardId;
    }

    public void setCardId(String cardId) {
        CardId = cardId;
    }
}
