package dat.backend.model.entities;

public class User
{
    private int userid;
    private String email;
    private String name;
    private String password;
    private String address;
    private int postalcode;
    private String cityName;
    private boolean isAdmin;

    public User(int userid, String email, String name, String password, String address, int postalcode,String cityName, boolean isAdmin) {
        this.userid = userid;
        this.email = email;
        this.name = name;
        this.password = password;
        this.address = address;
        this.postalcode = postalcode;
        this.cityName = cityName;
        this.isAdmin = isAdmin;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(int postalcode) {
        this.postalcode = postalcode;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "brugerNavn='" + email + '\'' +
                ", kodeord='" + password + '\'' +
                ", rolle='" + postalcode +
                '}';
    }
}
