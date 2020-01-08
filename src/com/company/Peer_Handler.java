package com.company;

import javax.json.Json;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;


public class Peer_Handler {

    private String peerNameq;
    public static String Hasher;

    //private Integer attempts = 5;

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));





    public void updateListenToPeers(String peerName, String remoteIP, String username, ServerThread serverThread, String localPort, String Hash, String SK) throws Exception {


        Socket socket = null;
        peerNameq = peerName;


        while (socket == null) {
            try {

                socket = new Socket(remoteIP, Integer.valueOf(localPort));

                new PeerThread(socket).start();

            } catch (ConnectException e) {


                System.out.println("Connect failed with ["+ remoteIP +"], waiting and trying again [e to cancel or ENTER to connect again]");

                String input = bufferedReader.readLine();


                if (input.equals("e")){
                    SC_Handler back = new SC_Handler();
                    back.inputCommand(">>");
                }

                try {
                    Thread.sleep(2000);//2 seconds
                } catch (InterruptedException ie) {
                    ie.printStackTrace();

                }
            }
        }


        System.out.println("CONNECTION ESTABLISHED!");

        communicate(peerNameq, username, serverThread, Hash, SK);

    }

    public void communicate(String peerNameq, String username, ServerThread serverThread, String Hash, String SK) {
        BC_Handler Bricks = new BC_Handler();
        Validator validator = new Validator();

        try {

            System.out.println(peerNameq + " IS RUNNING");
            System.out.println("> YOU ARE SECURED NOW AND CAN COMMUNICATE ;) (e to exit, c to change)");

            boolean flag = true;
            while(flag) {
                String message = bufferedReader.readLine();

                if (message.equals("e")) {

                    break;
                }  else {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();

                    StringWriter stringWriter = new StringWriter();
                    Json.createWriter(stringWriter).writeObject(Json.createObjectBuilder()
                            .add("time", dateFormat.format(date))
                            .add("username", username)
                            .add("message", message)
                            .build());
                    String dataObject =  stringWriter.toString();
                    String result = dataObject.replaceAll("\\{", "[").replaceAll("\\}", "]");

                    if (Hasher == null)
                    Bricks.Bricks(peerNameq, Hash, SK,  result);

                    else
                        Bricks.Bricks(peerNameq, Hasher, SK,  result);


                    String[] text = new String[2];
                    text[0] = Brick.hash[0];
                    Hasher = text[0];
                    Validator.P_HashforNext = text[0];

                    text[1] = Brick.hash[1];

                    Hash = text[0];




                    serverThread.sendMessage(text[1]);





                }
            }
            System.exit(0);
        } catch (Exception e) {}
    }




}
