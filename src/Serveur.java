import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.sql.*;

public class Serveur {

    public static void main(String[] zero) {

        ServerSocket socketserver  ;
        Socket socketduserveur ;
        BufferedReader in;
        PrintWriter out;

        try {

// Le serveur ouvre le socket et se met à l'écoute
            socketserver = new ServerSocket(2009);
            System.out.println("Le serveur est à l'écoute du port "+socketserver.getLocalPort());

 // Le serveur accepte la demande de connexion
            socketduserveur = socketserver.accept();
            System.out.println("Un client s'est connecté.");

// Le serveur envoie un message de bienvenue au client
            out = new PrintWriter(socketduserveur.getOutputStream());
            out.println("Serveur : Coucou, client.");
            out.flush();

 // Le serveur lit le message du client
            in = new BufferedReader (new InputStreamReader (socketduserveur.getInputStream()));
            String message_distant = in.readLine();
            System.out.println("Le client a dit : " + message_distant);

// Le serveur se connecte à la base de données et y stock le message du client
            try {

                //connection to database
                Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/dette?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","root", "root");
                System.out.println("CONNEXION DB");

                // create a Statement from the connection
                Statement statement = myConn.createStatement();

                // insert the data
                statement.executeUpdate("INSERT INTO messages (text_message) VALUES ('"+message_distant+"')");



                //create statement
                Statement myStmt = myConn.createStatement();

                //execute sql query
                ResultSet myRs = myStmt.executeQuery("select * from messages");

                //results set
                while (myRs.next()) {
                    System.out.println(myRs.getString("id_message")+ " ," +myRs.getString("text_message"));
                }
            }
            catch (Exception exc) {
                exc.printStackTrace();
            }

            socketduserveur.close();
            socketserver.close();

        }catch (IOException e) {

            e.printStackTrace();
        }
    }

}
