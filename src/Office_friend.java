import java.util.HashMap;

class Office_friend extends Recipient{
    private final String B_date;
    private String designation;
    HashMap<String, String> BirthdayGuy = new HashMap<>();
    Office_friend(String name, String email, String designation, String b_date) {
        super(name, email);
        B_date = b_date;
        this.designation = designation;
    }
    public String getB_date() {
        return B_date;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public void setB_date() {
        BirthdayGuy.put(this.B_date,super.getName());
    }
    public void GetBirthdayGuy(){
        System.out.println(BirthdayGuy.get(this.B_date));
    }
}
