import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class ServerMain {
    public static void main(String[] arg) throws SQLException, RemoteException, MalformedURLException,ClassNotFoundException{


        Server server = new Server();
        server.startRMIRegistry();
        Naming.rebind("RMIServer", new Server());

        //connection();
        System.out.println("Conectat la data base");


        }


    }
