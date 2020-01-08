package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.Scanner;
import java.util.ArrayList;

public class File_Handler {
    private static String port;
    private static String Host1_IP;
    private static String Host2_IP;
    private static String localIP;
    private static String remoteIP;
    protected static String peerName;
    private static String Hash;

    private static String Message;
    private static String P_Hash;
    protected static String SK;
    private static String timeStamp;
    private static String data;


    ArrayList<String> Array = new ArrayList<String>(5);
    protected static void Write_Peer_File(String Peer_Name, String Data) throws IOException {

        if (Files.exists(Paths.get(Peer_Name))){
            System.out.println("Peer file " + Peer_Name + " exists! File can't be created, please use another name of the peer!");
        }else {
            FileWriter out = new FileWriter(Peer_Name + ".cbm");
            out.write(Data + "\n");
            out.close();
        }
    }

    protected static void Append_Peer_File(String Peer_Name, String Data) throws IOException {

        if (Files.exists(Paths.get(Peer_Name + ".cbm"))){
            FileWriter out = new FileWriter(Peer_Name + ".cbm", true);
            out.write( Data + "\n");
            out.close();
        }else {

            System.out.println("WARNING: Peer file " + Peer_Name + " doesn't exists!");

        }
    }

    protected void List_CBM() throws IOException {


            Files.newDirectoryStream(Paths.get("."),
                    path -> path.toString().endsWith(".cbm"))
                    .forEach(System.out::println);


    }

    protected void Peer_Run(String File_Name) throws Exception {
        String Input_Command;
        String username;

        File File = new File(File_Name + ".cbm");
        if(File.exists()){
            BufferedReader Command_Reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter peer secret key:");
            SK = Command_Reader.readLine();

            System.out.println("SYNCHRONISING PEER... THIS MAY TAKE AWHILE!");
            System.out.println("Checking PeerFile...");
            try {
                Decrypt_File(File_Name, SK, false);
                //Read File for peer run and the n connect
              //  Read_Peer_File(File_Name, SK);
            }catch (NullPointerException e){
                System.out.println("ERROR: Secret key is not valid!");
                Peer_Run(File_Name);
            }
            ServerThread serverThread = new ServerThread(port);
            serverThread.start();
            Peer_Conf getLocalIP = new Peer_Conf();
            localIP = getLocalIP.getLocalIP();



            if (localIP.equals(Host1_IP)){
                remoteIP = Host2_IP;

            }else{
                remoteIP = Host1_IP;

            }

                    // Temporarly username input
            System.out.println("Enter username:");
            Input_Command = Command_Reader.readLine();
            username = Input_Command;
            new Peer_Handler().updateListenToPeers(peerName, remoteIP ,username, serverThread, port, Hash, SK);




        }else{
            System.out.println("Peer file doesn't exist! Check the filename and try again, or create new peer.");
        }

    }
    protected void Read_Peer_File(String File_Name, String Input_Command) throws Exception {
        String Master_Key = Input_Command;

        BufferedReader readFirstLine = new BufferedReader(new FileReader(File_Name + ".cbm"));
        String Data = readFirstLine.readLine();
        String Encrypted_Message = Data;
        String Cipher = Crypt.decrypt(Encrypted_Message, Master_Key );


        Scanner scan = new Scanner (Cipher);

        scan.useDelimiter("\\{|\\}|\\[|\\,|\\]|\\*");
      scan.next();
      scan.next();
      scan.next();
        peerName = scan.next();
        Host1_IP = scan.next();
        Host2_IP =  scan.next();
        port =  scan.next();


        //creating a global array from peerfile


    }


    protected void Decrypt_File(String File_Name, String Input_Command, Boolean Decrypt) throws Exception {
        String Master_Key = Input_Command;

        Scanner readLines = new Scanner(new File(File_Name + ".cbm"));
        Validator checkChain = new Validator();

        String Data;
        //Checking blockchain in file, if Decrypt is true then print peer content
        int index = 0;
        while (readLines.hasNextLine()){

            ++index;
            Data = readLines.nextLine();
            try {
                String Cipher = Crypt.decrypt(Data, Master_Key);
                Scanner scan = new Scanner(Cipher);
                scan.useDelimiter("\\{|\\}|\\[|\\]|\\*");

                Hash = scan.next();
                if (index == 1){
                    P_Hash = "0";
                } else {
                    P_Hash = checkChain.retValue();
                }
                scan.next();

                timeStamp = scan.next();
                data = scan.next();


                if (Decrypt){
                    Print_File(Cipher);

                }else
                    checkChain.isChainValid(Hash, P_Hash, timeStamp, "[" + data + "]");
            }catch (IOException e){}

            catch (NullPointerException e){}


        }
        System.out.println("DONE!");
        readLines.close();




    }




    protected void Print_File(String Data) throws Exception {
        System.out.println(Data);


    }



}
