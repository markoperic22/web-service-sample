/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.generali.domain;

import com.generali.util.HibernateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Marko
 * Pomoćna klasa čije metode komuniciraju sa bazom pomoću Hibernate-a
 */
public class UserHelper {
    Session session = null;
    
    public UserHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public List<User> getUserByName(String name) {
        List<User> users = null;
        
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from User where name = '" + name + "'");
        users = query.list();
        
        return users;
    }
    
    public User getUserByOIB(String oib) {
        User user = null;
        
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from User where oib = '" + oib + "'");
        user = (User) query.uniqueResult();
        
        
        return user;
    }
    
    public boolean addNewUser(String name, String surname, String oib, String street, String houseNum, String city, Date birthDate) {
        
        try {
            Transaction tx = session.beginTransaction();

            User user = new User();
            user.setName(name);
            user.setSurname(surname);
            user.setOib(oib);
            user.setHNum(houseNum);
            user.setCity(city);
            user.setStreet(street);
            user.setBirthDate(birthDate);

            session.save(user);
            tx.commit();
            return true;
            
        } catch (Exception ex) {
            return false;
        }
        
    }
    
    public boolean updateUserByID(int id, String name, String surname, String oib, String street, String houseNum, String city, Date birthDate) {
         
        try {
            Transaction tx = session.beginTransaction();  
            
            Query query = session.createQuery("from User where id = '" + id + "'");
            User user = (User) query.uniqueResult();
           
            user.setName(name);
            user.setSurname(surname);
            user.setOib(oib);
            user.setStreet(street);
            user.setHNum(houseNum);
            user.setCity(city);
            user.setBirthDate(birthDate);
            
            session.update(user); 
            tx.commit();
            return true;
           
        } catch (Exception ex) {
            return false;
        }
        
    }
    
}
