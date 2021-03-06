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


        boolean restart = true;
        while (restart) {


            Socket socket;
            BufferedReader in;
            PrintWriter out;

            try {
// Le client demande la connexion au serveur
                socket = new Socket(InetAddress.getLocalHost(), 2009);
                System.out.println("Demande de connexion");

// Le client lis le message du serveur et l'affiche
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message_distant = in.readLine();
                System.out.println(message_distant);

                // Le client envoie au serveur le message input de l'utilisateur
                out = new PrintWriter(socket.getOutputStream());
                String message;
                Scanner sc = new Scanner(System.in);
                System.out.println("Entrez le nombre de pièces pour le serveur :");
                message = sc.nextLine();
                out.println(message);
                out.flush();

                for (int n = 1; n <= Integer.parseInt(message); n++) {

                    // Le client envoie au serveur le prix de chaque pièce
                    out = new PrintWriter(socket.getOutputStream());
                    String prix_pieceN;
                    System.out.println("Entrez le cout de la pièce " + n + " pour le serveur :");
                    prix_pieceN = sc.nextLine();
                    out.println(prix_pieceN);
                    out.flush();

                }


                out = new PrintWriter(socket.getOutputStream());
                System.out.println("Entrez le nombre d'heures nécessaires pour effectuer la réparation :");
                out.println(sc.nextLine());
                out.flush();

                String test = in.readLine();

                // while(test.equals("Serveur : Entrez le coût de la pièce 1")){ }


                // Le client lit le résultat du calcul de rentabilité
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message_rentabilite = in.readLine();
                System.out.println(message_rentabilite);


                out = new PrintWriter(socket.getOutputStream());
                System.out.println("Inscrire une nouvelle panne ? 1 : OUI  0 : NON");
                String restart_answer = sc.nextLine();
                out.println(restart_answer);

                out.flush();

                if (restart_answer.equals("0")) {
                    restart = false;
                }


                socket.close();

            } catch (UnknownHostException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }
}

