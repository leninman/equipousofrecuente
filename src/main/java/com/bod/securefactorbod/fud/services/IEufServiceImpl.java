package com.bod.securefactorbod.fud.services;

import com.bod.securefactorbod.fud.daos.ICustomerDao;
//import com.bod.securefactorbod.fud.daos.IFingerprintDao;
import com.bod.securefactorbod.fud.daos.IFingerprintconfDao;
import com.bod.securefactorbod.fud.daos.IMachineDao;
import com.bod.securefactorbod.fud.daos.IgeneralConfigurationsDao;
import com.bod.securefactorbod.fud.models.Customer;
import com.bod.securefactorbod.fud.models.Customermachine;

import com.bod.securefactorbod.fud.models.Fingerprint;
import com.bod.securefactorbod.fud.models.Fingerprintconf;
import com.bod.securefactorbod.fud.models.Generalconfigurations;
import com.bod.securefactorbod.fud.models.Response;
import com.bod.securefactorbod.fud.utils.Constants;
import com.bod.securefactorbod.fud.utils.Utils;
import static com.bod.securefactorbod.fud.utils.Utils.sha1;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.servlet.http.HttpServletRequest;

@Service
public class IEufServiceImpl implements IEufService {

    Response result;

    Customermachine machine;

    Customer customer;

    Generalconfigurations generalconfiguration;

//    @Autowired
//    IFingerprintDao ifingerprintdao;
    @Autowired
    IMachineDao imachinedao;

    @Autowired
    ICustomerDao icustomerdao;

    @Autowired
    IgeneralConfigurationsDao igeneralconfigurationdao;

    @Autowired
    IFingerprintconfDao ifingerprintconfdao;

    @PersistenceContext
    private EntityManager em;

    HttpServletRequest request;

    private final IMachineDao imachinefindbyfinger;

    private final IMachineDao imachinefindbyfingeranduser;

    private final IMachineDao imachinefindbyuser;

    private final IMachineDao imachinefindbyuserandidmachine;

//    private final IFingerprintDao ifingerprintfindbyidmachine;
    IEufServiceImpl(IMachineDao imachinefindbyfinger, IMachineDao imachinefindbyuser, IMachineDao imachinefindbyfingeranduser, IMachineDao imachinefindbyuserandidmachine) {
        this.imachinefindbyfinger = imachinefindbyfinger;
        this.imachinefindbyuser = imachinefindbyuser;
//        this.ifingerprintfindbyidmachine = ifingerprintfindbyidmachine;
        this.imachinefindbyfingeranduser = imachinefindbyfingeranduser;
        this.imachinefindbyuserandidmachine = imachinefindbyuserandidmachine;

    }

