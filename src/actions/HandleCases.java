package actions;

import manager.RequestManager;
import utility.UtilityClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HandleCases {

    public void Caseid(String adhaar_no) {

        try {
            PreparedStatement statement1 = UtilityClass.getInstance().prepareStatement("select case_id from cases where (person_lawyer=? or defendent_lawyer=? or judge=?) and status!='disclosed'");

            statement1.setString(1, adhaar_no);
            statement1.setString(2, adhaar_no);
            statement1.setString(3, adhaar_no);

            ResultSet rs = statement1.executeQuery();

            System.out.println("\t\t*****HANDLING CASE ID's*****\n");
            System.out.print("CaseId's : ");
            while(rs.next()){

                System.out.print(rs.getInt(1)+", ");

            }

            System.out.println("\n\t\t****************************\n");

        } catch (Exception e) {
            System.out.println(e);
        }

    }


    public void SendJudgeCheck(String caseid,String aadhaar_no){

        try {
             PreparedStatement statement1 = UtilityClass.getInstance().prepareStatement("select judge from cases where case_id =? and person_lawyer=?");

            statement1.setInt(1,Integer.parseInt(caseid));
            statement1.setString(2,aadhaar_no);

            ResultSet rs = statement1.executeQuery();

            if(rs.next()){

            if(rs.getString(1)==null){

                RequestManager.isjudge=true;

            }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }


    public void showCase(String caseid,String aadhaar_no){

        try {
             PreparedStatement statement1 = UtilityClass.getInstance().prepareStatement("select defendent_lawyer,case_description,status,judge,status from cases where case_id =? and status!='disclosed' and (person_lawyer=? or defendent_lawyer=? or judge=?)");

            statement1.setInt(1,Integer.parseInt(caseid));
            statement1.setString(2,aadhaar_no);
            statement1.setString(3,aadhaar_no);
            statement1.setString(4,aadhaar_no);

            ResultSet rs = statement1.executeQuery();

            if(rs.next()){

                System.out.println("\n\t\t*****CASEID : "+caseid+"*****");
                System.out.print("Defendent lawyer : ");
                if(rs.getString(1)==null || rs.getString(1).equals("")) {
                    System.out.println("no one.");

                }

                else{
                    PreparedStatement statement2=UtilityClass.getInstance().prepareStatement("select name from profile where adhaar=?");
                    statement2.setString(1,rs.getString(1));
                    ResultSet rs2=statement2.executeQuery();

                    rs2.next();
                    System.out.println(rs2.getString(1));
                }

                System.out.print("Judge           : ");
                if(rs.getString(4)==null || rs.getString(4).equals("")) {

                    System.out.println("no one.");
                }

                else{

                    PreparedStatement statement3=UtilityClass.getInstance().prepareStatement("select name from profile where adhaar=?");
                    statement3.setString(1,rs.getString(4));
                    ResultSet rs3=statement3.executeQuery();

                    rs3.next();
                    System.out.println(rs3.getString(1));
                }

                System.out.println("Case Descript : "+rs.getString(2));
                System.out.println("Status        : "+rs.getString(3));
                System.out.println("\t\t********************\n");

              RequestManager.isValidCaseId=true;

              if(rs.getString(5).equals("pending") || rs.getString(5).equals("ongoing")){

                  RequestManager.ispending=true;
              }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    public void changeStatus(String caseid,String status){

        try {
             PreparedStatement statement1 = UtilityClass.getInstance().prepareStatement("update cases set status=? where case_id=?");
            statement1.setString(1,status);
            statement1.setInt(2,Integer.parseInt(caseid));

            int q=statement1.executeUpdate();
            System.out.println("\n\tSuccessfully updated.");

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public boolean CheckCases(String caseId,String aadhaar_no) {

        boolean flag2=false;

        try {
            PreparedStatement statement1 = UtilityClass.getInstance().prepareStatement("select defendent_lawyer from cases where case_id=? and defendent=?");

            statement1.setInt(1,Integer.parseInt(caseId));
            statement1.setString(2,aadhaar_no);
            ResultSet rs=statement1.executeQuery();

            if(rs.next()){

           if(rs.getString(1)!=null){
             System.out.println("\nDefendent lawyer is alredy set.\n");
           }

           else{
               flag2=true;
           }

            }

            else {
                System.out.println("\nYou don't have permission to send it to lawyer.\n");
            }


        } catch (Exception e) {
            System.out.println(e);
        }

        return flag2;
    }
}

