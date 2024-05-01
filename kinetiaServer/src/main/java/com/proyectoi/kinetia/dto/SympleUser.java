package com.proyectoi.kinetia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyectoi.kinetia.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class SympleUser {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("rol")
    private RolModel.RolType rol;
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("secondSurname")
    private String secondSurname;
    @JsonProperty("birthDate")
    private LocalDate birthDate;
    @JsonProperty("profilePicture")
    private String profilePicture;
    @JsonProperty("company")
    private String company;
    @JsonProperty("cif")
    private String cif;
    @JsonProperty("adress")
    private String adress;
    @JsonProperty("advertisements")
    private int advertisements;
    @JsonProperty("activitiesOfered")
    private int activitiesOffered;
    @JsonProperty("activitiesFav")
    private int activitiesFav;
    @JsonProperty("activitiesReserved")
    private int activitiesReserved;
    @JsonProperty("chats")
    private int chats;

    public SympleUser(UserModel user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.rol = user.getRol().getRolType();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.secondSurname = user.getSecondSurname();
        this.birthDate = user.getBirthDate();
        this.profilePicture = user.getProfilePicture();
        this.company = user.getCompany();
        this.cif = user.getCif();
        this.adress = user.getAdress();
        this.advertisements = user.getAdvertisements().size();
        this.activitiesOffered = user.getActivitiesOffered().size();
        this.activitiesFav = user.getActivitiesFav().size();
        this.activitiesReserved = user.getActivitiesReserved().size();

        Set<UserModel> contacts = new HashSet<>();

        for (MessageModel message : user.getSentMessages()) {
            if (!message.getSenderHasDeleted())
                contacts.add(message.getReceiver());
        }
        for (MessageModel message : user.getReceivedMessages()) {
            if (!message.getReceiverHasDeleted())
                contacts.add(message.getSender());
        }

        this.chats = contacts.size();


    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", rol=" + rol +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", birthDate=" + birthDate +
                ", profilePicture='" + profilePicture + '\'' +
                ", company='" + company + '\'' +
                ", cif='" + cif + '\'' +
                ", adress='" + adress + '\'' +
                ", advertisements=" + advertisements +
                ", activitiesOffered=" + activitiesOffered +
                ", activitiesFav=" + activitiesFav +
                ", activitiesReserved=" + activitiesReserved +
                ", contacts=" + chats +
                '}';
    }
}
