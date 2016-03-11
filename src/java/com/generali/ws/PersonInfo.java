/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.generali.ws;

import com.generali.domain.User;
import com.generali.domain.UserHelper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 *
 * @author Marko
 * 
 * Web servis sa operacijama.
 * 
 */
@WebService(serviceName = "PersonInfo")
public class PersonInfo {
    

    /**
     * Metoda web servisa koja za parametar 'name' dohvaća i vraća 
     * listu svih osoba sa traženim imenom
     * @param name
     * @return lista osoba
     */
    @WebMethod(operationName = "getUserByName")
    public java.util.List<com.generali.domain.User> getUserByName(@WebParam(name = "name") String name) {
        
        UserHelper helper = new UserHelper();
        List<User> users = helper.getUserByName(name);
        
        return users;
    }

    /**
     * Metoda web servisa koja za oib dohvaća i vraća jedinstvenog korisnika
     * @param oib
     * return osoba
     */
    @WebMethod(operationName = "getUserByOib")
    public User getUserByOib(@WebParam(name = "oib") String oib) {
        //TODO write your implementation code here:
        UserHelper helper = new UserHelper();
        User user = helper.getUserByOIB(oib);
        
        return user;
    }

    /**
     * Metoda dodaje novog korisnika na temelju ulaznih parametara
     * @param name
     * @param surname
     * @param oib
     * @param street
     * @param houseNum
     * @param city
     * @param birthDate - tipa String radi testiranja, kasnije se konvertira u Date
     * return rezultat dodavanja novog korisnika (uspješno ili neuspješno)
     */
    @WebMethod(operationName = "addNewUser")
    public boolean addNewUser(@WebParam(name = "name") String name, @WebParam(name = "surname") String surname, @WebParam(name = "oib") String oib, @WebParam(name = "street") String street, @WebParam(name = "houseNum") String houseNum, @WebParam(name = "city") String city, @WebParam(name = "birthDate") String birthDate) {
        
        UserHelper helper = new UserHelper();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        boolean userAdded = false;
        
        try {
            Date dateF = sdf.parse(birthDate);
            userAdded = helper.addNewUser(name, surname, oib, street, houseNum, city, dateF);
        } catch (ParseException ex) {
            Logger.getLogger(PersonInfo.class.getName()).log(Level.SEVERE, null, ex);
        } 
                
        return userAdded;
    }

    /**
     * Metoda ažurira podatke o korisniku sa određenim ID-om. Upisani podaci 
     * gaze stare podatke pa bi za unaprijeđenje funkcionalnosti trebalo uvjetno 
     * provjeriti za svaki parametar je li prazan i ažurirati samo ona polja
     * koja imaju neku vrijednost.
     * 
     * @param id
     * @param name
     * @param surname
     * @param oib
     * @param street
     * @param houseNum
     * @param city
     * @param birthDate - tipa String radi testiranja, kasnije se konvertira u Date
     * return rezultat ažuriranja novog korisnika (uspješno ili neuspješno)
     */
    @WebMethod(operationName = "updateUserByOIB")
    public Boolean updateUserByOIB(@WebParam(name = "id") int id, @WebParam(name = "oib") String oib, @WebParam(name = "name") String name, @WebParam(name = "surname") String surname, @WebParam(name = "street") String street, @WebParam(name = "houseNum") String houseNum, @WebParam(name = "city") String city, @WebParam(name = "birthDate") String birthDate) {
        UserHelper helper = new UserHelper();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        boolean userAdded = false;
        try { 
            Date dateF = sdf.parse(birthDate);
            userAdded = helper.updateUserByID(id, name, surname, oib, street, houseNum, city, dateF);
        } catch (ParseException ex) {
            Logger.getLogger(PersonInfo.class.getName()).log(Level.SEVERE, null, ex);
        } 
                
        return userAdded;
    }
    
    
}
