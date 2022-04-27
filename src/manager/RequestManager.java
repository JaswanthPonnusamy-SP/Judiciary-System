package manager;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import actions.*;
import dto.*;
import service.Service;
import utility.DateTest;
import utility.NotificationMessage;
import driverpackage.DriverManager;

public class RequestManager {

    public static boolean isConsole;
    public static Scanner Sc;
    private static LoginDto login;
    private CaseDto case1;
    public ProfileDto profilee;
    private static RequestManager req;
    private ProfileUser pu;
    public static boolean isvalidaadhaar;
    public static boolean ispassword=false;
    public static boolean isjudge=false;
    public static boolean isValidCaseId=false;
    public static boolean ispending=false;


    public static LoginDto getInput() throws IOException, InterruptedException {

        if(isConsole){
            req=new RequestManager();
            login = req.LoginCredentials();
            LoginFunction lf = new LoginFunction();
            login = lf.Login_check(login);

            if(login.getRole()==null){
                if(!ispassword) {
                    System.out.println("\ninvalid username or password.\n");
                }

                else{
                    ispassword=false;
                }

                while(isvalidaadhaar){

                    System.out.println("forgotten password.[yes/no]");
                    String yesOrNo=Sc.nextLine();
                    if(yesOrNo.equals("yes")){
                      OtpMessage("otp1",login);
                    }

                    else if(yesOrNo.equals("no")){
                        isvalidaadhaar=false;
                    }

                    else{
                        System.out.println("please enter yes or no");
                    }
                }
                 }
            }
        return login;
    }

    private LoginDto LoginCredentials() throws IOException, InterruptedException {

            Thread.sleep(100);
        System.out.println("\nENTER THE AADHAAR NO[12]: ");
        String adhaar_no1 = Sc.nextLine();
        while(!adhaar_no1.matches("[0-9]{12}") || adhaar_no1.equals("")) {
            System.out.println("please, enter something correctly.");
                adhaar_no1 = Sc.nextLine();
        }
            Thread.sleep(100);
        LoginFunction lf=new LoginFunction();
        isvalidaadhaar=lf.Aadhaar_check(adhaar_no1);
        login = new LoginDto();
        login.setAdhaar(adhaar_no1);
        if(ispassword){

            System.out.println("Newly Created Profile.\nPlease enter to create password : ");
            OtpMessage("otp2",login);
        }
        else {
            System.out.println("\nENTER THE PASSWORD   : ");
            String password = Sc.nextLine();
            while (!password.matches("^[\\s\\S\\d]{8,30}$") || password.equals("")) {
                System.out.println("invalid credentials.");
                password = Sc.nextLine();
            }
            login.setPassword(password);
        }
        return login;
    }


    public CaseDto Casechecker(String adhaar) throws SQLException, ClassNotFoundException {
        case1 = new CaseDto();

        case1.setUser_adhaar(adhaar);
        case1.setUser("You");

        String adhaar1;                                             //check user
        do {
            adhaar1 = null;
            UserChecker us = new UserChecker();
            adhaar1 = us.UserCheck(caseOnWhom(case1),adhaar,"","person"," adhaar!=?");
        } while (adhaar1 == null);

        String adhaar2;                                             //check lawyer
        do {
            adhaar2 = null;
            UserChecker us = new UserChecker();
            adhaar2 = us.UserCheck(caseToLawyer(case1),adhaar,"and Role='lawyer' ","lawyer","adhaar not in (?,?)");
        } while (adhaar2 == null);

        System.out.println("\nCASE DESCRIPTION            : ");
        String casedescription = Sc.nextLine().trim();
        while(casedescription.equals("")){
            casedescription=Sc.nextLine().trim();
        }
        case1.setCaseDescription(casedescription);               //setting case description

        return case1;
    }


    public CaseDto DefendentLawyerCasechecker(String aadhaar,String CaseId) throws SQLException, ClassNotFoundException {
        case1 = new CaseDto();

        String adhaar2;                                             //check Defendent lawyer
        do {
            adhaar2 = null;
            UserChecker us = new UserChecker();
            adhaar2 = us.JudgeLawyerCheck(caseToDefLawyer(case1),aadhaar,"and Role='lawyer' ","lawyer","adhaar not in (?,?,?)",CaseId);
        } while (adhaar2 == null);

        return case1;
    }


