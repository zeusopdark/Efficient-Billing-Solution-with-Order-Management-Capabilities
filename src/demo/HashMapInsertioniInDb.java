/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Deepak
 */
public class HashMapInsertioniInDb
{
    public static void main(String[] args)
    {
        //{bs/102=[Teady bear, 3000, 4], bs/101=[Reebook Tshirt, 1000, 1]}
        //{bs/102=[Teady bear, 3000, 4], bs/101=[Reebook Tshirt, 1000, 1]}
        
        ArrayList al1=new ArrayList();
        al1.add("Teady bear");
        al1.add(3000);
        al1.add(4);
        
        ArrayList al2=new ArrayList();
        al2.add("Reebook Tshirt");
        al2.add(1000);
        al2.add(1);
        
        HashMap hm=new HashMap();
        hm.put("bs/102", al1);
        hm.put("bs/101", al2);
        
        String customer_phno="9876543211";
        String emp_email="aaa@gmail.com";
        
        LocalDate ld=LocalDate.now();
        String date1=ld.toString();
        
        LocalTime lt=LocalTime.now();
        String time1=lt.toString();
        
        //---------------------------------------------------
        
        HashMapInsertioniInDb hmi=new HashMapInsertioniInDb();
        
        //String items=hmi.hashmapToString(hm);
        //hmi.insertData(customer_phno, items, date1, time1, emp_email);
        
        hmi.getData();
    }
    
    void insertData(String customer_phno, String items, String date1, String time1, String emp_email)
    {
        try
        {
            Connection con=dbcon.DbConnection.getConnection();
            
            PreparedStatement ps=con.prepareStatement("insert into shopping_demo values(?,?,?,?,?)");
            ps.setString(1, customer_phno);
            ps.setString(2, items);
            ps.setString(3, date1);
            ps.setString(4, time1);
            ps.setString(5, emp_email);
            int i=ps.executeUpdate();
            if(i>0)
            {
                System.out.println("insertion success");
            }
            else
            {
                System.out.println("insertion failed");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    void getData()
    {
        try
        {
            Connection con=dbcon.DbConnection.getConnection();
            
            PreparedStatement ps=con.prepareStatement("select * from shopping_demo");
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                System.out.println(rs.getString("customer_phno"));
                System.out.println(rs.getString("datee"));
                System.out.println(rs.getString("timee"));
                System.out.println(rs.getString("emp_email"));
                System.out.println(rs.getString("customer_items"));
            }
            else
            {
                System.out.println("getting data failed");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    String hashmapToString(HashMap<String, ArrayList> hm)
    {
        String items="";
        for(Map.Entry me:hm.entrySet())
        {
            items=items+me.getKey()+",";
            ArrayList al=(ArrayList)me.getValue();
            items=items+al.get(0)+",";
            items=items+al.get(1)+",";
            items=items+al.get(2)+",";
        }
        return items;
    }
}