    @Override
    public Response validate(Fingerprint finger) {
        // TODO Auto-generated method stub
        result = new Response();
        Customermachine machine = new Customermachine();
        String stringfinger;
        try {

            if (finger != null) {
                if (validateClient(finger.getId_Cuscun_User()).getResponseCode() == Constants.NOTVALIDCLIENT_CODE) {
                    result.setResponseCode(Constants.NOTVALIDCLIENT_CODE);
                    result.setResponseDescription(Constants.NOTVALIDCLIENT_DESC);
                } else {
                    stringfinger = Utils.getHashFinger(finger);
                    //machine = this.findMachineByFinger(stringfinger);

                    machine = findMachineByFingerAndUser(stringfinger, finger.getId_Cuscun_User());

                    if (machine != null) {
                        //  if (machine.getCuscunuser().equals(finger.getId_Cuscun_User())) {
                        result.setResponseCode(Constants.FINGERPRINTOK_CODE);
                        result.setResponseDescription(Constants.FINGERPRINTOK_DESC);
//                        result.setCustomermachine(machine);
                        // } else {
                        // result.setResponseCode(Constants.BADUSER_CODE);
                        // result.setResponseDescription(Constants.BADUSER_DESC);
                        // }
                    } else {
                        result = getPercentege(finger);
//                      result.setResponseCode(this.getPercentege(finger).getResponseCode());
//                      result.setResponseDescription(this.getPercentege(finger).getResponseDescription());

                    }

                }
            }

//            } else {
//
//                result.setResponseCode(102);
//                result.setResponseDescription("VALORES NULOS");
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Response enrollMachine(Fingerprint finger, HttpServletRequest req) {
        try {
            // TODO Auto-generated method stub
            result = new Response();
            machine = new Customermachine();
            generalconfiguration = new Generalconfigurations();

            String hashstring;
            hashstring = Utils.getHashFinger(finger);

            machine = findMachineByFingerAndUser(hashstring, finger.getId_Cuscun_User());

            if (machine == null) {

                int Numdevreg = numdevicesregistered(finger.getId_Cuscun_User());

                generalconfiguration = igeneralconfigurationdao.findConfByConfParam("NUM_ENROLLED");

                int maxdevsallowed = generalconfiguration.getConf_Value();

                if (Numdevreg == maxdevsallowed) {
                    removeoldestmachine(finger.getId_Cuscun_User());
                }

                Customermachine mach = new Customermachine();
                mach.setMach_Fingerprint(hashstring);
                mach.setMach_Start_Date(new Date());
                mach.setMach_Status("1");
                mach.setMach_Last_Connect_Date(new Date());
                if (req.getHeader("User-Agent").indexOf("Mobile") != -1) {
                    mach.setMach_Type("Mobile");
                } else {
                    mach.setMach_Type("PC");
                }
                mach.setCuscunuser(finger.getId_Cuscun_User());
                result = saveMachine(mach);
                //Customermachine machinesaved = this.findMachineByFinger(hashstring);
                //finger.setId_Machine(machinesaved.getId_Cus_Machine());
                //SALVA TODOS LOS PARAMETROS DE LA HUELLA DEL EQUIPO
                //ifingerprintdao.save(finger);
                // result.setResponseCode(Constants.MACHINESAVED_CODE);
                // result.setResponseDescription(Constants.MACHINESAVED_DESC);

            } else {
                result.setResponseCode(Constants.FINGERPRINTOK_CODE);
                result.setResponseDescription(Constants.FINGERPRINTOK_DESC);

            }

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(IEufServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public List<Customermachine> listEnrolledMachines(String usersharedkey) {
        // TODO Auto-generated method stub

        return imachinedao.findMachineBycuscunuser(usersharedkey);

    }

    @Override
    public Response retrieveMachineInfo() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Response validateClient(String shk) {
        // TODO Auto-generated method stub

        Response response = new Response();

        customer = new Customer();

        customer = findCustomer(shk);

        if (customer == null) {
            response.setResponseCode(Constants.NOTVALIDCLIENT_CODE);
            response.setResponseDescription(Constants.NOTVALIDCLIENT_DESC);
        } else {
            response.setResponseCode(Constants.VALIDCLIENT_CODE);
            response.setResponseDescription(Constants.VALIDCLIENT_DESC);
        }
        //System.out.println(response.getResponseCode());
        return response;
    }

    @Override
    public Customer findCustomer(String shk) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        return icustomerdao.findById(shk).orElse(null);

    }

    @Override
    public Customermachine findMachineByFinger(String Fingerprint) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Customermachine machine = new Customermachine();
        machine = imachinefindbyfinger.findMachineByFingerprint(Fingerprint);

        return machine;

    }

    @Override
    public Response saveMachine(Customermachine machine) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Response resp = new Response();
        imachinedao.save(machine);
        Customermachine mach = imachinedao.findMachineByFingerprintAndCuscunuser(machine.getFingerprint(), machine.getCuscunuser());
//        resp.setCustomermachine(mach);
        resp.setResponseCode(Constants.MACHINESAVED_CODE);
        resp.setResponseDescription(Constants.MACHINESAVED_DESC);
        Fingerprint fingermachine = new Fingerprint();
        fingermachine = Utils.recoverHashMapFingerprint(machine.getFingerprint());
        resp.setKeyValue(fingermachine.getVal_Key_Hash());
        return resp;

    }

    @Override
    public Response getPercentege(Fingerprint finger) {

        Response resp = new Response();
        try {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            int porcentajetotal = 0;

            int porcentajemayor = 0;

            generalconfiguration = new Generalconfigurations();

            generalconfiguration = igeneralconfigurationdao.findConfByConfParam("TOL_PER");

            int tolerance = generalconfiguration.getConf_Value();

            String hashmachine = Utils.getHashFinger(finger);

            List<Customermachine> machines;
//            Customermachine machine = new Customermachine();
            machines = new ArrayList<>();

            Fingerprint fingermachine = new Fingerprint();

//            machine = this.findMachineByFingerAndUser(hashmachine,finger.getId_Cuscun_User());
            machines = findMachineByUser(finger.getId_Cuscun_User());
//            machine = imachinefindbyuserandidmachine.findMachineByCuscunuserAndIdcusmachine(finger.getId_Cuscun_User(), finger.getId_Machine());
//              machine=this.findMachineByIdmachine(finger.getId_Machine());
            if (machines.size() != 0) { //VALIDA SI EL CLIENTE POSEE ALGUN EQUIPO REGISTRADO
                for (int i = 0; i < machines.size(); i++) {
                    fingermachine = Utils.recoverHashMapFingerprint(machines.get(i).getFingerprint());
//            fingermachine = Utils.recoverHashMapFingerprint(machine.getFingerprint());
                    porcentajetotal = 0;
                    String NameKeyHash = Utils.calulate_ValueHash_NameHash("NamekeyHash", finger.getUsername() + finger.getAzcriptor_Id());
                    String ValueKeyHash = Utils.calulate_ValueHash_NameHash("ValueKeyHash", finger.getUsername() + finger.getNum_Comp_Nav() + finger.getAgent_Us_Nav());

                    Fingerprintconf parameters = listFingerConfParameters();

//            if (machines != null && fingermachine != null) {
                    if (fingermachine.getNav_On_Line().equals(sha1(finger.getNav_On_Line()))) {
                        porcentajetotal = porcentajetotal + Integer.valueOf(parameters.getNav_On_Line());
                    }

                    if (fingermachine.getCookies_On().equals(sha1(finger.getCookies_On()))) {
                        porcentajetotal = porcentajetotal + Integer.valueOf(parameters.getCookies_On());
                    }

                    if (fingermachine.getNum_Ver_Prov().equals(sha1(finger.getNum_Ver_Prov()))) {
                        porcentajetotal = porcentajetotal + Integer.valueOf(parameters.getNum_Ver_Prov());
                    }

                    if (fingermachine.getName_Prov_Nav().equals(sha1(finger.getName_Prov_Nav()))) {
                        porcentajetotal = porcentajetotal + Integer.valueOf(parameters.getName_Prov_Nav());
                    }

                    if (fingermachine.getNum_Comp_Nav().equals(sha1(finger.getNum_Comp_Nav()))) {
                        porcentajetotal = porcentajetotal + Integer.valueOf(parameters.getNum_Comp_Nav());
                    }

                    if (fingermachine.getDen_Nav().equals(sha1(finger.getDen_Nav()))) {
                        porcentajetotal = porcentajetotal + Integer.valueOf(parameters.getDen_Nav());
                    }

                    if (fingermachine.getPlatform().equals(sha1(finger.getPlatform()))) {
                        porcentajetotal = porcentajetotal + Integer.valueOf(parameters.getPlatform());
                    }

                    if (fingermachine.getAgent_Us_Nav().equals(sha1(finger.getAgent_Us_Nav()))) {
                        porcentajetotal = porcentajetotal + Integer.valueOf(parameters.getAgent_Us_Nav());
                    }

                    if (fingermachine.getLang().equals(sha1(finger.getLang()))) {
                        porcentajetotal = porcentajetotal + Integer.valueOf(parameters.getLang());
                    }

                    if (fingermachine.getVersion_Nav().equals(sha1(finger.getVersion_Nav()))) {
                        porcentajetotal = porcentajetotal + Integer.valueOf(parameters.getVersion_Nav());
                    }

                    if (fingermachine.getNav_Name().equals(sha1(finger.getNav_Name()))) {
                        porcentajetotal = porcentajetotal + Integer.valueOf(parameters.getNav_Name());
                    }

                    if (fingermachine.getNo_Reg_User_Action().equals(sha1(finger.getNo_Reg_User_Action()))) {
                        porcentajetotal = porcentajetotal + Integer.valueOf(parameters.getNo_Reg_User_Action());
                    }

                    if (fingermachine.getJava_On().equals(sha1(finger.getJava_On()))) {
                        porcentajetotal = porcentajetotal + Integer.valueOf(parameters.getJava_On());
                    }

                    if (fingermachine.getIp_Addr().equals(sha1(finger.getIp_Addr()))) {
                        porcentajetotal = porcentajetotal + Integer.valueOf(parameters.getIp_Addr());
                    }

                    if (fingermachine.getMac_Addr().equals(sha1(finger.getMac_Addr()))) {
                        porcentajetotal = porcentajetotal + Integer.valueOf(parameters.getMac_Addr());
                    }

                    if (fingermachine.getVal_Key_Hash().equals(ValueKeyHash)) {
                        porcentajetotal = porcentajetotal + Integer.valueOf(parameters.getVal_Key_Hash());
                    }

                    if (fingermachine.getName_Key_Hash().equals(NameKeyHash)) {
                        porcentajetotal = porcentajetotal + Integer.valueOf(parameters.getName_Key_Hash());

                    }

                    if (fingermachine.getCod_Country().equals(sha1(finger.getCod_Country()))) {
                        porcentajetotal = porcentajetotal + Integer.valueOf(parameters.getCod_Country());
                    }

                    if (porcentajetotal > porcentajemayor) {
                        porcentajemayor = porcentajetotal;
//                        resp.setCustomermachine(machines.get(i));
                    }

                }
                if (porcentajetotal >= tolerance) {
                    resp.setResponseCode(Constants.FINGERPRINTOK_CODE);
                    resp.setResponseDescription(Constants.FINGERPRINTOK_DESC);
                } else {
                    resp.setResponseCode(Constants.FINGERPRINTBAD_CODE);
                    resp.setResponseDescription(Constants.FINGERPRINTBAD_DESC);
                }

            } else {

                resp.setResponseCode(Constants.NOREGISTEREDMACHINES_CODE);
                resp.setResponseDescription(Constants.NOREGISTEREDMACHINES_DESC);

            }

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(IEufServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

//    @Override
//    public List<Fingerprintconf> listFingerConfParameters() {
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        ArrayList<Fingerprintconf> listaparametros = new ArrayList<>();
//        return ifingerprintconfdao.findAll();
//    }
    @Override
    public List<Customermachine> findMachineByUser(String cuscunuser) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return imachinefindbyuser.findMachineBycuscunuser(cuscunuser);
    }

    @Override
    public Customermachine findMachineByFingerAndUser(String finger, String cuscunuser) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return imachinefindbyfingeranduser.findMachineByFingerprintAndCuscunuser(finger, cuscunuser);
    }

    @Override
    public Response saveFingerConfValues(Fingerprintconf fingerprintconfigurations) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        Response resp = new Response();

//        Fingerprintconf fingerprintconf = new Fingerprintconf();
        ifingerprintconfdao.save(fingerprintconfigurations);

        resp.setResponseCode(Constants.CONFFINGERSAFE_CODE);
        resp.setResponseDescription(Constants.CONFFINGERSAFE_DESC);

        return resp;
    }

    @Override
    public Fingerprintconf listFingerConfParameters() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return ifingerprintconfdao.findById(1).orElse(null);

    }

    @Override
    public Customermachine findMachineByIdmachine(Long idmachine) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Customermachine machine = new Customermachine();
        machine = imachinedao.findById(idmachine).orElse(null);
        return machine;
    }

    public int numdevicesregistered(String idcuscunuser) {

        List<Customermachine> machines;

        machines = new ArrayList<>();

        Fingerprint fingermachine = new Fingerprint();

        machines = findMachineByUser(idcuscunuser);

        return machines.size();

    }

    @Override
    public void removeoldestmachine(String user) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        Date oldestdate;

//        Query q = em.createNamedQuery("Customermachine.findOldestUseMachine");
//        
//        q.setParameter("cuscunuser", user);
//        
//        Customermachine oldestmachine=(Customermachine) q.getSingleResult();
        Customermachine oldestmachine = imachinedao.findOldestUseMachine(user);

//        oldestdate=oldestmachine.getMach_Last_Connect_Date();
        imachinedao.delete(oldestmachine);

    }

}
