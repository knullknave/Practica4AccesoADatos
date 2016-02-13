package com.dam.practic2.Model.Objects;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "medic")
public class Medic
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="userName")
    private String userName;
    @Column(name="userPasword")
    private String userPasword;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Column(name="adress")
    private String address;
    @Column(name="medicalCentre")
    private String medicalCentre;
    @Column(name="email")
    private String email;
    @Column(name="medicalSpeciality")
    private String medicalSpeciality;
    @Column(name="telephone")
    private String telephone;
    @Column(name="birthDate")
    private Date birthDate;

    public Medic()
    {

    }

    public Medic(int id, String userName, String userPasword, String name, String surname, String adress, String medicalCentre, String email, String medicalSpeciality, String telephone, Date birthDate)
    {
        this.id = id;
        this.userName = userName;
        this.userPasword = userPasword;
        this.name = name;
        this.surname = surname;
        this.address = adress;
        this.medicalCentre = medicalCentre;
        this.email = email;
        this.medicalSpeciality = medicalSpeciality;
        this.telephone = telephone;
        this.birthDate = birthDate;
    }

    public Medic(int id, String name, String surname, String adress, String medicalCentre, String email, String medicalSpeciality, String telephone, Date birthDate)
    {
        this.id = id;
        this.userName = userName;
        this.userPasword = userPasword;
        this.name = name;
        this.surname = surname;
        this.address = adress;
        this.medicalCentre = medicalCentre;
        this.email = email;
        this.medicalSpeciality = medicalSpeciality;
        this.telephone = telephone;
        this.birthDate = birthDate;
    }

    public Medic(String userName, String userPasword, String name, String surname, String adress, String medicalCentre, String email, String medicalSpeciality, String telephone, Date birthDate)
    {
        this.userName = userName;
        this.userPasword = userPasword;
        this.name = name;
        this.surname = surname;
        this.address = adress;
        this.medicalCentre = medicalCentre;
        this.email = email;
        this.medicalSpeciality = medicalSpeciality;
        this.telephone = telephone;
        this.birthDate = birthDate;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int collegiateNumber)
    {
        this.id = collegiateNumber;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPasword()
    {
        return userPasword;
    }

    public void setUserPasword(String userPasword)
    {
        this.userPasword = userPasword;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getMedicalCentre()
    {
        return medicalCentre;
    }

    public void setMedicalCentre(String medicalCentre)
    {
        this.medicalCentre = medicalCentre;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getMedicalSpeciality()
    {
        return medicalSpeciality;
    }

    public void setMedicalSpeciality(String medicalSpeciality)
    {
        this.medicalSpeciality = medicalSpeciality;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public Date getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }
}
