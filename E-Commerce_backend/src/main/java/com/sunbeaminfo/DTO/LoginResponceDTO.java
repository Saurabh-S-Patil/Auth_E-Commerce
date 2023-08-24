package com.sunbeaminfo.DTO;

import com.sunbeaminfo.entities.User;

public class LoginResponceDTO {
    private long id;
    private String email;
    private String first_name;
    private String last_name;
    private String gender;
    private long mob_no; 
    private String jwt;
    private String status;

    public LoginResponceDTO(){
        super();
    }

    public LoginResponceDTO(User user, String jwt,String status){
        this.id = user.getId();
        this.email = user.getEmail();
        this.first_name=user.getFirst_name();
        this.last_name = user.getLast_name();
        this.gender = user.getGender();
        this.mob_no = user.getMob_no();
        this.jwt = jwt;
        this.status=status;
    }

   

    public LoginResponceDTO(String email, String first_name, String last_name, String gender, long mob_no, String jwt,
            String status) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.mob_no = mob_no;
        this.jwt = jwt;
        this.status = status;
    }

    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getMob_no() {
        return mob_no;
    }

    public void setMob_no(long mob_no) {
        this.mob_no = mob_no;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJwt(){
        return this.jwt;
    }

    public void setJwt(String jwt){
        this.jwt = jwt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
