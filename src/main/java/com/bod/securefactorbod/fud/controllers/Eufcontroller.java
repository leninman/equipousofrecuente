package com.bod.securefactorbod.fud.controllers;

import com.bod.securefactorbod.fud.models.Customermachine;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bod.securefactorbod.fud.models.Fingerprint;
import com.bod.securefactorbod.fud.models.Fingerprintconf;
import com.bod.securefactorbod.fud.models.Response;
import com.bod.securefactorbod.fud.services.IEufService;
import java.util.ArrayList;

import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import static javax.swing.text.html.FormSubmitEvent.MethodType.GET;
import static org.springframework.http.HttpMethod.GET;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping(path = "/euf")
public class Eufcontroller {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    IEufService eufService;

    Response resp;

    @RequestMapping(method = RequestMethod.POST, path = "/validate",
            consumes = "application/json",
            produces = "application/json")
    public @ResponseBody
    Response validate(@RequestBody Fingerprint finger) {
        Response resp = new Response();

        resp = eufService.validate(finger);

        return resp;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/save",
            consumes = "application/json",
            produces = "application/json")
    public @ResponseBody
    Response save(@RequestBody Fingerprint finger,HttpServletRequest req) {
        Response resp = new Response();

        resp = eufService.enrollMachine(finger,req);

        return resp;
    }
    
    
     @RequestMapping(method = RequestMethod.POST, path = "/saveconfigurationfinger",
            consumes = "application/json",
            produces = "application/json")
    public @ResponseBody
    Response saveconfigurationfinger(@RequestBody Fingerprintconf fingerconfiguration) {
        Response resp = new Response();

        resp = eufService.saveFingerConfValues(fingerconfiguration);

        return resp;
    }
    
    

    @RequestMapping(method = RequestMethod.POST, path = "/getenrolledmachines/{usershk}",
            consumes = "application/json",
            produces = "application/json")
    public List<Customermachine> getEnrolledMachines(@PathVariable(value="usershk") String usershk) {
        List<Customermachine> machines=new ArrayList<Customermachine>();

        machines= eufService.listEnrolledMachines(usershk);

        return machines;
    }
    
}
