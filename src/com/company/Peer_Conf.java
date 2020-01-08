package com.company;

import javax.crypto.Cipher;
import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Enumeration;


class Peer_Conf {
    protected  static String localIP;

    protected static void createPeer(boolean internal) throws Exception {

        String[] PeerData = new String[4];
        String Cipher_Key;
        String message = "WELCOME TO NEW PEER :)!";

        BufferedReader Command_Reader = new BufferedReader(new InputStreamReader(System.in));
        BC_Handler BC_Handler = new BC_Handler();
        System_Messages System_Messages = new System_Messages();
        SC_Handler SC_Handler = new SC_Handler(); //System command handler

        System.out.println(System_Messages.Peer_Conf_Welcome);

        SC_Handler.PeerCommandHandler();

        System.out.println(System_Messages.New_Peer_Name);
        PeerData[0] = Command_Reader.readLine();
        if (internal == true){
            System.out.println(System_Messages.Local_IP_Message);
            PeerData[1] = Command_Reader.readLine();}
        else
            PeerData[1] = getLocalIP();
        System.out.println(System_Messages.Remote_Peer_Address);
        PeerData[2] = Command_Reader.readLine();
        System.out.println(System_Messages.Peer_Port);
        PeerData[3] = Command_Reader.readLine();
        System.out.println(System_Messages.Cipher_Key_Message);
        Cipher_Key = Command_Reader.readLine();
        System.out.println(System_Messages.Entered_Peer_Data);
        System.out.println("PEER NAME: " + PeerData[0]);
        System.out.println("REMOTE IP ADDRESS: " + PeerData[2]);
        System.out.println("PORT: " + PeerData[3]);
        SC_Handler.PeerCommandHandler();
        ///////////////////////////
        String Peer_Data_String = Arrays.toString(PeerData);

        if(BC_Handler.Genesis_Block(PeerData[0], Peer_Data_String.replaceAll("\\s+",""), "0", Cipher_Key)){
            System.out.println("SUCCESS: Peer created");
        }else{
            System.out.println("ERROR: Peer not created");
        }
        SC_Handler.inputCommand(System_Messages.Waiting_Command_Message);



    }

    protected static String getLocalIP(){
        String localIP = null;

        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader getIP = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));

            localIP = getIP.readLine();

        }catch (Exception e){
            System.out.println("ERROR: Can't define local IP address");
        }

        return localIP;
    }
    //Obtain internal IP - might be the feature of the future
/*
    protected static String getLocalIP2()  throws Exception {

        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            // drop inactive
            if (!networkInterface.isUp())
                continue;

            // smth we can explore
            Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
            while(addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                System.out.println(String.format("NetInterface: name [%s], ip [%s]",
                        networkInterface.getDisplayName(), addr.getHostAddress()));
                localIP = addr.getHostAddress();
            }
        }
        return localIP;
    }


*/




}
