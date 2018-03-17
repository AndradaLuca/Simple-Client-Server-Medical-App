


import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements ChatInterface {
   // private static final long serialVersionUID = 1L;
    private String clientName[] = {"Bodea", "Luca", "Asjad", "Clarry", "Tahar"};
    private String clientPass[] = {"12345", "123456", "12345", "123456", "12345"};
    private ArrayList<ChatInterface> clientList;
    public  DoctorController doctorController = new DoctorController();
    public  PatientController patientController = new PatientController();
    private ArrayList<Doctor> doctorArray = new ArrayList<>();
    private ArrayList<Patient> patientArray = new ArrayList<>();

    protected Server() throws RemoteException {
        clientList = new ArrayList<ChatInterface>();
    }



    public synchronized boolean checkClientCredintials(ChatInterface chatinterface, String clientname, String password) throws RemoteException {
        boolean chkLog = false;
        for (int i = 0; i < clientName.length; i++) {
            if (clientName[i].equals(clientname) && clientPass[i].equals(password)) {
                chkLog = true;
                this.clientList.add(chatinterface);
            }
        }
        return chkLog;
    }

    public synchronized void broadcastMessage(ChatInterface chatinterface, String clientname, String message) throws RemoteException {
        for (int i = 0; i < clientList.size(); i++) {
            if (!(clientList.get(i).equals(chatinterface))) {
                clientList.get(i).sendMessageToClient(clientname.toUpperCase() + " : " + message);
            }
        }

    }

    public synchronized ArrayList<Patient> allPatients() throws RemoteException {
        ArrayList<Patient> patientArray = new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/medical_app_schema?useSSL=false", "root", "parola");
                Statement statement = connection.createStatement()
        ) {
            patientArray = patientController.getPatients(statement);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return patientArray;
    }

    public ArrayList<Doctor> allDoctors() throws RemoteException {
        ArrayList<Doctor> doctorArray = new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/medical_app_schema?useSSL=false", "root", "parola");
                Statement statement = connection.createStatement()
        ) {
            doctorArray = doctorController.getDoctors(statement);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return doctorArray;
    }

    public synchronized String search(String nume) throws RemoteException {
        String doctor = "";
        for (int i = 0; i < clientName.length; i++)
            if (nume.equals(clientName[i])) {
                doctor = clientName[i];
            }
        return doctor;
    }

    public synchronized void sendMessageToClient(String message) throws RemoteException {

    }

    public static void startRMIRegistry() {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            System.out.println("RMI Server ready");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public static void connection() throws SQLException{

        //Class.forName("com.mysql.jdbc.Driver");
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/medical_app_schema?useSSL=false", "root", "parola");
                Statement statement = connection.createStatement()
        ) {

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

   // public static void main(String[] arg) throws SQLException, RemoteException, MalformedURLException ,ClassNotFoundException{
      //  startRMIRegistry();


     //   Naming.rebind("RMIServer", new Server());

        //connection();
      //  System.out.println("Conectat la data base");
   // }
}