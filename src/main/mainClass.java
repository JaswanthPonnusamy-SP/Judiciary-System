package main;

import service.Service;
import utility.UtilityClass;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class mainClass {

private static Service serve;

    public static final String BLUE_BOLD = "\033[1;34m";
    public static final String ANSI_BLACK = "\u001B[1;30m";

    public static void main(String[] args) throws InterruptedException, IOException, ParseException, SQLException, ClassNotFoundException {

//        UtilityClass.printWithSleep(BLUE_BOLD, "\u2593\u2593\u2593\u2593\u2593 \u2593  \u2593 \u2593\u2593\u2593\u2593\u2593 \u2593 \u2593\u2593\u2593\u2593 \u2593 \u2593\u2593\u2593\u2593\u2593 \u2593\u2593\u2593\u2593\u2593 \u2593   \u2593", ANSI_BLACK, "  \u2593   \u2593  \u2593  \u2593  \u2593 \u2593 \u2593    \u2593 \u2593   \u2593 \u2593   \u2593  \u2593 \u2593", ANSI_BLACK, "\u2593 \u2593   \u2593  \u2593  \u2593  \u2593 \u2593 \u2593    \u2593 \u2593\u2593\u2593\u2593\u2593 \u2593\u2593\u2593\u2593    \u2593", BLUE_BOLD, "\u2593\u2593\u2593   \u2593\u2593\u2593\u2593 \u2593\u2593\u2593\u2593\u2593 \u2593 \u2593\u2593\u2593\u2593 \u2593 \u2593   \u2593 \u2593  \u2593    \u2593",ANSI_BLACK);
        UtilityClass.printWithSleep(BLUE_BOLD," ▓▓▓▓▓▓▓ ▓   ▓ ▓▓▓  ▓▓▓▓▓ ▓▓▓▓ ▓▓▓▓▓ ▓▓▓▓ ▓▓▓  ▓   ▓     ▓▓▓▓▓ ▓   ▓ ▓▓▓▓▓ ▓▓▓▓▓ ▓▓▓▓▓ ▓▓▓▓▓▓▓",ANSI_BLACK,"      ▓  ▓   ▓ ▓  ▓   ▓   ▓      ▓   ▓  ▓ ▓  ▓ ▓   ▓     ▓     ▓   ▓ ▓       ▓   ▓     ▓  ▓  ▓",BLUE_BOLD,"      ▓  ▓   ▓ ▓  ▓   ▓   ▓      ▓   ▓▓▓▓ ▓▓▓  ▓▓▓▓▓     ▓▓▓▓▓ ▓▓▓▓▓ ▓▓▓▓▓   ▓   ▓▓▓▓▓ ▓  ▓  ▓",ANSI_BLACK,"  ▓   ▓  ▓   ▓ ▓  ▓   ▓   ▓      ▓   ▓  ▓ ▓ ▓      ▓         ▓     ▓     ▓   ▓   ▓     ▓  ▓  ▓ ",BLUE_BOLD,"  ▓▓▓▓▓  ▓▓▓▓▓ ▓▓▓  ▓▓▓▓▓ ▓▓▓▓ ▓▓▓▓▓ ▓  ▓ ▓  ▓   ▓▓▓     ▓▓▓▓▓   ▓▓▓ ▓▓▓▓▓   ▓   ▓▓▓▓▓ ▓  ▓  ▓ ",ANSI_BLACK);
        System.out.println("\n\n\t\t******LOGIN TO YOUR ACCOUNT******\n");
        serve=new Service();
        serve.getInput();
        UtilityClass.getInstance().close();

    }
}



