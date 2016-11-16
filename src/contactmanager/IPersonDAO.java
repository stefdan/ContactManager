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
public interface IPersonDAO {
    int create(Person p);
    Person find(int personid);
    boolean update(Person p);
    boolean delete(Person p);
    boolean delete(int personid);
    List<Person> getAll();  
}
