package manager;

import actions.ResultChecker;
import java.sql.SQLException;
import static manager.RequestManager.Sc;

public class CaseResults {

    ResultChecker res;

    public void caseResults(String aadhaar_no) throws SQLException, ClassNotFoundException {

        System.out.println("\nEnter the required field : \n");
        System.out.println("1.Hearings[hearings].\n2.Filed by you.[filedbyme]\n3.Filed on you[filedonme].\n4.Search through case_id or status[search].\n5.Back[back].");
        res=new ResultChecker();
        String switch_Choice=Sc.nextLine();
        while(switch_Choice.equals("") || !switch_Choice.matches("(hearings|filedbyme|filedonme|search|back)")){
            System.out.println("please enter something.");
            switch_Choice=Sc.nextLine();
        }

        switch (switch_Choice){

            case "hearings":
                RequestManager req=new RequestManager();
                req.HearingShower(aadhaar_no);
                break;

            case "filedbyme":
                res.CaseResultChecker(aadhaar_no,"person");
                break;

            case "filedonme":
                res.CaseResultChecker(aadhaar_no,"defendent");
                break;

            case "search":
                String casee_id2=Sc.next();
                while(casee_id2.equals("") || !casee_id2.matches("^(pending|ongoing|disclosed|[0-9]{1,3})$")){
                    System.out.println("Enter something correctly.");
                    casee_id2=Sc.next();
                }
                if(casee_id2.matches("[0-9]{1,3}")) {
                    res.case_idChecker(casee_id2, aadhaar_no, "case_id");
                }
                else{
                    res.case_idChecker(casee_id2, aadhaar_no, "status");
                }
                break;

            case "back":
                break;

            }
    }
}