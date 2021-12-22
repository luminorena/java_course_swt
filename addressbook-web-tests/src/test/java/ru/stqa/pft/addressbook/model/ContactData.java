package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String homephone;
    private final String email;
    private String group;

    public ContactData(String firstname, String lastname, String homephone, String email, String group) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.homephone = homephone;
        this.email = email;
        this.group = group;

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

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", homephone='" + homephone + '\'' +
                ", email='" + email + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(homephone, that.homephone) && Objects.equals(email, that.email) && Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, homephone, email, group);
    }
}
