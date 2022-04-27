package actions;

import dto.CaseDto;
import dto.CreateProfileDto;
import utility.UtilityClass;

import java.sql.*;

public class managerClass {

    public void UserCaseInsertIntoDB(CaseDto user){

        String Case_Description=user.getCaseDescription();
        String byWhom=user.getUser_adhaar();
        String lawyer_adhaar=user.getLawyer_adhaar();
        String onWhom_adhaar=user.getDefendent_adhaar();

        try{
             PreparedStatement statement1 = UtilityClass.getInstance().prepareStatement("insert into cases(person,person_lawyer,defendent,case_description,status)values(?,?,?,?,?)");

            statement1.setString(1, byWhom);
            statement1.setString(2,lawyer_adhaar);
            statement1.setString(3,onWhom_adhaar);
            statement1.setString(4,Case_Description);
            statement1.setString(5,"pending");

            int i=statement1.executeUpdate();

            PreparedStatement statement3= UtilityClass.getInstance().prepareStatement("select max(case_id) from cases");
            ResultSet rs3=statement3.executeQuery();
            rs3.next();

            PreparedStatement statement4= UtilityClass.getInstance().prepareStatement("insert into hearings(case_id) values(?)");
            statement4.setString(1,rs3.getString(1));

            int z=statement4.executeUpdate();


            System.out.println("\n\tsaved successfully, check results.\n");

        }catch(Exception e){ System.out.println(e);}

    }


    public void insertIntoTheDatabaseNewProfile(CreateProfileDto createprofile){

        try{
            PreparedStatement statement1 = UtilityClass.getInstance().prepareStatement("insert into login(adhaar,role)values(?,?)");

            statement1.setString(1, createprofile.getAadhaar());
            statement1.setString(2,createprofile.getRole());


            int i=statement1.executeUpdate();

            PreparedStatement statement2 = UtilityClass.getInstance().prepareStatement("insert into profile(adhaar,name,age,contact_no,email_id,address,Role)values(?,?,?,?,?,?,?)");

            statement2.setString(1, createprofile.getAadhaar());
            statement2.setString(2,createprofile.getName());
            statement2.setInt(3,createprofile.getAge());
            statement2.setString(4,createprofile.getContact_no());
            statement2.setString(5,createprofile.getEmail_id());
            statement2.setString(6,createprofile.getAddress());
            statement2.setString(7,createprofile.getRole());

            int j=statement2.executeUpdate();

            System.out.println("\nsaved successfully, check results.\n");

        }catch(Exception e){ System.out.println(e);}


    }


    public void UserCaseInsertJudge(String judge,String case_id,String aadhaar_no){


        try{
            PreparedStatement statement1 = UtilityClass.getInstance().prepareStatement("update cases set judge=? where case_id=? and person_lawyer=?");

            statement1.setString(1,judge);
            statement1.setString(2,case_id);
            statement1.setString(3,aadhaar_no);

            int i=statement1.executeUpdate();

            System.out.println("\nsaved successfully, check results.\n");

        }catch(Exception e){ System.out.println("invalid handling caseId.");}

    }

    public void RoleChanger(String role,String aadhaar_no) throws ClassNotFoundException, SQLException {
        PreparedStatement statement1 = UtilityClass.getInstance().prepareStatement("update login set role=? where adhaar=?");
        statement1.setString(1,role);
        statement1.setString(2,aadhaar_no);
        statement1.executeUpdate();

        PreparedStatement statement2 = UtilityClass.getInstance().prepareStatement("update profile set Role=? where adhaar=?");
        statement2.setString(1,role);
        statement2.setString(2,aadhaar_no);
        statement2.executeUpdate();

        System.out.println("\nSuccessfully changed.\n");

    }

    public void DefendentLawyer(CaseDto case1,String CaseId) throws SQLException, ClassNotFoundException {

        PreparedStatement statement1 = UtilityClass.getInstance().prepareStatement("update cases set defendent_lawyer=? where case_id=?");
        statement1.setString(1,case1.getDefendentlawyer_Adhaar());
        statement1.setString(2,CaseId);

        int z=statement1.executeUpdate();

        System.out.println("\nScuccessfully updated.\n");
    }
}

