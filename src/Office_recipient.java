class Official_recipient extends Recipient{
    private String designation;
    Official_recipient(String name, String email, String designation) {
        super(name, email);
        this.designation = designation;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
