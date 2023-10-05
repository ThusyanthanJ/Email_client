import java.util.HashMap;

class Personal_recipient extends Recipient{
    private String nickName;
    private final String B_date;
    HashMap<String, String> BirthdayGuy = new HashMap<>();
    Personal_recipient(String name, String nickName, String email, String b_date) {
        super(name, email);
        this.nickName = nickName;
        B_date = b_date;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getB_date() {
        return B_date;
    }
    public void setB_date() {
        BirthdayGuy.put(this.B_date,super.getName());
    }
    public void GetBirthdayGuy(){
        System.out.println(BirthdayGuy.get(this.B_date));
    }
}