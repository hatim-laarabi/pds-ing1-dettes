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

        ServerSocket socketserver  ;
        Socket socketduserveur ;
        BufferedReader in;
        PrintWriter out;

        /* try {


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


            // Le serveur lit l'âge du client
            in = new BufferedReader (new InputStreamReader (socketduserveur.getInputStream()));
            String age_client = in.readLine();
            System.out.println("Le client a pour age : " + age_client);


            // Le serveur lit le prénom
            in = new BufferedReader (new InputStreamReader (socketduserveur.getInputStream()));
            String prenom_client = in.readLine();
            System.out.println("Le client s'appelle : " + prenom_client);


            // GSON

            // Le serveur crée un objet java User avec les infos du CLient
            User user_client = new User(age_client, prenom_client);

            // On crée une instance Gson
            Gson gson = new Gson();
            // On crée l'objet Json depuis le user_client crée précédemment avec les input
            String json = gson.toJson(user_client);

            // On désérialise ce json en un objet Java
            Gson gson1 = new Gson();
            User User2 = gson.fromJson(json,User.class);




            socketduserveur.close();
            socketserver.close();

        }catch (IOException e) {

            e.printStackTrace();
        }
    */

        //public void ajout_panne(){

            Scanner input = new Scanner(System.in);

        int id_panne;
        int nb_pieces;
        int cout_pieces = 0;
        int nb_heures;


        System.out.println("Pour enregistrer une panne, entrez le nombre de pièces à remplacer.");
        nb_pieces = input.nextInt();

        for(int n = 1; n <= nb_pieces; n++){
            System.out.println("Entrez le coût de la pièce n°" + n + ".");
            cout_pieces = cout_pieces + input.nextInt();
            }

            System.out.println("Entrez le nombre d'heures dont vous avez besoin pour effectuer l'opération.");
            nb_heures = input.nextInt();

            int cout_panne = cout_pieces +  10 * nb_heures;

            // On crée l'objet Java de la Panne
            Panne p = new Panne(0,nb_pieces,cout_pieces,nb_heures,cout_panne);

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


    }


}
// }
