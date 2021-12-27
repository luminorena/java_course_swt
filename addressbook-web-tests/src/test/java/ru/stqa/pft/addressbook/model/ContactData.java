package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    public void setId(int id) {
        this.id = id;
    }

    private  int id;
    private final String firstname;
    private final String lastname;
    private final String homephone;
    private final String email;


    public int getId() {
        return id;
    }


    public ContactData(int id, String firstname, String lastname, String homephone, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.homephone = homephone;
        this.email = email;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstname, that.firstname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname);
    }
    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                '}';
    }

}