    public CaseDto LawyerCasechecker(String aadhaar,String caseId) throws SQLException, ClassNotFoundException {
        case1 = new CaseDto();

        String adhaar2;                                             //check judge
        do {
            adhaar2 = null;
            UserChecker us = new UserChecker();
            adhaar2 = us.JudgeLawyerCheck(caseToJudge(case1),aadhaar,"and Role='judge' ","judge","adhaar not in (?,?)",caseId);
        } while (adhaar2 == null);

        return case1;
    }



    public ProfileDto ProfileChecker(String aadhaar_no) {
        pu = new ProfileUser();
        profilee=pu.setupTheProfile(aadhaar_no);

        return profilee;
    }


    private CaseDto caseOnWhom(CaseDto case1){

        System.out.println("\nCASE ON WHOM (you can search by entering values) : ");
        String user_name=Sc.nextLine();
        while(user_name.equals("")){
            System.out.println("please enter something.");
            user_name=Sc.nextLine();
        }
        case1.setDefendent_name(user_name);

        return case1;
    }


    private CaseDto caseToLawyer(CaseDto case1){

        System.out.println("\nTO WHICH LAWYER (you can search by entering values) : ");
        String lawyer_name=Sc.nextLine();
        while(lawyer_name.equals("")){
            System.out.println("please enter something.");
            lawyer_name=Sc.nextLine();
        }
        case1.setLawyer(lawyer_name);

        return case1;
    }

    private CaseDto caseToDefLawyer(CaseDto case1){

        System.out.println("\nTO WHICH LAWYER (you can search by entering values) : ");
        String lawyer_name=Sc.nextLine();
        while(lawyer_name.equals("")){
            System.out.println("please enter something.");
            lawyer_name=Sc.nextLine();
        }
        case1.setDefendentLawyer_name(lawyer_name);

        return case1;
    }


    private CaseDto caseToJudge(CaseDto case1){

        System.out.println("\nTO WHICH JUDGE (you can search by entering values) : ");
        String judge_name=Sc.nextLine();
        while(judge_name.equals("")){
            System.out.println("please enter something.");
            judge_name=Sc.nextLine();
        }
        case1.setJudge(judge_name);

        return case1;
    }


    public CreateProfileDto createProfile(){

        CreateProfileDto createprofile=new CreateProfileDto();
        System.out.println("Aadhaar number [12]: ");
        String aadhaar = Sc.nextLine();

        while (aadhaar.equals("") || !aadhaar.matches("[0-9]{12}")) {
            System.out.println("\ninvalid enter again.\n");
            aadhaar = Sc.nextLine();

        }
        System.out.println("\nUsername [Only caps,small characters,\".\"]      : ");
        String username = Sc.nextLine();

        while (username.equals("") || !username.matches("[a-zA-Z.]{6,100}")) {
            System.out.println("please enter something correctly");
            username = Sc.nextLine();
        }

        System.out.println("Age : ");
        String age = Sc.nextLine();

        while (age.equals("") || !age.matches("[0-9]{1,3}")) {
            System.out.println("please enter something correctly");
            age = Sc.nextLine();
        }
        int ageint = Integer.parseInt(age);

        System.out.println("Contact Number : ");
        String contact_number = Sc.nextLine();

        while (contact_number.equals("") || !contact_number.matches("[0-9]{10}")) {
            System.out.println("please enter something correctly");
            contact_number = Sc.nextLine();
        }

        System.out.println("Zoho-mail Id [@ not allowed, 2+ space]: ");
        String email_id = Sc.nextLine();

        while (email_id.equals("") || !email_id.matches("^(?!(.*\\s){1,})[!#$%& '*.\\/=?^_+\\-`{|}~a-zA-Z0-9]{6,30}[@](zoho|zohocorp)[.]com$")) {
            System.out.println("please enter something correctly");
            email_id = Sc.nextLine();

        }
        System.out.println("Address        : ");
        String address = Sc.nextLine().trim();

        while (address.equals("") || !address.matches("[\\s\\S\\d]{10,300}")) {
            System.out.println("please enter anything");
            address = Sc.nextLine();
        }

        System.out.println("Role [person/lawyer/judge] : ");
        String role = Sc.nextLine();

        while (role.equals("") || !role.matches("(person|lawyer|judge)")) {
            System.out.println("please enter correctly.");
            role = Sc.nextLine();

        }
        createprofile.setAadhaar(aadhaar);
        createprofile.setAge(ageint);
        createprofile.setName(username);
        createprofile.setContact_no(contact_number);
        createprofile.setEmail_id(email_id);
        createprofile.setAddress(address);
        createprofile.setRole(role);

        return createprofile;
    }

