/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.securefactorbod.fud.utils;

/**
 *
 * @author lmanrique
 */
import com.bod.securefactorbod.fud.models.Fingerprint;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

public class Utils {

    static ArrayList<Fingerprint> arrayList;

//OBTIENE EL HASH DE LA HUELLA COMPLETA
    /*  public static String getHashFinger(Fingerprint finger) {

        String result = "";
                                 
       String fingerstring=finger.getNav_On_Line()
                            +";"+finger.getCookies_On()
                            +";"+finger.getNum_Ver_Prov()
                            +";"+finger.getName_Prov_Nav()
                            +";"+finger.getNum_Comp_Nav()
                            +";"+finger.getDen_Nav()
                            +";"+finger.getPlatform()
                            +";"+finger.getAgent_Us_Nav()
                            +"+"+finger.getLang()
                            +";"+finger.getVersion_Nav()
                            +";"+finger.getNo_Reg_User_Action()
                            +";"+finger.getJava_On()
                            +";"+finger.getIp_Addr()
                            +";"+finger.getMac_Addr()
                            +","+finger.getCookie_Value()
                            +";"+finger.getName_Cookie()
                            +";"+finger.getCod_Country();
                                
                
        
        
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(fingerstring.getBytes("utf8"));
            result = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }


        
        return result;
    }*/
    public static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public static String calulate_ValueHash_NameHash(String typecalculation, String invalue) throws NoSuchAlgorithmException {

        String valueCalculated;

        if (typecalculation.equalsIgnoreCase("ValueKeyHash")) {

            valueCalculated = sha1(invalue);

        } else {

            valueCalculated = sha1(invalue);

        }

        return valueCalculated;

    }

    public static String getHashFinger(Fingerprint finger) throws NoSuchAlgorithmException {

        String fingerprint = "";
        String ValueKeyHash = "";
        String NamekeyHash = "";

        String value1 = sha1(finger.getNav_On_Line());
        String value2 = sha1(finger.getCookies_On());
        String value3 = sha1(finger.getNum_Ver_Prov());
        String value4 = sha1(finger.getName_Prov_Nav());
        String value5 = sha1(finger.getNum_Comp_Nav());
        String value6 = sha1(finger.getDen_Nav());
        String value7 = sha1(finger.getPlatform());
        String value8 = sha1(finger.getAgent_Us_Nav());
        String value9 = sha1(finger.getLang());
        String value10 = sha1(finger.getVersion_Nav());
        String value11 = sha1(finger.getNav_Name());
        String value12 = sha1(finger.getNo_Reg_User_Action());
        String value13 = sha1(finger.getJava_On());
        String value14 = sha1(finger.getIp_Addr());
        String value15 = sha1(finger.getMac_Addr());
        String value16 = sha1(finger.getCod_Country());

        NamekeyHash = calulate_ValueHash_NameHash("NamekeyHash", finger.getUsername() + finger.getAzcriptor_Id());
        ValueKeyHash = calulate_ValueHash_NameHash("ValueKeyHash", finger.getUsername() + finger.getNum_Comp_Nav() + finger.getAgent_Us_Nav());

        fingerprint = value1 + "@" + value2 + "@" + value3 + "@" + value4 + "@" + value5 + "@" + value6 + "@" + value7 + "@" + value8 + "@" + value9
                + "@" + value10 + "@" + value11 + "@" + value12 + "@" + value13 + "@" + value14 + "@" + value15 + "@" + value16 + "@" + NamekeyHash + "@" + ValueKeyHash;

        return fingerprint;
    }

    //static HashMap<String, String> recoverHashMapFingerprint(int index){
    public static Fingerprint recoverHashMapFingerprint(String finger) {

        //    HashMap<String, String> fingerprintMap = new HashMap<String, String>();
        Fingerprint fingermachine = new Fingerprint();
        String[] partsFingerprint = finger.split("@");

        fingermachine.setNav_On_Line(partsFingerprint[0]);
        fingermachine.setCookies_On(partsFingerprint[1]);
        fingermachine.setNum_Ver_Prov(partsFingerprint[2]);
        fingermachine.setName_Prov_Nav(partsFingerprint[3]);
        fingermachine.setNum_Comp_Nav(partsFingerprint[4]);
        fingermachine.setDen_Nav(partsFingerprint[5]);
        fingermachine.setPlatform(partsFingerprint[6]);
        fingermachine.setAgent_Us_Nav(partsFingerprint[7]);
        fingermachine.setLang(partsFingerprint[8]);
        fingermachine.setVersion_Nav(partsFingerprint[9]);
        fingermachine.setNav_Name(partsFingerprint[10]);
        fingermachine.setNo_Reg_User_Action(partsFingerprint[11]);
        fingermachine.setJava_On(partsFingerprint[12]);
        fingermachine.setIp_Addr(partsFingerprint[13]);
        fingermachine.setMac_Addr(partsFingerprint[14]);
        fingermachine.setCod_Country(partsFingerprint[15]);
        fingermachine.setName_Key_Hash(partsFingerprint[16]);
        fingermachine.setVal_Key_Hash(partsFingerprint[17]);

        //       partsFingerprint = arrayList.get(index).getFingerprint().split("@");
//           fingerprintMap.put("value_online",   partsFingerprint[0]);
//           fingerprintMap.put("value_coockies", partsFingerprint[1]);
//           fingerprintMap.put("value_version",  partsFingerprint[2]);
//           fingerprintMap.put("value_nombre",   partsFingerprint[3]);
//           fingerprintMap.put("value_ncopila",  partsFingerprint[4]);
//           fingerprintMap.put("value_dnaveg",   partsFingerprint[5]);
//           fingerprintMap.put("value_pforma",   partsFingerprint[6]);
//           fingerprintMap.put("value_agente",   partsFingerprint[7]);
//           fingerprintMap.put("value_idioma",   partsFingerprint[8]);
//           fingerprintMap.put("value_vernav",   partsFingerprint[9]);
//           fingerprintMap.put("value_nonav",    partsFingerprint[10]);
//           fingerprintMap.put("value_noregacc", partsFingerprint[11]);
//           fingerprintMap.put("value_javahabi", partsFingerprint[12]);
//           fingerprintMap.put("value_codpais",  partsFingerprint[13]);
//           fingerprintMap.put("value_ip",       partsFingerprint[14]);
//           fingerprintMap.put("value_mac",      partsFingerprint[15]);
//           fingerprintMap.put("NamekeyHash",    partsFingerprint[16]);
//           fingerprintMap.put("ValueKeyHash",   partsFingerprint[17]);
//        
        return fingermachine;
    }

}
