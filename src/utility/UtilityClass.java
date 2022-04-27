package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

public class UtilityClass {

private UtilityClass(){}

private static Connection Connection=null;

public static Connection getInstance() throws ClassNotFoundException, SQLException {

if(Connection==null){

    Class.forName("com.mysql.cj.jdbc.Driver");

    Connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/judiciary", "root", "Jasppd123");

}
    return Connection;
}


    public static void printWithSleep(String... s) throws InterruptedException {

        for (int i = 0; i < s.length; i++) {

            char[] array = s[i].toCharArray();

            for (int j = 0; j < array.length; j++) {
                System.out.print(array[j]);
                Thread.sleep(2);
            }

            if(Arrays.toString(array).matches("[^m]+")) {

                System.out.println();

            }

        }

    }


    public static void printCustomisable(String... s) throws InterruptedException {

        int k = 0;
        String a="";

        for(int z=0;z<3;z++) {

            for (int i = 0; i < s.length; i++) {

                if (s[i].matches("[a-zA-z: ,'0-9]+") && k == 0) {

                    a=s[i];
                    print2(s[i]);

                    k = k + 1;
                } else if (!s[i].matches("[a-zA-z: ,'0-9]+")) {

                    print2(s[i]);

                }

            }

        }

        for(int q=0;q<a.length();q++){

            System.out.print("\b");
            Thread.sleep(150);

        }

    }


    public static void print2(String... s1) throws InterruptedException {

        String a=s1[0];

        for(int i=0;i<a.length();i++){

            System.out.print(a.charAt(i));
            Thread.sleep(250);

        }

    }


}