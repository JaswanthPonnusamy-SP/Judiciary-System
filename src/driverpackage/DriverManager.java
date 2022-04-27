package driverpackage;

import dto.*;
import actions.managerClass;
import manager.RequestManager;
import utility.UtilityClass;

public class DriverManager {

    public managerClass mancl;

    public void Login(LoginDto login) throws InterruptedException {

        String username= login.getName();
        System.out.print("\n");
        UtilityClass.printCustomisable("Loading","...\b\b\b");
        if(username==null){
            System.out.println("\nHi admin\n");
        }
        else {
            System.out.println("\nHi " + username + "\n");
        }
        Thread.sleep(500);
    }


    public void CaseFiledUser(CaseDto user){

        mancl=new managerClass();
        String username=user.getUser();
        String case2=user.getCaseDescription();
        String lawyer=user.getLawyer();
        String defendent=user.getDefendent_name();
        System.out.println("\n\t\tYOUR SUBMITTED DETAILS\n");
        System.out.println("case filed by : "+username+"\nlawyer name : "+lawyer+"\ncase description : "+case2+"\ndefendent name : "+defendent+"\n");
        System.out.println("\t\t---CONFIRMATION(Yes\\No)---\n\n");
        String confrimYesOrNo= RequestManager.Sc.nextLine().toLowerCase();

        while(confrimYesOrNo.equals("") || !confrimYesOrNo.matches("(yes|no)")){
         System.out.println("enter the correctly.");
         confrimYesOrNo=RequestManager.Sc.nextLine().toLowerCase();
         }

        if(confrimYesOrNo.equals("yes")) {
            mancl.UserCaseInsertIntoDB(user);
            System.out.println("\t\t Successfully sended");
        }

        else if(confrimYesOrNo.equals("no")){
            System.out.println("\t\t     You Cancelled it.");
        }
    }


    public void ProfileShow(ProfileDto profilee){

        String Adhaar=profilee.getAdhaar_number();
        String name=profilee.getName();
        int age=profilee.getAge();
        String emailid=profilee.getEmail_Id();
        String contact_no=profilee.getContact_no();
        String address=profilee.getAddress();
        System.out.println("\n\t\t******YOUR PROFILE******");
        System.out.println("Name       : "+name+"\nAGE        : "+age+"\nE-MAIL     : "+emailid+"\nCONTACT NO  : "+contact_no+"\nADDRESS    : "+address+"\nAADHAAR NO : "+Adhaar);
        System.out.println("\t\t**********************\n");
    }


    public void insertIntoDatabaseNewProfile(CreateProfileDto createprofile){

        mancl=new managerClass();

        System.out.println("\t\t---CONFIRMATION(Yes\\No)---\n\n");

        String confrimYesOrNo= RequestManager.Sc.nextLine().toLowerCase();

        while(confrimYesOrNo.equals("") || !confrimYesOrNo.matches("(yes|no)")){
            System.out.println("enter the correctly.");
            confrimYesOrNo=RequestManager.Sc.nextLine().toLowerCase();
        }

        if(confrimYesOrNo.equals("yes")) {
            mancl.insertIntoTheDatabaseNewProfile(createprofile);
            System.out.println("\n\tsuccessfully created.");
        }

        else if(confrimYesOrNo.equals("no")){
            System.out.println("\t\t     You Cancelled it.\n");
        }
    }

    public void HearingShower(HearingDto hearing) {


        if (hearing.getTimestamp_1() != null) {
            System.out.println("\n\t\t******HEARINGS FOR CASEID : " + hearing.getCase_id() + " ******");

            System.out.println("Hearing 1 timestamp : " + hearing.getTimestamp_1());

            if (hearing.getHearing_1() != null) {
                System.out.println("Hearing 1            :" + hearing.getHearing_1());
            }

            if (hearing.getTimestamp_2() != null) {
                System.out.println("Hearing 2 timestamp : " + hearing.getTimestamp_2());
            }

            if (hearing.getHearing_2() != null) {
                System.out.println("Hearing 2            :" + hearing.getHearing_2());
            }

            if (hearing.getTimestamp_3() != null) {
                System.out.println("Hearing 3 timestamp : " + hearing.getTimestamp_3());
            }

            if (hearing.getHearing_3() != null) {
                System.out.println("Hearing 3           :" + hearing.getHearing_3());
            }

            System.out.println("\t\t*********************************\n");

        }

        else{
            System.out.println("\nNo hearings yet.\n");
        }
    }

}