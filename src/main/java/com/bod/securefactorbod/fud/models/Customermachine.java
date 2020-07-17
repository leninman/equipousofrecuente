/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.securefactorbod.fud.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author lmanrique
 */


@NamedNativeQueries({

    @NamedNativeQuery(name="Customermachine.findOldestUseMachine",
            
            query="SELECT * from CUSTOMER_MACHINE u WHERE u.MACH_LAST_CONNECT_DATE "
                    + "in (SELECT min(v.MACH_LAST_CONNECT_DATE) from CUSTOMER_MACHINE v WHERE "
                    + "v.MACH_ID_CUSCUN_USER=:cuscunuser)",
            resultClass = Customermachine.class
    ),
//    @NamedNativeQuery(name="Customermachine.delOldestUseMachine",
//            
//            query="DELETE FROM CUSTOMER_MACHINE u WHERE u.MACH_LAST_CONNECT_DATE=:lastconnectdate")
})
@Entity
@Table(name="CUSTOMER_MACHINE")
public class Customermachine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CUS_MACHINE")
    private Long idcusmachine;
   
    @Column(name = "MACH_FINGERPRINT",length=4096)
    private String fingerprint;

    @Column(name = "MACH_START_DATE")
    private Date mach_Start_Date;

    @Column(name = "MACH_STATUS")
    private String mach_Status;

    @Column(name = "MACH_LAST_CONNECT_DATE")
    private Date mach_Last_Connect_Date;

    @Column(name = "MACH_TYPE")
    private String mach_Type;

    @Column(name = "MACH_ID_CUSCUN_USER")
    private String cuscunuser;
    
   

    public Customermachine() {

    }

    public Customermachine(Long idcusmachine, String fingerprint, String id_Hash_User, Date mach_Start_Date, String mach_Status, Date mach_Last_Connect_Date, String mach_Type, String cuscunuser) {
        this.idcusmachine = idcusmachine;
        this.fingerprint = fingerprint;
        this.mach_Start_Date = mach_Start_Date;
        this.mach_Status = mach_Status;
        this.mach_Last_Connect_Date = mach_Last_Connect_Date;
        this.mach_Type = mach_Type;
        this.cuscunuser = cuscunuser;
       
    }

   

   

    

    public Long getId_Cus_Machine() {
        return idcusmachine;
    }

    public void setId_Cus_Machine(Long idcusmachine) {
        this.idcusmachine = idcusmachine;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setMach_Fingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    

   

    public Date getMach_Start_Date() {
        return mach_Start_Date;
    }

    public void setMach_Start_Date(Date mach_Start_Date) {
        this.mach_Start_Date = mach_Start_Date;
    }

    public String getMach_Status() {
        return mach_Status;
    }

    public void setMach_Status(String mach_Status) {
        this.mach_Status = mach_Status;
    }

    public Date getMach_Last_Connect_Date() {
        return mach_Last_Connect_Date;
    }

    public void setMach_Last_Connect_Date(Date mach_Last_Connect_Date) {
        this.mach_Last_Connect_Date = mach_Last_Connect_Date;
    }

    public String getMach_Type() {
        return mach_Type;
    }

    public void setMach_Type(String mach_Type) {
        this.mach_Type = mach_Type;
    }

    public String getCuscunuser() {
        return cuscunuser;
    }

    public void setCuscunuser(String cuscunser) {
        this.cuscunuser = cuscunser;
    }

   

   

   
    

}
