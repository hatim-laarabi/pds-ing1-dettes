import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.sql.*;

public class Client {

    public static void main(String[] zero) {


        Socket socket;
        BufferedReader in;
        PrintWriter out;

        try {
// Le client demande la connexion au serveur
            socket = new Socket(InetAddress.getLocalHost(),2009);
            System.out.println("Demande de connexion");

// Le client lis le message du serveur et l'affiche
            in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
            String message_distant = in.readLine();
            System.out.println(message_distant);

 // Le client envoie au serveur le message input de l'utilisateur
            out = new PrintWriter(socket.getOutputStream());
            String message;
            Scanner sc = new Scanner(System.in);
            System.out.println("Entrez un message pour le serveur :");
            message = sc.nextLine();
            out.println(message);
            out.flush();

            socket.close();

        }catch (UnknownHostException e) {

            e.printStackTrace();
        }catch (IOException e) {

            e.printStackTrace();
        }
    }

}
