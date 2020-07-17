/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.securefactorbod.fud.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 *
 * @author lmanrique
 */

//@NamedNativeQueries({
//
//    @NamedNativeQuery(name="Generalconfigurations.findConfByConfParam",
//            
//            query="SELECT u from CUSTOMER_MACHINE u WHERE u.MACH_LAST_CONNECT_DATE "
//                    + "in (SELECT max(v.LAST_CONNECT_DATE) from CUSTOMER_MACHINE v WHERE "
//                    + "v.MACH_ID_CUSCUN_USER=:cuscunuser)",
//            resultClass = Customermachine.class
//    )
//
//
//})
@Entity
@Table(name = "COMMON_GENERAL_CONFIGURATION")
public class Generalconfigurations implements Serializable {

    @Id
    @Column(name = "ID_CONF")
    private Long id_Conf;

    @Column(name = "CONF_PARAM")
    private String confParam;

    @Column(name = "CONF_PARAM_DESC")
    private String conf_Param_Desc;

    @Column(name = "CONF_VALUE")
    private Integer conf_Value;

    @Column(name = "CONF_LAST_UPDATE")
    private Date conf_Last_Update;

   public Generalconfigurations(){
       
   }
    
    
    public Generalconfigurations(Long id_Conf, String confParam, String conf_Param_Desc, Integer conf_Value, Date conf_Last_Update) {
        this.id_Conf = id_Conf;
        this.confParam = confParam;
        this.conf_Param_Desc = conf_Param_Desc;
        this.conf_Value = conf_Value;
        this.conf_Last_Update = conf_Last_Update;
    }
    
    
    

    public Long getId_Conf() {
        return id_Conf;
    }

    public void setId_Conf(Long id_Conf) {
        this.id_Conf = id_Conf;
    }

    public String getConf_Param() {
        return confParam;
    }

    public void setConf_Param(String confParam) {
        this.confParam = confParam;
    }

    public String getConf_Param_Desc() {
        return conf_Param_Desc;
    }

    public void setConf_Param_Desc(String conf_Param_Desc) {
        this.conf_Param_Desc = conf_Param_Desc;
    }

    public Integer getConf_Value() {
        return conf_Value;
    }

    public void setConf_Value(Integer conf_Value) {
        this.conf_Value = conf_Value;
    }

    public Date getConf_Last_Update() {
        return conf_Last_Update;
    }

    public void setConf_Last_Update(Date conf_Last_Update) {
        this.conf_Last_Update = conf_Last_Update;
    }
    
    
    

}
