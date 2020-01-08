package com.company;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import org.json.JSONObject;


public class PeerThread extends Thread {
    private BufferedReader bufferedReader;
    private static String Hash;
    private static String P_Hash;
    private static String timeStamp;
    private static String data;
    private static String message;



    public PeerThread(Socket socket) throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    public void run() {
        boolean flag = true;
        while (flag) {
            try {
                JsonObject jsonObject = Json.createReader(bufferedReader).readObject();
                if (jsonObject.containsKey("cipher"))
                    message = jsonObject.getString("cipher");

                Validator checkChain = new Validator();
                Crypt recievedCipher = new Crypt();


                String rCipher = recievedCipher.decrypt(message, File_Handler.SK);
                Scanner scan = new Scanner(rCipher);
                scan.useDelimiter("\\{|\\}|\\[|\\]|\\*");
                Hash = scan.next();
                P_Hash = scan.next();
                timeStamp = scan.next();
                data = scan.next();



                if (checkChain.isRemoteHashValid(P_Hash)){
                    String output = "{" + data + "}";

                    JSONObject object  = new JSONObject(output);


                    System.out.println("[" + object.getString("time") + "][" + object.getString("username") + "]: " + object.getString("message"));

                    //System.out.println(Hash + " " + P_Hash + " " + data);
                    File_Handler Append = new File_Handler();
                    Append.Append_Peer_File(File_Handler.peerName, message);
                    Peer_Handler peer_handler = new Peer_Handler();
                    peer_handler.Hasher = this.Hash;
                    Validator.P_HashforNext = this.Hash;
                }
                /*

                System.out.println("[" + jsonObject.getString("time") + "][" + jsonObject.getString("username") + "]: " + jsonObject.getString("message") +  jsonObject.getString("cipher") );*/
            } catch(Exception e) {
                flag = false;
                interrupt();
            }
        }
    }
}
