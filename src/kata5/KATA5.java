package kata5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KATA5 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conec = DriverManager.getConnection("jdbc:oracle:thin:@10.0.2.15:1521:orcl", "system", "orcl");

        Statement state = conec.createStatement();
        ResultSet rs = state.executeQuery("SELECT * FROM PEOPLE");

        while (rs.next()) {
            System.out.println("ID = " + rs.getInt("ID"));
            System.out.println("NAME = " + rs.getString("NAME"));
        }


        BufferedReader reader = new BufferedReader(new FileReader(new File("emailsfilev1.txt")));

        String mail;
        while ((mail = reader.readLine()) != null) {
            state.executeUpdate("INSERT INTO MAIL (MAIL) VALUES ('" + mail + "')");
        }

        rs.close();
        state.close();
        conec.close();
    }
}
