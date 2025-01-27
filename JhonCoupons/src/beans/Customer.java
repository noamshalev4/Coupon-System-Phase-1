package beans;

import java.util.ArrayList;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ArrayList<Coupon> coupons;

    //Use for add customer
    public Customer(String firstName, String lastName, String email, String password, ArrayList<Coupon> coupons) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.coupons = coupons;
    }

    public Customer(int id, String firstName, String lastName, String email, String password, ArrayList<Coupon> coupons) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.coupons = coupons;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return  "customer id: " + id +
                ", firstName: " + firstName +
                ", lastName: " + lastName +
                ", email: " + email +
                ", password: " + password +
                ", coupons: " + coupons;
    }
}
