package actions;

import driverpackage.DriverManager;
import dto.LoginDto;
import manager.RequestManager;
import service.Service;
import utility.UtilityClass;

import java.sql.*;

public class LoginFunction {

    public DriverManager dr;

    public LoginDto Login_check(LoginDto login){

        dr=new DriverManager();

        String adhaar=login.getAdhaar();
        String pass=login.getPassword();

        try{
            PreparedStatement statement = UtilityClass.getInstance().prepareStatement("SELECT * FROM login WHERE adhaar=? AND password=?");

            statement.setString(1, adhaar);
            statement.setString(2,pass);

            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                    login.setRole(rs.getString(3));
                    Service.LoginBoolean=false;
            }
             PreparedStatement statement1=UtilityClass.getInstance().prepareStatement("select name from profile where adhaar=?");

              statement1.setString(1,adhaar);

              ResultSet rs1 = statement1.executeQuery();

              rs1.next();

              login.setName(rs1.getString(1));

        }catch(Exception e){ System.out.print("");}

        return login;

    }


    public boolean Aadhaar_check(String aadhaar){

        boolean isAadhaar=false;

        try{
             PreparedStatement statement = UtilityClass.getInstance().prepareStatement("SELECT password FROM login WHERE adhaar=?");

            statement.setString(1, aadhaar);

            ResultSet rs = statement.executeQuery();

            if(rs.next()){

               isAadhaar=true;
               String passCheck=rs.getString(1);

               if(passCheck==null || passCheck.equals("")){

                   RequestManager.ispassword=true;

               }
            }

        }catch(Exception e){ System.out.print("");}

        return isAadhaar;

    }


    public static void password(String aadhaar_no,String password){

        try{
             PreparedStatement statement1 = UtilityClass.getInstance().prepareStatement("UPDATE login SET password=? WHERE adhaar=?");


            statement1.setString(1,password);
            statement1.setString(2, aadhaar_no);

            int i = statement1.executeUpdate();
            System.out.println("Successfully done.Kindly login again.");
            RequestManager.isvalidaadhaar=false;

        }catch(Exception e){ System.out.print("");
        }
    }
}