    public void Logout(){

     Service.LoginBoolean=true;
     Service.LogoutBoolean=false;
     System.out.println("\n\t\t\t***************BYE***************\n\n");
        System.out.println("\n\t\t******LOGIN TO YOUR ACCOUNT******\n");
    }

    public void NoOfCases(String aadhaar,String Role) throws ParseException, SQLException, ClassNotFoundException {

        managerClass man = new managerClass();
        RequestManager reqm = new RequestManager();
        HandleCases han = new HandleCases();
        han.Caseid(aadhaar);
        System.out.println("Enter the CaseId : ");
        String a = Sc.nextLine();

        while (a.equals("") || !a.matches("[0-9]{1,3}")) {
            System.out.println("please enter correctly.");
            a = Sc.nextLine();
        }
        han.showCase(a, aadhaar);

        if (isValidCaseId) {
            if (Role.equals("lawyer")) {
                System.out.println("\nEnter the required field : ");
                System.out.println("->Send to Judge[send].\n->Back");
                String b = Sc.nextLine();
                while (b.equals("") || !b.matches("(send|back)")) {
                    System.out.println("please enter something correctly.");
                    b = Sc.nextLine();
                }

                switch (b) {
                    case "send":
                        han.SendJudgeCheck(a, aadhaar);
                        if (isjudge) {
                            case1 = reqm.LawyerCasechecker(aadhaar,a);

                            System.out.println("\t\t*****Confirmatiom[Yes\\No]*****");
                            String confirmation = Sc.nextLine().toLowerCase();
                            while (confirmation.equals("") || !confirmation.matches("(yes|no)")) {
                                System.out.println("please enter something correctly.");
                                confirmation = Sc.nextLine().toLowerCase();
                            }
                            if (confirmation.equals("yes")) {
                                man.UserCaseInsertJudge(case1.getJudge_adhaar(), a, aadhaar);
                            }
                            else if (confirmation.equals("no")) {
                                System.out.println("\nyou denied it.\n");
                            }
                            isjudge = false;

                        } else {
                            System.out.println("\nIt is already sent to the judge.\n");
                        }
                        break;

                    case "back":
                        break;

                }
            }
            else if (Role.equals("judge")) {
                System.out.println("\nEnter the required Field : ");
                System.out.println("-->Edit the case[edit].\n-->Back[back].");

                String b = Sc.nextLine();
                while (b.equals("") || !b.matches("(edit|back)")) {
                    System.out.println("please enter something correctly.");
                    b = Sc.nextLine();
                }

                switch(b){

                    case "edit":
                        System.out.println("\nEdit status[status] (or) add hearing[addhearing]");

                        String ans=Sc.nextLine();

                        while(ans.equals("") || !ans.matches("(status|addhearing)")){
                            System.out.println("Please enter something correctly.");
                            ans=Sc.nextLine();
                        }

                        if(ispending && ans.equals("status[ongoing/disclosed] :")){
                            System.out.println("\nEnter the status to be changed : ");
                            String status=Sc.nextLine();

                            while(status.equals("") || !status.matches("(ongoing|disclosed)")){
                                System.out.println("Please enter something correctly.");
                                status=Sc.nextLine();
                            }
                            han.changeStatus(a,status);
                        }

                        else if(ans.equals("addhearing")){

                            System.out.println("\nHearing time[hearingtime] (or) Hearing desccription[hearingdescrip]");
                            String hearing=Sc.nextLine();
                            HearingsShower hear=new HearingsShower();
                            while(hearing.equals("") || !hearing.matches("(hearingtime|hearingdescrip)")){
                                System.out.println("enter correctly.");
                                hearing=Sc.nextLine();
                            }
                            if(hearing.equals("hearingtime")) {
                                int v=hear.Hearings(a,"hearing_1ts,hearing_1,hearing_2ts,hearing_2,hearing_3ts,hearing_3","time");
                                if(v==0){

                                    System.out.println("How many days from now : ");
                                    String set=Sc.nextLine();
                                    while(set.equals("") || !set.matches("[0-9]{1,2}")){
                                        System.out.println("enter correctly.");
                                        set=Sc.nextLine();
                                    }
                                hear.updateHearing(a,"hearing_1ts", DateTest.hearingCase(set));
                                }

                                else if(v==1){
                                    System.out.println("How many days from now[enter below 100 days]: ");
                                    String set=Sc.nextLine();
                                    while(set.equals("") || !set.matches("[0-9]{1,2}")){
                                        System.out.println("enter correctly.");
                                        set=Sc.nextLine();
                                    }
                                    hear.updateHearing(a,"hearing_2ts", DateTest.hearingCase(set));
                                }

                                else if(v==2){
                                    System.out.println("How many days from now : ");
                                    String set=Sc.nextLine();
                                    while(set.equals("") || !set.matches("[0-9]{1,2}")){
                                        System.out.println("less than 100 days enter correctly.");
                                        set=Sc.nextLine();
                                    }
                                    hear.updateHearing(a,"hearing_3ts", DateTest.hearingCase(set));
                                }

                                else if(v==4){
                                    System.out.println("\nPlease enter the previous hearing description.\n");
                                }
                            }

                            else if(hearing.equals("hearingdescrip")){

                                int w=hear.Hearings(a,"hearing_1ts,hearing_1,hearing_2ts,hearing_2,hearing_3ts,hearing_3","descrip");

                                if(w==0){
                                    System.out.println("Hearing description: ");
                                    String set=Sc.nextLine();
                                    while(set.equals("")){
                                        set=Sc.nextLine();
                                    }
                                    hear.updateHearing(a,"hearing_1", set);
                                }

                                else if(w==1){
                                    System.out.println("Hearing description: : ");
                                    String set=Sc.nextLine();
                                    while(set.equals("")){
                                        set=Sc.nextLine();
                                    }
                                    hear.updateHearing(a,"hearing_2", set);
                                }

                                else if(w==2){
                                    System.out.println("Hearing description: : ");
                                    String set=Sc.nextLine();
                                    while(set.equals("")){
                                        set=Sc.nextLine();
                                    }
                                    hear.updateHearing(a,"hearing_3", set);
                                }

                                else if(w==3){
                                    System.out.println("\nHearing finished.\n");
                                }

                                else if(w==4) {
                                    System.out.println("\nPlease enter the previous hearing time.\n");
                                }
                            }

                            else{
                                System.out.println("please enter correctly.");
                            }
                        }

                        else{
                            System.out.println("\nplease enter correctly.\n");
                        }
                        break;

                    case "back":
                        break;
                }
            }
            ispending=false;

         isValidCaseId=false;
        }
        else{
            System.out.println("\nIt's not your handling caseId.\n");
        }
    }

