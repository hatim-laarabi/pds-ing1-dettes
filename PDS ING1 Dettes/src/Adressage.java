import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;

public class Adressage {

    public static void main(String[] args) {

        InetAddress LocaleAdresse ;
        InetAddress ServeurAdresse;

        try {

            LocaleAdresse = InetAddress.getLocalHost();
            System.out.println("L'adresse locale est : "+LocaleAdresse );


        } catch (UnknownHostException e) {

            e.printStackTrace();
        }
    }

}