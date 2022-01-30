package ru.stqa.pft.addressbook.model;
import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;
@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private  int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    private  String firstname;
    @Expose
    @Column(name = "lastname")
    private  String lastname;
    @Expose
    @Type(type="text")
    @Column(name = "home")
    private  String homephone;
    @Expose
    @Column(name = "mobile")
    @Type(type="text")
    private String mobile;
    @Column(name = "work")
    @Type(type="text")
    private String work;
    @Transient
    private String secondaryPhone;
    @Transient
    private String allPhones;
    @Column (name = "address")
    @Type(type="text")
    private String address;
    @Expose
    @Column(name = "email")
    @Type(type="text")
    private String email;
    @Column(name = "email2")
    @Type(type="text")
    private String email2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(homephone, that.homephone) && Objects.equals(mobile, that.mobile) && Objects.equals(work, that.work) && Objects.equals(address, that.address) && Objects.equals(email, that.email) && Objects.equals(email2, that.email2) && Objects.equals(email3, that.email3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, homephone, mobile, work, address, email, email2, email3);
    }

    @Column(name = "email3")
    @Type(type="text")
    private String email3;
    @Transient
    private String allEmails;
    @Column(name = "photo")
    @Type(type="text")
    private String photo;

    public File getPhoto() {
        return new File (photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public String getEmail2() {
        return email2;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public String getEmail3() {
        return email3;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
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

    public ContactData withSecondaryPhone(String secondaryPhone) {
        this.secondaryPhone = secondaryPhone;
        return this;
    }




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

    public String getSecondaryPhone() {
        return secondaryPhone;
    }


    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }
}
