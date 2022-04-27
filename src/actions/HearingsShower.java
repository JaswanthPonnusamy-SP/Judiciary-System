package actions;

import dto.HearingDto;
import utility.UtilityClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HearingsShower {

    public boolean noOfHearings(String case_id, String aadhaar_no, HearingDto hearing) {

        boolean flag=false;
        int noofHearingsint=0;
        try {
             PreparedStatement statement1 = UtilityClass.getInstance().prepareStatement("select case_id,person from cases where case_id=? and (person=? or person_lawyer=? or defendent=? or defendent_lawyer=? or judge=?)");

            statement1.setInt(1, Integer.parseInt(case_id));
            statement1.setString(2, aadhaar_no);
            statement1.setString(3, aadhaar_no);
            statement1.setString(4, aadhaar_no);
            statement1.setString(5, aadhaar_no);
            statement1.setString(6, aadhaar_no);

            ResultSet rs1 = statement1.executeQuery();

            if(rs1.next()) {

                flag=true;

                PreparedStatement statement2 = UtilityClass.getInstance().prepareStatement("select hearing_1,hearing_2,hearing_3,hearing_1ts,hearing_2ts,hearing_3ts from hearings where case_id=?");
                statement2.setInt(1, Integer.parseInt(case_id));

                ResultSet rs2= statement2.executeQuery();

                if (rs2.next()) {

                   if(rs2.getString(1)!=null){
                       noofHearingsint=noofHearingsint+1;
                    }

                    if(rs2.getString(2)!=null){
                        noofHearingsint=noofHearingsint+1;
                    }

                    if(rs2.getString(3)!=null){
                        noofHearingsint=noofHearingsint+1;
                    }

                    System.out.println("\nNo. of Hearings for this CaseId : "+noofHearingsint+"\n");
                    hearing.setCase_id(Integer.parseInt(case_id));
                    hearing.setTimestamp_1(rs2.getString(4));
                    hearing.setHearing_1(rs2.getString(1));
                    hearing.setTimestamp_2(rs2.getString(5));
                    hearing.setHearing_2(rs2.getString(2));
                    hearing.setTimestamp_3(rs2.getString(6));
                    hearing.setHearing_3(rs2.getString(3));

                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return flag;
    }


    public int Hearings(String Case_id,String timeOrHearings,String timeDescrip){
        int k=0;
        try {
            PreparedStatement statement1 = UtilityClass.getInstance().prepareStatement("select "+timeOrHearings+" from hearings where case_id=?");

            statement1.setInt(1,Integer.parseInt(Case_id));

            ResultSet rs1=statement1.executeQuery();

            if(rs1.next()){

                if(timeDescrip.equals("time")) {

                    if (rs1.getString(1) != null) {
                        k = 1;
                    }

                    if (rs1.getString(3) != null) {
                        k = 2;
                    }

                    if (rs1.getString(5) != null) {
                        k = 3;
                    }


                    if(k==1){
                        if(rs1.getString(2) == null){
                           k=4;
                        }
                    }

                    else if(k==2){
                        if(rs1.getString(4) == null){
                            k=4;
                        }
                    }

                    else if(k==3){
                        if(rs1.getString(6) == null){
                            k=4;
                        }

                        else{
                            System.out.println("\nHearing finished.\n");
                        }
                    }
                }

                else if(timeDescrip.equals("descrip")){

                    if (rs1.getString(2) != null) {
                        k = 1;
                    }

                    if (rs1.getString(4) != null) {
                        k = 2;
                    }

                    if (rs1.getString(6) != null) {
                        k = 3;
                    }

                    if(k==0){
                        if(rs1.getString(1) == null){
                            k = 4;
                        }
                    }

                    else if(k==1){
                        if(rs1.getString(3) == null){
                            k = 4;
                        }
                    }

                    else if(k==2) {
                        if (rs1.getString(5) == null) {
                            k = 4;
                        }
                    }
                }
            }
        }
            catch (Exception e) {
                System.out.println(e);}

        return k;
    }

    public void updateHearing(String caseid,String any,String setString){

        try {
             PreparedStatement statement1 = UtilityClass.getInstance().prepareStatement("update hearings set "+any+"=? where case_id=?");


            statement1.setString(1,setString);
            statement1.setInt(2,Integer.parseInt(caseid));

            int q=statement1.executeUpdate();
            System.out.println("\n\tSuccessfully updated.\n");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
