package dto;

public class CaseDto {

    public String CaseDescription;

    public int case_id;

    public String user;

    public String lawyer;

    public String judge;

    public String defendent_name;

    public String defendentLawyer_name;

    public String user_adhaar;

    public String lawyer_adhaar;

    public String judge_adhaar;

    public String defendent_adhaar;

    public String defendentlawyer_Adhaar;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCaseDescription() {
        return CaseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        CaseDescription = caseDescription;
    }

    public int getCase_id() {
        return case_id;
    }

    public void setCase_id(int case_id) {
        this.case_id = case_id;
    }

    public String getLawyer() {
        return lawyer;
    }

    public void setLawyer(String lawyer) {
        this.lawyer = lawyer;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }

    public String getUser_adhaar() {
        return user_adhaar;
    }

    public void setUser_adhaar(String user_adhaar) {
        this.user_adhaar = user_adhaar;
    }

    public String getLawyer_adhaar() {
        return lawyer_adhaar;
    }

    public void setLawyer_adhaar(String lawyer_adhaar) {
        this.lawyer_adhaar = lawyer_adhaar;
    }

    public String getJudge_adhaar() {
        return judge_adhaar;
    }

    public void setJudge_adhaar(String judge_adhaar) {
        this.judge_adhaar = judge_adhaar;
    }


    public String getDefendent_adhaar() {
        return defendent_adhaar;
    }

    public void setDefendent_adhaar(String defendent_adhaar) {
        this.defendent_adhaar = defendent_adhaar;
    }

    public String getDefendentlawyer_Adhaar() {
        return defendentlawyer_Adhaar;
    }

    public void setDefendentlawyer_Adhaar(String defendentlawyer_Adhaar) {
        this.defendentlawyer_Adhaar = defendentlawyer_Adhaar;
    }



    public String getDefendent_name() {
        return defendent_name;
    }

    public void setDefendent_name(String defendent_name) {
        this.defendent_name = defendent_name;
    }

    public String getDefendentLawyer_name() {
        return defendentLawyer_name;
    }

    public void setDefendentLawyer_name(String defendentLawyer_name) {
        this.defendentLawyer_name = defendentLawyer_name;
    }

}
