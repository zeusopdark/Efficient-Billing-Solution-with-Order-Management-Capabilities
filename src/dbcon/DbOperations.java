/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbcon;

import gettersetter.RegisterModel;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Deepak
 */
public class DbOperations 
{
    public static ResultSet login(String email1, String pass1)
    {
        ResultSet rs=null;
        try
        {
            Connection con=dbcon.DbConnection.getConnection();
            
            PreparedStatement ps=con.prepareStatement("select * from register where email=? and password=?");
            ps.setString(1, email1);
            ps.setString(2, pass1);
            
            rs=ps.executeQuery();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return rs;
    }
    
    public static boolean register(RegisterModel reg)
    {
        boolean status=false;
        try
        {
            Connection con=DbConnection.getConnection();
            
            PreparedStatement ps=con.prepareStatement("insert into register values(?,?,?,?,?,?)");
            
            ps.setString(1, reg.getName());
            ps.setString(2, reg.getEmail());
            ps.setString(3, reg.getPassword());
            ps.setString(4, reg.getGender());
            ps.setString(5, reg.getPhoneno());
            ps.setString(6, reg.getModule());
            
            int i=ps.executeUpdate();
            if(i>0)
            {
                status=true;
            }
            else
            {
                status=false;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return status;
    }
    
    public static ResultSet showAllEmpsData()
    {
        ResultSet rs = null;
        try
        {
            Connection con=DbConnection.getConnection();
            
            PreparedStatement ps=con.prepareStatement("select * from register where module='Employee'");
            rs=ps.executeQuery();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return rs;
    }
    public static ResultSet getSelectedEmployee(String email)
    {
        ResultSet rs = null;
        try
        {
            Connection con=DbConnection.getConnection();
            
            PreparedStatement ps=con.prepareStatement("select * from register where email=?");
            ps.setString(1, email);
            
            rs=ps.executeQuery();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return rs;
    }
    
    public static int updateEmpDetails(RegisterModel reg)
    {
        int i = 0;
        try
        {
            Connection con=DbConnection.getConnection();
            
            PreparedStatement ps=con.prepareStatement("update register set name=?, password=?, gender=?, phone_no=? where email=?");
            ps.setString(1, reg.getName());
            ps.setString(2, reg.getPassword());
            ps.setString(3, reg.getGender());
            ps.setString(4, reg.getPhoneno());
            ps.setString(5, reg.getEmail());
            
            i=ps.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return i;
    }
    
    public static int deleteEmpDetails(String email)
    {
        int i = 0;
        try
        {
            Connection con=DbConnection.getConnection();
            
            PreparedStatement ps=con.prepareStatement("delete from register where email=?");
            ps.setString(1, email);
            
            i=ps.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return i;
    }
    
    public static boolean insertItemDetails(FileInputStream fis, String... str)
    {
        boolean status=false;
        try
        {
            Connection con=DbConnection.getConnection();
            
            PreparedStatement ps=con.prepareStatement("insert into items values(?,?,?,?,?,?)");
            ps.setString(1, str[0]);
            ps.setString(2, str[1]);
            ps.setString(3, str[2]);
            ps.setString(4, str[3]);
            ps.setString(5, str[4]);
            ps.setBinaryStream(6, fis);
            
            int i=ps.executeUpdate();
            if(i>0)
            {
                status=true;
            }
            else
            {
                status=false;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return status;
    }
    
    public static ResultSet getAllItems()
    {
        ResultSet rs = null;
        try
        {
            Connection con=DbConnection.getConnection();
            
            PreparedStatement ps=con.prepareStatement("select * from items");
            rs=ps.executeQuery();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return rs;
    }
    public static ResultSet getItemDetails(String item_id)
    {
        ResultSet rs = null;
        try
        {
            Connection con=DbConnection.getConnection();
            
            PreparedStatement ps=con.prepareStatement("select * from items where item_id=?");
            ps.setString(1, item_id);
            rs=ps.executeQuery();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return rs;
    }
    
    public static boolean deleteItem(String item_id)
    {
        boolean status=false;
        try
        {
            Connection con=DbConnection.getConnection();
            
            PreparedStatement ps=con.prepareStatement("delete from items where item_id=?");
            ps.setString(1, item_id);
            
            int i=ps.executeUpdate();
            if(i>0)
            {
                status=true;
            }
            else
            {
                status=false;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return status;
    }
    public static boolean updateItemWithImage(FileInputStream fis, String... str)
    {
        boolean status=false;
        try
        {
            Connection con=DbConnection.getConnection();
            
            PreparedStatement ps=con.prepareStatement("update items set item_name=?, item_price=?, item_desc=?, item_category=?, item_img=? where item_id=?");
            ps.setString(1, str[1]);
            ps.setString(2, str[2]);
            ps.setString(3, str[3]);
            ps.setString(4, str[4]);
            ps.setBinaryStream(5, fis);
            ps.setString(6, str[0]);
            
            int i=ps.executeUpdate();
            if(i>0)
            {
                status=true;
            }
            else
            {
                status=false;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return status;
    }
    public static boolean updateItemWithoutImage(String... str)
    {
        boolean status=false;
        try
        {
            Connection con=DbConnection.getConnection();
            
            PreparedStatement ps=con.prepareStatement("update items set item_name=?, item_price=?, item_desc=?, item_category=? where item_id=?");
            ps.setString(1, str[1]);
            ps.setString(2, str[2]);
            ps.setString(3, str[3]);
            ps.setString(4, str[4]);
            ps.setString(5, str[0]);
            
            int i=ps.executeUpdate();
            if(i>0)
            {
                status=true;
            }
            else
            {
                status=false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }
    
    public static boolean checkOldPassword(String old_pwd, String email)
    {
        boolean status=false;
        try
        {
            Connection con=DbConnection.getConnection();
            
            PreparedStatement ps=con.prepareStatement("select * from register where email=? and password=?");
            ps.setString(1, email);
            ps.setString(2, old_pwd);
            
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                status=true;
            }
            else
            {
                status=false;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        return status;
    }
    
    public static boolean updatePassword(String new_pwd, String email)
    {
        boolean status=false;
        try
        {
            Connection con=DbConnection.getConnection();
            
            PreparedStatement ps=con.prepareStatement("update register set password=? where email=?");
            ps.setString(1, new_pwd);
            ps.setString(2, email);
            
            int i=ps.executeUpdate();
            if(i>0)
            {
                status=true;
            }
            else
            {
                status=false;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return status;
    }
    
    public static boolean updateMyProfile(String name, String phno, String gender, String email)
    {
        boolean status=false;
        try
        {
            Connection con=DbConnection.getConnection();
            
            PreparedStatement ps=con.prepareStatement("update register set name=?, gender=?, phone_no=? where email=?");
            ps.setString(1, name);
            ps.setString(2, gender);
            ps.setString(3, phno);
            ps.setString(4, email);
            int i=ps.executeUpdate();
            if(i>0)
            {
                status=true;
            }
            else
            {
                status=false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }
    
    public static boolean insertCustomerDetails(String... str)
    {
        boolean status=false;
        
        String name1=str[0];
        String email1=str[1];
        String pass1=str[2];
        String gender1=str[3];
        String phno1=str[4];
        String module1=str[5];
        
        try
        {
            Connection con=DbConnection.getConnection();
            
            PreparedStatement ps=con.prepareStatement("insert into register values(?,?,?,?,?,?)");
            ps.setString(1, name1);
            ps.setString(2, email1);
            ps.setString(3, pass1);
            ps.setString(4, gender1);
            ps.setString(5, phno1);
            ps.setString(6, module1);
            
            int i=ps.executeUpdate();
            if(i>0)
            {
                status=true;
            }
            else
            {
                status=false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }
    
    public static ResultSet checkCustomerExists(String phno)
    {
        ResultSet rs = null;
        try
        {
            Connection con=DbConnection.getConnection();
        
            PreparedStatement ps=con.prepareStatement("select * from register where phone_no=?");
            ps.setString(1, phno);
            
            rs=ps.executeQuery();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }
    
    public static boolean customerBillingDetails(String customer_phno, String items, String date1, String time1, String emp_email)
    {
        boolean status=false;
        try
        {
            Connection con=dbcon.DbConnection.getConnection();
            
            PreparedStatement ps=con.prepareStatement("insert into customer_shopping_details values(?,?,?,?,?)");
            ps.setString(1, customer_phno);
            ps.setString(2, items);
            ps.setString(3, date1);
            ps.setString(4, time1);
            ps.setString(5, emp_email);
            int i=ps.executeUpdate();
            if(i>0)
            {
                status=true;
            }
            else
            {
                status=false;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return status;
    }
    
    public static ResultSet getCustomerShoppingDetails(String phno)
    {
        ResultSet rs=null;
        try
        {
            Connection con=DbConnection.getConnection();
            
            PreparedStatement ps=con.prepareStatement("select * from customer_shopping_details where customer_phno=?");
            ps.setString(1, phno);
            
            rs=ps.executeQuery();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }
}
