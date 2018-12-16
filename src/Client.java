import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.sql.*;
import com.google.gson.*;

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


            // Le client envoie au serveur son age
            out = new PrintWriter(socket.getOutputStream());
            String age;
            Scanner sc2 = new Scanner(System.in);
            System.out.println("Entrez votre age :");
            age = sc.nextLine();
            out.println(age);
            out.flush();

            // Le client envoie au serveur son prenom
            out = new PrintWriter(socket.getOutputStream());
            String prenom;
            Scanner sc3 = new Scanner(System.in);
            System.out.println("Entrez votre prenom :");
            prenom = sc.nextLine();
            out.println(prenom);
            out.flush();


            socket.close();

        }catch (UnknownHostException e) {

            e.printStackTrace();
        }catch (IOException e) {

            e.printStackTrace();
        }
    }

}

