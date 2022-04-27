package service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import dto.LoginDto;
import manager.RequestManager;
import driverpackage.DriverManager;
import manager.RoleRedirector;
import static manager.RequestManager.Sc;

public class Service {

    public DriverManager driver;
    public static boolean LoginBoolean = true;
    public static boolean LogoutBoolean=false;
    public LoginDto login;
    private RoleRedirector roledir;

    public void getInput() throws InterruptedException, IOException, ParseException, SQLException, ClassNotFoundException {

        while(true){
        if (LoginBoolean) {

            driver = new DriverManager();
            RequestManager.isConsole = true;
            Sc = new Scanner(System.in);
            login = RequestManager.getInput();

            if(login.getRole()!=null) {
                driver.Login(login);
                LogoutBoolean=true;
            }
        }

        while(LogoutBoolean){
            roledir=new RoleRedirector();
            roledir.privilege(driver,login);
        }

        }

    }
}