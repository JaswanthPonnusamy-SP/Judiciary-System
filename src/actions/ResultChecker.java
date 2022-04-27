package actions;

import utility.UtilityClass;

import java.sql.*;

public class ResultChecker {

    private static String Filed_By = "";
    private static String Person_Lawyer = "";
    private static String defendent = "";
    private static String defendent_Lawyer = "";
    private static String Case_Description="";
    private static String Status="";
    private static int case_id=0;
    private static String Judge="";

    public void CaseResultChecker(String adhaar_no,String who) throws ClassNotFoundException, SQLException {

            PreparedStatement statement1 = UtilityClass.getInstance().prepareStatement("select * from cases where "+who+"=?");
            statement1.setString(1, adhaar_no);
            ResultSet rs = statement1.executeQuery();
            CaseResults(rs,UtilityClass.getInstance());
    }


    public void CaseResults(ResultSet rs,Connection con) throws SQLException {

    int i=1;
    while (rs.next()) {
        System.out.println("\n\t\t*****Case "+i+"*****");
        case_id = rs.getInt(1);
        if (rs.getString(3) !=null && rs.getString(4) != null && rs.getString(5) != null) {
            int o=0;
            while(o<4) {
                PreparedStatement statement2 = con.prepareStatement("select name from profile where adhaar=?");
                statement2.setString(1, rs.getString(o+2));

                ResultSet rs1 = statement2.executeQuery();

                if (rs1.next()) {
                    if(o==0) {
                        Filed_By = rs1.getString(1);

                    }
                    else if (o == 1) {
                        Person_Lawyer = rs1.getString(1);
                    }

                    else if(o==2) {
                        defendent = rs1.getString(1);
                    }

                    else if(o==3) {
                        defendent_Lawyer = rs1.getString(1);
                    }
                }
                o=o+1;
            }

        } else if (rs.getString(5) == null) {

            int p=0;
            while(p<3) {
                PreparedStatement statement2 = con.prepareStatement("select name from profile where adhaar=?");
                statement2.setString(1, rs.getString(p+2));
                ResultSet rs1 = statement2.executeQuery();
                if (rs1.next()) {

                    if(p==0) {
                        Filed_By = rs1.getString(1);
                    }

                    else if(p==1) {
                        Person_Lawyer = rs1.getString(1);
                    }

                    else if(p==2) {
                        defendent = rs1.getString(1);
                    }
                }
                p=p+1;
            }
        }

        if(rs.getString(8)!=null){
            PreparedStatement statement5 = con.prepareStatement("select name from profile where adhaar=?");
            statement5.setString(1,rs.getString(8));
            ResultSet rs5=statement5.executeQuery();
            if (rs5.next()){
                Judge=rs5.getString(1);
            }
        }

        Case_Description = rs.getString(6);
        Status = rs.getString(7);
        printStatement();
        i = i + 1;
    }

            if(Status.equals("")){
        System.out.println("\nNo cases found\n");
    }

            Status="";
}

    public void case_idChecker(String casee_id,String aadhaar_no,String statusOrCaseId) throws ClassNotFoundException, SQLException {

            PreparedStatement statement1 = UtilityClass.getInstance().prepareStatement("select * from cases where "+statusOrCaseId+"=? and (person=? or person_lawyer=? or defendent=? or defendent_lawyer=? or judge=?)");

            if(casee_id.matches("[0-9]{1,3}")){
                statement1.setInt(1, Integer.parseInt(casee_id));
            }
            else{
                statement1.setString(1, casee_id);
            }
            statement1.setString(2,aadhaar_no);
            statement1.setString(3,aadhaar_no);
            statement1.setString(4,aadhaar_no);
            statement1.setString(5,aadhaar_no);
            statement1.setString(6,aadhaar_no);
            ResultSet rs = statement1.executeQuery();
            CaseResults(rs,UtilityClass.getInstance());
    }


    public static void printStatement(){

        System.out.println("case id          : " + case_id);
        System.out.println("Filed By         : " + Filed_By);
        System.out.println("Plaintiff lawyer : " + Person_Lawyer);
        System.out.println("defendent        : " + defendent);
        if(!defendent_Lawyer.equals("")) {
            System.out.println("defendent lawyer : " + defendent_Lawyer);
        }
        if(!Judge.equals("")) {
            System.out.println("Judge            : " + Judge);
        }
        System.out.println("Case Description : " + Case_Description);
        System.out.println("status           : " + Status);
        System.out.println("\t\t**************\n");

        case_id=0;Filed_By="";Person_Lawyer="";defendent="";defendent_Lawyer="";Case_Description="";Judge="";
    }
}