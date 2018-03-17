

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client extends UnicastRemoteObject implements ChatInterface , Runnable
{
   // private static final long serialVersionUID = 1L;
    private ChatInterface server;
    private String ClientName;
    boolean chkExit = true;
    boolean chkLog = false;
    ArrayList<Doctor> doctorArray = new ArrayList<>();
    ArrayList<Patient> patientArray = new ArrayList<>();

    protected Client(ChatInterface chatinterface,String clientname,String password) throws RemoteException
    {
        this.server = chatinterface;
        this.ClientName = clientname;
        chkLog = server.checkClientCredintials(this,clientname,password);
    }

    public void sendMessageToClient(String message) throws RemoteException
    {
        System.out.println(message);
    }

    public void broadcastMessage(ChatInterface chatinterface,String clientname,String message) throws RemoteException {}

    public boolean checkClientCredintials(ChatInterface chatinterface ,String clientname,String password) throws RemoteException{
        return true;
    }

    public  ArrayList<Doctor> allDoctors() throws RemoteException {
        ArrayList<Doctor> doctorArrays = new ArrayList<>();
        return doctorArrays;
    }

    public  ArrayList<Patient> allPatients() throws RemoteException

    {
        ArrayList<Patient> patientArrays = new ArrayList<>();
        return patientArrays;
    }


    public String search(String nume) throws RemoteException
    {   String an="";
        return an;
    }

    public void run()
    {
        if(chkLog)
        {
            System.out.println("Successfully Connected To RMI Server");
            System.out.println("NOTE : Type #logout to Exit From The Service");
            System.out.println("NOTE : Type #doctors to see all doctors");
            System.out.println("NOTE : Type #patients to see al patients");
            System.out.println("Now Your Online To Chat\n");
            Scanner scanner = new Scanner(System.in);

            String message;
            while(chkExit)
            {
                message = scanner.nextLine();

                if(message.equals("#patients"))
                {
                    try
                    {

                        ArrayList<Patient> patientArray = server.allPatients();
                        for (int i=0; i<patientArray.size(); i++)
                        {
                            System.out.println(patientArray.get(i).showPatientDetails());
                        }

                    }
                    catch(RemoteException e)
                    {
                        e.printStackTrace();
                    }
                }else

                if(message.equals("#search"))
                {    String message1;
                    message1 = scanner.nextLine();
                    try {
                        String doctor = server.search(message1);
                        System.out.println(doctor);
                    }
                    catch(RemoteException e)
                    {
                        e.printStackTrace();
                    }

                }else

                if (message.equals("#doctors"))
                {   try
                {

                    ArrayList<Doctor> doctorArray  =server.allDoctors();
                    for (int i=0; i<=doctorArray.size()-1; i++)
                    {
                        System.out.println(doctorArray.get(i).showDoctorDetails());
                    }



                }
                catch(RemoteException e)
                {
                    e.printStackTrace();
                }

                }else
                if(message.equals("#logout"))
                {
                    chkExit = false;
                }
                else
                {
                    try
                    {
                        server.broadcastMessage(this.server,ClientName , message);
                    }
                    catch(RemoteException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("\nSuccessfully Logout From The RMI Chat Program\n");       }
        else
        {
            System.out.println("\nClient Name or Password Incorrect...");
        }
    }

    public static void main(String[] args) throws MalformedURLException,RemoteException,NotBoundException
    {
        Scanner scanner = new Scanner(System.in);
        String clientName = "";
        String clientPassword = "";

        System.out.println("\n~~ Welcome To RMI Chat Program ~~\n");
        System.out.print("Enter The Name : ");
        clientName = scanner.nextLine();
        System.out.print("Enter The Password : ");
        clientPassword = scanner.nextLine();
        System.out.println("\nConnecting To RMI Server...\n");

        ChatInterface chatinterface = (ChatInterface)Naming.lookup("rmi://localhost/RMIServer");
        new Thread(new Client(chatinterface , clientName , clientPassword)).start();
    }

}