package manager;

import actions.HandleCases;
import actions.LoginFunction;
import actions.managerClass;
import driverpackage.DriverManager;
import dto.LoginDto;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import static manager.RequestManager.Sc;

public class RoleRedirector {

    private RequestManager req1;
    private CaseResults caseres;

    public void privilege(DriverManager driver, LoginDto login) throws ParseException, SQLException, ClassNotFoundException {

        caseres=new CaseResults();
        req1 = new RequestManager();

        if (login.getRole().equals("person")) {

            Sc = new Scanner(System.in);
            System.out.println("1.File a case[file].\uD83D\uDE00");
            System.out.println("2.Profile[profile].");
            System.out.println("3.Results[results].");
            System.out.println("4.Set Defendent Lawyer[setdefendent].");
            System.out.println("5.log out[logout].");
            String choice = Sc.nextLine();
            while(choice.equals("") || !choice.matches("(file|profile|results|logout|handlecases|setdefendent)")){
                System.out.println("please enter something correct.");
                choice = Sc.nextLine();
            }

            switch (choice) {
                case "file":
                    driver.CaseFiledUser(req1.Casechecker(login.getAdhaar()));
                    break;

                case "profile":
                    driver.ProfileShow(req1.ProfileChecker(login.getAdhaar()));
                    break;

                case "results":
                    caseres.caseResults(login.getAdhaar());
                    break;

                case "setdefendent":
                    HandleCases han=new HandleCases();
                    managerClass mc=new managerClass();
                    System.out.println("Enter the CaseId : ");
                    String CaseId=Sc.nextLine();
                    while(CaseId.equals("") || !CaseId.matches("[0-9]{1,3}")){
                        System.out.println("enter the correct caseId.");
                        CaseId=Sc.nextLine();
                    }

                    if(han.CheckCases(CaseId,login.getAdhaar())){
                        mc.DefendentLawyer(req1.DefendentLawyerCasechecker(login.getAdhaar(),CaseId),CaseId);
                    }
                    break;

                case "logout":
                    req1.Logout();
                    break;

            }

        }
            else if (login.getRole().equals("lawyer")  || login.getRole().equals("judge")) {

            Sc = new Scanner(System.in);
            System.out.println("1.File a case[file].\uD83D\uDE00");
            System.out.println("2.Profile[profile].");
            System.out.println("3.Results[results].");
            System.out.println("4.log out[logout].");
            System.out.println("5.Set Defendent Lawyer[setdefendent].");
            System.out.println("6.Handle your case[handlecases].");
            String choice = Sc.nextLine();
            while(choice.equals("") || !choice.matches("(file|profile|results|logout|handlecases|setdefendent)")){
                System.out.println("please enter something.");
                choice = Sc.nextLine();
            }
            req1 = new RequestManager();

            switch (choice) {
                case "file":
                    driver.CaseFiledUser(req1.Casechecker(login.getAdhaar()));
                    break;

                case "profile":
                    driver.ProfileShow(req1.ProfileChecker(login.getAdhaar()));
                    break;

                case "results":
                    caseres.caseResults(login.getAdhaar());
                    break;

                case "setdefendent":
                    HandleCases han=new HandleCases();
                    managerClass mc=new managerClass();
                    System.out.println("Enter the CaseId : ");
                    String CaseId=Sc.nextLine();
                    while(CaseId.equals("") || !CaseId.matches("[0-9]{1,3}")){
                        System.out.println("enter the correct caseId.");
                        CaseId=Sc.nextLine();
                    }

                    if(han.CheckCases(CaseId,login.getAdhaar())){
                        mc.DefendentLawyer(req1.DefendentLawyerCasechecker(login.getAdhaar(),CaseId),CaseId);
                    }
                    break;

                case "logout":
                    req1.Logout();
                    break;

                case "handlecases":
                    req1.NoOfCases(login.getAdhaar(),login.getRole());
                    break;

               }
        }
            else if (login.getRole().equals("admin")) {

                Sc = new Scanner(System.in);
                System.out.println("1.Create a User [create].\uD83D\uDE00");
                System.out.println("2.Change Role [changerole].");
                System.out.println("3.log out[logout].");
                String choice = Sc.nextLine();
            while(choice.equals("") || !choice.matches("(create|changerole|logout)")){
                System.out.println("please enter something.");
                choice = Sc.nextLine();
            }

                switch (choice) {
                    case "create":
                        driver.insertIntoDatabaseNewProfile(req1.createProfile());
                        break;

                    case "changerole":
                        req1.changeRole();
                        break;

                    case "logout":
                        req1.Logout();
                        break;

                }
            }
        }

}