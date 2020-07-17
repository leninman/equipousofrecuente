/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.securefactorbod.fud.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author lmanrique
 */
@Entity
@Table(name="MACHINE_FINGERPRINT_PARAMETERS")
public class Fingerprintconf implements Serializable{

        @Id
        @Column(name="id")
        private int id;
    
        @Column(name="NAV_ON_LINE")
        private String nav_On_Line;
	
        @Column(name="COOKIES_ON")
	private String cookies_On;
	
	@Column(name="NUM_VER_PROV")
        private String num_Ver_Prov;
	
	 @Column(name="NAME_PROV_NAV")
        private String name_Prov_Nav;
	
	 @Column(name="NUM_COMP_NAV")
         private String num_Comp_Nav;
	
	 @Column(name="DEN_NAV")
         private String den_Nav;
	
	 @Column(name="PLATFORM")
         private String platform;
	
	 @Column(name="AGENT_US_NAV")
         private String agent_Us_Nav;
	
	 @Column(name="LANG")
         private String lang;
         
         @Column(name="VERSION_NAV")
         private String version_Nav;
         
         @Column(name="NAV_NAME")
         private String nav_Name;
	
	@Column(name="NO_REG_USER_ACTION")
         private String no_Reg_User_Action;
	
	@Column(name="JAVA_ON")
        private String java_On;
	
	@Column(name="IP_ADDR")
        private String ip_Addr;
        
        @Column(name="MAC_ADDR")
        private String mac_Addr;
	

	@Column(name="COD_COUNTRY")
        private String cod_Country;
        
        
        @Column(name="VAL_KEY_HASH")
        private String val_Key_Hash;
        
        @Column(name="NAME_KEY_HASH")
        private String name_Key_Hash;
        
        
        public Fingerprintconf(){
            
        }

    public Fingerprintconf(int id, String nav_On_Line, String cookies_On, String num_Ver_Prov, String name_Prov_Nav, String num_Comp_Nav, String den_Nav, String platform, String agent_Us_Nav, String lang, String version_Nav, String nav_Name, String no_Reg_User_Action, String java_On, String ip_Addr, String mac_Addr, String cod_Country, String val_Key_Hash, String name_Key_Hash) {
        this.id = id;
        this.nav_On_Line = nav_On_Line;
        this.cookies_On = cookies_On;
        this.num_Ver_Prov = num_Ver_Prov;
        this.name_Prov_Nav = name_Prov_Nav;
        this.num_Comp_Nav = num_Comp_Nav;
        this.den_Nav = den_Nav;
        this.platform = platform;
        this.agent_Us_Nav = agent_Us_Nav;
        this.lang = lang;
        this.version_Nav = version_Nav;
        this.nav_Name = nav_Name;
        this.no_Reg_User_Action = no_Reg_User_Action;
        this.java_On = java_On;
        this.ip_Addr = ip_Addr;
        this.mac_Addr = mac_Addr;
        this.cod_Country = cod_Country;
        this.val_Key_Hash = val_Key_Hash;
        this.name_Key_Hash = name_Key_Hash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNav_On_Line() {
        return nav_On_Line;
    }

    public void setNav_On_Line(String nav_On_Line) {
        this.nav_On_Line = nav_On_Line;
    }

    public String getCookies_On() {
        return cookies_On;
    }

    public void setCookies_On(String cookies_On) {
        this.cookies_On = cookies_On;
    }

    public String getNum_Ver_Prov() {
        return num_Ver_Prov;
    }

    public void setNum_Ver_Prov(String num_Ver_Prov) {
        this.num_Ver_Prov = num_Ver_Prov;
    }

    public String getName_Prov_Nav() {
        return name_Prov_Nav;
    }

    public void setName_Prov_Nav(String name_Prov_Nav) {
        this.name_Prov_Nav = name_Prov_Nav;
    }

    public String getNum_Comp_Nav() {
        return num_Comp_Nav;
    }

    public void setNum_Comp_Nav(String num_Comp_Nav) {
        this.num_Comp_Nav = num_Comp_Nav;
    }

    public String getDen_Nav() {
        return den_Nav;
    }

    public void setDen_Nav(String den_Nav) {
        this.den_Nav = den_Nav;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getAgent_Us_Nav() {
        return agent_Us_Nav;
    }

    public void setAgent_Us_Nav(String agent_Us_Nav) {
        this.agent_Us_Nav = agent_Us_Nav;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getVersion_Nav() {
        return version_Nav;
    }

    public void setVersion_Nav(String version_Nav) {
        this.version_Nav = version_Nav;
    }

    public String getNav_Name() {
        return nav_Name;
    }

    public void setNav_Name(String nav_Name) {
        this.nav_Name = nav_Name;
    }

    public String getNo_Reg_User_Action() {
        return no_Reg_User_Action;
    }

    public void setNo_Reg_User_Action(String no_Reg_User_Action) {
        this.no_Reg_User_Action = no_Reg_User_Action;
    }

    public String getJava_On() {
        return java_On;
    }

    public void setJava_On(String java_On) {
        this.java_On = java_On;
    }

    public String getIp_Addr() {
        return ip_Addr;
    }

    public void setIp_Addr(String ip_Addr) {
        this.ip_Addr = ip_Addr;
    }

    public String getMac_Addr() {
        return mac_Addr;
    }

    public void setMac_Addr(String mac_Addr) {
        this.mac_Addr = mac_Addr;
    }

    public String getCod_Country() {
        return cod_Country;
    }

    public void setCod_Country(String cod_Country) {
        this.cod_Country = cod_Country;
    }

    public String getVal_Key_Hash() {
        return val_Key_Hash;
    }

    public void setVal_Key_Hash(String val_Key_Hash) {
        this.val_Key_Hash = val_Key_Hash;
    }

    public String getName_Key_Hash() {
        return name_Key_Hash;
    }

    public void setName_Key_Hash(String name_Key_Hash) {
        this.name_Key_Hash = name_Key_Hash;
    }
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
    
    
    
     

    

   
    

    
}
