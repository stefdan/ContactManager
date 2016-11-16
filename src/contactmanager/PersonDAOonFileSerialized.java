/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactmanager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class PersonDAOonFileSerialized implements IPersonDAO{
    private String datapath = null;
    private ArrayList<Person> persons = new ArrayList<>();

    public PersonDAOonFileSerialized(String datapath) {
        this.datapath = datapath;
        loadData();
    }
   
    @Override
    public int create(Person p) {
        int retval = getNextID();
        p.id = retval;
        persons.add(p);
        saveData();
        return retval;
    }
    private int getNextID()
    {
        int retval = 1;
        for (Person person : persons) {
            if(retval < person.id)
                retval = person.id;
        }
        return retval;
    }

    @Override
    public Person find(int personid) {
        Person retval = null;
        for (Person person : persons) {
            if(person.id == personid){
                retval = person;
                break;
            }
        }
        return retval;
    }

    @Override
    public boolean update(Person p) {
        boolean retval = false;
//        for (int i = 0; i < persons.size(); i++) {
//            if(persons.get(i).id == p.id)
//            {
//                persons.set(i, p);
//                retval = true;
//                saveData();
//                break;
//            }
//        }
        int index = getPersonIndex(p.id);
        if(index >= 0){
            persons.set(index, p);
            retval = true;
            saveData();
        }
        return retval;
    }

    @Override
    public boolean delete(Person p) {
        boolean retval = false;
        int index = getPersonIndex(p.id);
        if(index >= 0){
            persons.remove(index);
            retval = true;
            saveData();
        }
        return retval;
    }

    @Override
    public boolean delete(int personid) {
        boolean retval = false;
        int index = getPersonIndex(personid);
        if(index >= 0){
            persons.remove(index);
            retval = true;
            saveData();
        }
        return retval;
    }

    @Override
    public List<Person> getAll() {
        List<Person> retval = persons;
        return retval;
    }
    
    private void loadData(){
        try(FileInputStream fis = new FileInputStream(datapath);) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            persons.clear();
            persons = (ArrayList<Person>)o;
        } catch(IOException ioex) {
            System.out.println("IOexception in loadData: \n" + ioex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found in loadData: \n" + ex.getMessage());
        }
    }
    private void saveData()
    {
        try(FileOutputStream fos = new FileOutputStream(datapath);) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(persons);
        } catch(IOException ioex) {
            System.out.println("IOexception in saveData: \n" + ioex.getMessage());
        } 
    }
    private int getPersonIndex(int PersonID)
    {
        int retval = -1;
        for (int i = 0; i < persons.size(); i++) {
            if(persons.get(i).id == PersonID)
            {
                retval = i;
                break;
            }
        }
        return retval;
    }
    
}
