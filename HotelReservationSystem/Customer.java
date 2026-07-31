/**
 * Abstract base class for a person staying at the hotel.
 * Demonstrates inheritance: Customer extends Person.
 */
abstract class Person {

    protected String name;
    protected int age;
    protected String gender;
    protected String phone;
    protected String email;
    protected String address;

    public Person(String name, int age, String gender, String phone, String email, String address) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public abstract String getContactInfo();

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

/**
 * Customer class holding the personal details of a guest.
 * Uses encapsulation: all fields are private and accessed via getters/setters.
 */
public class Customer extends Person {

    public Customer(String name, int age, String gender, String phone, String email, String address) {
        super(name, age, gender, phone, email, address);
    }

    @Override
    public String getContactInfo() {
        return getName() + " | " + getPhone() + " | " + getEmail();
    }

    @Override
    public String toString() {
        return "Name    : " + getName() + "\n"
                + "Age     : " + getAge() + "\n"
                + "Gender  : " + getGender() + "\n"
                + "Phone   : " + getPhone() + "\n"
                + "Email   : " + getEmail() + "\n"
                + "Address : " + getAddress();
    }
}
