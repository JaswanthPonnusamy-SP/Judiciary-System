package actions;

import dto.CaseDto;
import utility.UtilityClass;

import java.sql.*;

public class UserChecker {

    public String user_aadhaar=null;
    public String user_nameCheck="";

    public String UserCheck(CaseDto case1,String aadhaar_no,String sql,String Role,String allow) throws ClassNotFoundException, SQLException {

        if(Role.equals("person")){
            user_nameCheck=case1.getDefendent_name();
        }else if(Role.equals("lawyer")){
            user_nameCheck=case1.getLawyer();
        }
            PreparedStatement statement1 = UtilityClass.getInstance().prepareStatement("select adhaar from profile where name=? "+sql+"and "+allow);

            statement1.setString(1, user_nameCheck);
            statement1.setString(2,aadhaar_no);
            if(case1.getDefendent_adhaar()!=null) {
                statement1.setString(3, case1.getDefendent_adhaar());
            }
            ResultSet rs1 = statement1.executeQuery();
            if (rs1.next()){
                if(Role.equals("person")) {
                    user_aadhaar=rs1.getString(1);
                    case1.setDefendent_adhaar(rs1.getString(1));
                }

                else if(Role.equals("lawyer")){
                    user_aadhaar=rs1.getString(1);
                    case1.setLawyer_adhaar(rs1.getString(1));
                }
            }

            else {
                System.out.println("\n\t\t******FOUND RESULTS******");
                PreparedStatement statement2 = UtilityClass.getInstance().prepareStatement("select name from profile where name like ? "+sql+"and "+allow);

                statement2.setString(1, user_nameCheck+"%");
                statement2.setString(2,aadhaar_no);
                if(case1.getDefendent_adhaar()!=null) {
                    statement2.setString(3, case1.getDefendent_adhaar());
                }

                ResultSet rs2 = statement2.executeQuery();


                while (rs2.next()) {
                    System.out.println(rs2.getString(1));
                }
                System.out.println("\t\t*************************\n");
            }

        return user_aadhaar;
    }


    public String JudgeLawyerCheck(CaseDto case1,String aadhaar_no,String sql,String Role,String allow,String caseid) throws ClassNotFoundException, SQLException {

        if(Role.equals("lawyer")){
            user_nameCheck=case1.getDefendentLawyer_name();
        }else if(Role.equals("judge")){
            user_nameCheck=case1.getJudge();
        }

        PreparedStatement statement5=UtilityClass.getInstance().prepareStatement("select person,person_lawyer,defendent from cases where case_id=?");
        statement5.setInt(1,Integer.parseInt(caseid));
        ResultSet rs5 = statement5.executeQuery();
        rs5.next();
        PreparedStatement statement1 = UtilityClass.getInstance().prepareStatement("select adhaar from profile where name=? "+sql+"and "+allow);

        statement1.setString(1, user_nameCheck);

        if(Role.equals("lawyer")){
            statement1.setString(2,rs5.getString(1));
            statement1.setString(3,rs5.getString(2));
            statement1.setString(4,rs5.getString(3));
        }
        else if(Role.equals("judge")){
            statement1.setString(2,rs5.getString(1));
            statement1.setString(3,rs5.getString(3));
        }

        ResultSet rs1 = statement1.executeQuery();

        if (rs1.next()){

            if(Role.equals("lawyer")){
                user_aadhaar=rs1.getString(1);
                case1.setDefendentlawyer_Adhaar(rs1.getString(1));
            }

            else if(Role.equals("judge")){
                user_aadhaar=rs1.getString(1);
                case1.setJudge_adhaar(rs1.getString(1));
            }

        }

        else {
            System.out.println("\n\t\t******FOUND RESULTS******");
            PreparedStatement statement2 = UtilityClass.getInstance().prepareStatement("select name from profile where name like ? "+sql+"and "+allow);

            statement2.setString(1, user_nameCheck+"%");
            if(Role.equals("lawyer")){
                statement2.setString(2,rs5.getString(1));
                statement2.setString(3,rs5.getString(2));
                statement2.setString(4,rs5.getString(3));
            }
            else if(Role.equals("judge")){
                statement2.setString(2,rs5.getString(1));
                statement2.setString(3,rs5.getString(3));
            }
            if(case1.getDefendent_adhaar()!=null) {
                statement2.setString(3, case1.getDefendent_adhaar());
            }

            ResultSet rs2 = statement2.executeQuery();


            while (rs2.next()) {
                System.out.println(rs2.getString(1));
            }
            System.out.println("\t\t*************************\n");
        }
        return user_aadhaar;
    }
}