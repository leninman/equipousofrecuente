/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.securefactorbod.fud.daos;

import com.bod.securefactorbod.fud.models.Fingerprintconf;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author lmanrique
 */
public interface IFingerprintconfDao extends JpaRepository<Fingerprintconf,Integer> {
    
}
