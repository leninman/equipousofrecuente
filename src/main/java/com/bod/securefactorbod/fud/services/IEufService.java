package com.bod.securefactorbod.fud.services;


import com.bod.securefactorbod.fud.models.Customer;
import com.bod.securefactorbod.fud.models.Customermachine;
import com.bod.securefactorbod.fud.models.Fingerprint;
import com.bod.securefactorbod.fud.models.Fingerprintconf;
import com.bod.securefactorbod.fud.models.Response;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public interface IEufService {
 	
	public Response validate(Fingerprint finger);
        
        public Response enrollMachine(Fingerprint finger,HttpServletRequest req);
        
        public Response validateClient(String shk);
        
        public Customer findCustomer(String shk);
        
        public Customermachine findMachineByFinger(String finger);
        
        public Customermachine findMachineByFingerAndUser(String finger, String cuscunuser);
        
    
        public List<Customermachine> findMachineByUser(String cuscunuser);
        
        public Customermachine findMachineByIdmachine(Long idmachine);
        
        public void removeoldestmachine(String cuscunuser);
        
        
        public Response saveMachine(Customermachine machine);
        
        public Response getPercentege(Fingerprint finger);
       
	public List<Customermachine> listEnrolledMachines(String cuscunuser);
        
        public Fingerprintconf listFingerConfParameters();
        
        public Response saveFingerConfValues(Fingerprintconf fingerprintconfigurations);
	
//       Retorna la información asociada a un dispositivo enrolado en formato XML
        public Response retrieveMachineInfo();
        
        
	   
}
