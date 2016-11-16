/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactmanager;

import java.util.List;

/**
 *
 * @author Admin
 */
public class ContactManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Person p = new Person();
        p.firstname = "Toni";
        p.lastname = "Tester";
        p.birthday = "01.01.1992";
        p.city = "Berlin";
        
        PersonDAOonFileSerialized persdao = new PersonDAOonFileSerialized("c:\\Test\\PersData.dat");
        persdao.create(p);
        
        List<Person> perslist = persdao.getAll();
        System.out.println(perslist.get(0));
    }
    
}