    public void changeRole() throws SQLException, ClassNotFoundException {
        System.out.println("Enter the AadhaarId : ");
        String aadhaar=Sc.nextLine();
        while(aadhaar.equals("") || !aadhaar.matches("[0-9]{12}")){
            System.out.println("please enter correctly.");
            aadhaar=Sc.nextLine();
        }
        LoginFunction lf=new LoginFunction();
        if(lf.Aadhaar_check(aadhaar)){
            managerClass man=new managerClass();
            System.out.println("Enter the Role : ");
            String Role=Sc.nextLine();
            while(Role.equals("") || !Role.matches("(person|lawyer|judge)"))
            man.RoleChanger(Role,aadhaar);
        }
    }

    public static void OtpMessage(String otp,LoginDto login) throws IOException {

        ArrayList<String> array =NotificationMessage.notificationOTPMessage();
        if(array.get(1).equals("204")){
            System.out.println("enter the OTP : ");
            String ch=Sc.nextLine();
            while(ch.equals("")){
                ch=Sc.nextLine();
            }
            if(ch.equals(array.get(0))){
                System.out.println("Please enter the new-password : ");
                String password=Sc.nextLine();
                while(password.equals("")){
                    password=Sc.nextLine();
                }
                LoginFunction.password(login.getAdhaar(),password);
            }
            else {
                if(otp.equals("otp1")) {
                    System.out.println("sorry, wrong OTP.");
                    isvalidaadhaar = false;
                }
                else if(otp.equals("opt2")){
                    System.out.println("sorry, wrong OTP.Try again.");
                }
            }
        }
        else{
            System.out.println("sorry not send.");
        }
    }

    public void HearingShower(String aadhaar_no) {
        HearingsShower hear=new HearingsShower();
        HearingDto hearing=new HearingDto();
        System.out.println("\nEnter the CaseId : ");
        String CaseId = Sc.nextLine();
        while (CaseId.equals("") || !CaseId.matches("^[0-9]{1,3}$")) {
            System.out.println("Please, enter correctly");
            CaseId = Sc.nextLine();
        }

        if(hear.noOfHearings(CaseId, aadhaar_no,hearing)){
            DriverManager printhearing=new DriverManager();
            printhearing.HearingShower(hearing);
        }

        else{
            System.out.println("\nthis is not your caseId.\n");
        }
    }
}
