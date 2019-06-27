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
import com.google.gson.*;
import java.util.Scanner;

public class Serveur {

    public static void main(String[] zero) {

        boolean restart = true;

 while(restart){



        ServerSocket socketserver  ;
        Socket socketduserveur ;
        BufferedReader in;
        PrintWriter out;

        int id_panne;
        int nb_pieces;
        int cout_pieces = 0;
        int nb_heures;


        try {


// Le serveur ouvre le socket et se met à l'écoute
            socketserver = new ServerSocket(2009);
            System.out.println("Le serveur est à l'écoute du port "+socketserver.getLocalPort());

 // Le serveur accepte la demande de connexion
            socketduserveur = socketserver.accept();
            System.out.println("Un client s'est connecté.");

// Le serveur envoie un message de bienvenue au client
            out = new PrintWriter(socketduserveur.getOutputStream());
            out.println("Serveur : Pour enregistrer une panne, entrez le nombre de pièces à remplacer.");
            out.flush();

 // Le serveur lit le message du client
            in = new BufferedReader (new InputStreamReader (socketduserveur.getInputStream()));
            String message_distant = in.readLine();


            nb_pieces = Integer.parseInt(message_distant);



            for(int n = 1; n <= nb_pieces; n++){
                out = new PrintWriter(socketduserveur.getOutputStream());
                out.println("Serveur : Entrez le coût de la pièce " +n+"");
                out.flush();

                in = new BufferedReader (new InputStreamReader (socketduserveur.getInputStream()));
                String reponse_piece = in.readLine();

                cout_pieces = cout_pieces + Integer.parseInt(reponse_piece);
            }


            out = new PrintWriter(socketduserveur.getOutputStream());
            out.println("Entrez le nombre d'heures dont vous avez besoin pour effectuer l'opération.");
            out.flush();

            in = new BufferedReader (new InputStreamReader (socketduserveur.getInputStream()));
            String reponse_heures = in.readLine();
            nb_heures = Integer.parseInt(reponse_heures);

            int cout_panne = cout_pieces +  10 * nb_heures;



            // On crée l'objet Java de la Panne
            Panne p = new Panne(0,nb_pieces,cout_pieces,nb_heures,cout_panne);


            // On détermine la rentabilité de la panne pour le réparateur.
            out = new PrintWriter(socketduserveur.getOutputStream());
            if(cout_panne < 8000)
                out.println("Serveur : La réparation coûte "+cout_panne+". Elle est rentable.");
            else{
                out.println("Serveur : La réparation coûte "+cout_panne+". Elle N'EST PAS rentable. Faire une demande d'achat au service achat.");
            }
            out.flush();

            String url = "jdbc:mysql://localhost:3306/dette?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
            String login = "root";
            String passwd = "root";
            Connection cn = null;
            Statement st = null;



            try{
                // 1 : Chargement du driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                // 2 : Récupération de la connexion
                cn = DriverManager.getConnection(url, login, passwd);
                // 3 : Création d'un statement
                st = cn.createStatement();
                String sql = "INSERT INTO pannes VALUES (" + nb_pieces + " ," + cout_pieces +" ,"+ cout_panne + ",'" + cout_pieces+cout_panne +"')";
                // 4 : Execution requete
                st.executeUpdate(sql);
            }

            catch (SQLException e){
                e.printStackTrace();
            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
            } finally {
                try {
                    // 5 : libérer ressources mémoire
                    cn.close();
                    st.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }




            // GSON



            // On crée une instance Gson
            Gson gson = new Gson();
            // On crée l'objet Json depuis le user_client crée précédemment avec les input
            String json = gson.toJson(p);

            // On désérialise ce json en un objet Java
            Gson gson1 = new Gson();
            Panne Panne2 = gson.fromJson(json,Panne.class);



            // Le serveur lit le message du client
            in = new BufferedReader (new InputStreamReader (socketduserveur.getInputStream()));
            String restart_answer = in.readLine();

            if(restart_answer.equals("0")){
                restart = false;
            }


            socketduserveur.close();
            socketserver.close();

        }catch (IOException e) {

            e.printStackTrace();
        }






    }

    }
}
// }
