/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.securefactorbod.fud.daos;

import com.bod.securefactorbod.fud.models.Customermachine;
import com.bod.securefactorbod.fud.models.Generalconfigurations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author lmanrique
 */
public interface IgeneralConfigurationsDao extends JpaRepository<Generalconfigurations, Long> {

    Generalconfigurations findConfByConfParam(String ConfParam);
    

}
