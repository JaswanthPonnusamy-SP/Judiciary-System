package actions;

import dto.ProfileDto;
import utility.UtilityClass;

import java.sql.*;

public class ProfileUser {

    public ProfileDto profileOfUser;

    public ProfileDto setupTheProfile(String adhaar_no){

        try{
             PreparedStatement statement = UtilityClass.getInstance().prepareStatement("SELECT * FROM profile WHERE adhaar = ?");

            statement.setString(1, adhaar_no);

            ResultSet rs = statement.executeQuery();
            rs.next();

            profileOfUser=new ProfileDto();
            profileOfUser.setName(rs.getString(3));
            profileOfUser.setAdhaar_number(adhaar_no);
            profileOfUser.setAge(rs.getInt(4));
            profileOfUser.setAddress(rs.getString(7));
            profileOfUser.setContact_no(rs.getString(5));
            profileOfUser.setEmail_Id(rs.getString(6));

        }catch(Exception e){ System.out.println(e);}

    return profileOfUser;
    }
}
