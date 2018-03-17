

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ChatInterface extends java.rmi.Remote
{
    boolean checkClientCredintials(ChatInterface ci,String name, String pass) throws RemoteException;
    void broadcastMessage(ChatInterface chatinterface,String name,String message) throws RemoteException;
    void sendMessageToClient(String message) throws RemoteException;
   // ArrayList<String> allDoctors() throws RemoteException;
    String search(String nume) throws RemoteException;
    ArrayList<Doctor> allDoctors() throws RemoteException;
    ArrayList<Patient> allPatients() throws RemoteException;
}