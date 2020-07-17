/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.securefactorbod.fud.models;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.Table;

/**
 *
 * @author lmanrique
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @Column(name = "ID_CUSCUN_USER")
    private String id_Cuscun_User;
    
    
    @Column(name = "ID_CUSCUN")
    private Long id_Cuscun;

    @Column(name = "CUSCUN_NAME")
    private String cuscun_Name;

    @Column(name = "CUSCUN_DATE_ADD")
    private Date cuscun_Date_Add;

    @Column(name = "CUSCUN_STATUS")
    private String cuscun_Status;

    @Column(name = "CUSCUN_STATUS_OTP_SERVICE")
    private String status_Otp_Service;

    @Column(name = "CUSCUN_LAST_DATE_CHANGE")
    private Date cuscun_Last_Date_Change;

    @Column(name = "CUSCUN_CONF_CHANNEL")
    private String cuscun_Conf_Channel;
    
    
    
    

    public Customer() {

    }

    public Customer(String id_Cuscun_User, Long id_Cuscun, String cuscun_Name, Date cuscun_Date_Add, String cuscun_Status, String status_Otp_Service, Date cuscun_Last_Date_Change, String cuscun_Conf_Channel) {
        this.id_Cuscun_User = id_Cuscun_User;
        this.id_Cuscun = id_Cuscun;
        this.cuscun_Name = cuscun_Name;
        this.cuscun_Date_Add = cuscun_Date_Add;
        this.cuscun_Status = cuscun_Status;
        this.status_Otp_Service = status_Otp_Service;
        this.cuscun_Last_Date_Change = cuscun_Last_Date_Change;
        this.cuscun_Conf_Channel = cuscun_Conf_Channel;
    }




   

    public String getId_Cuscun_User() {
        return id_Cuscun_User;
    }

    public void setId_Cuscun_User(String id_Cuscun_User) {
        this.id_Cuscun_User = id_Cuscun_User;
    }

    public Long getId_Cuscun() {
        return id_Cuscun;
    }

    public void setId_Cuscun(Long id_Cuscun) {
        this.id_Cuscun = id_Cuscun;
    }

    public String getCuscun_Name() {
        return cuscun_Name;
    }

    public void setCuscun_Name(String cuscun_Name) {
        this.cuscun_Name = cuscun_Name;
    }

    public Date getCuscun_Date_Add() {
        return cuscun_Date_Add;
    }

    public void setCuscun_Date_Add(Date cuscun_Date_Add) {
        this.cuscun_Date_Add = cuscun_Date_Add;
    }

    public String getCuscun_Status() {
        return cuscun_Status;
    }

    public void setStatus(String cuscun_Status) {
        this.cuscun_Status = cuscun_Status;
    }

    public String getStatus_Otp_Service() {
        return status_Otp_Service;
    }

    public void setStatus_Otp_Service(String status_Otp_Service) {
        this.status_Otp_Service = status_Otp_Service;
    }

    public Date getCuscun_Last_Date_Change() {
        return cuscun_Last_Date_Change;
    }

    public void setCuscun_Last_Date_Change(Date cuscun_Last_Date_Change) {
        this.cuscun_Last_Date_Change = cuscun_Last_Date_Change;
    }

    public String getCuscun_Conf_Channel() {
        return cuscun_Conf_Channel;
    }

    public void setCuscun_Conf_Channel(String cuscun_Conf_Channel) {
        this.cuscun_Conf_Channel = cuscun_Conf_Channel;
    }

   

   

}
