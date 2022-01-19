package ru.stqa.pft.addressbook.model;
import java.util.Objects;
public class ContactData {
    private  int id = Integer.MAX_VALUE;
    private  String firstname;
    private  String lastname;
    private  String homephone;
    private String mobile;
    private String work;
    private String allPhones;

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }
    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }



    public ContactData withHomephone(String homephone) {
        this.homephone = homephone;
        return this;
    }
    public ContactData withMobilephone(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withWorkphone(String work) {
        this.work = work;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    private  String email;

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", homephone='" + homephone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getHomephone() {
        return homephone;
    }

    public String getEmail() {
        return email;
    }

    public String getMobilephone() {
        return mobile;
    }

    public String getWorkphone() {
        return work;
    }


}
