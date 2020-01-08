package com.company;

import java.util.Date;

public class Brick {

    public static String[] hash;
    public String previousHash;
    private long timeStamp;
    private String blockmessage;

    //Block Constructor.
    public  Brick(String Peer_Name, String previousHash, String cipherKey, String message ) throws Exception {

        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash(Peer_Name, cipherKey, message);
        this.blockmessage = message;







    }

    public String[] calculateHash(String Peer_Name, String Cipher_Key, String message) throws Exception {


        SHA_Hash SHA = new SHA_Hash();
        File_Handler File_Handler = new File_Handler() ;
        Peer_Handler Peer_Handler = new Peer_Handler();
        String Cipher;
        String calculatedhash = SHA.applySha256(
                previousHash +
                        timeStamp +  message

        );


        Cipher = Crypt.encrypt("{" + calculatedhash + "}"  + previousHash  + "*" +
                timeStamp + message, Cipher_Key);

        File_Handler.Append_Peer_File(Peer_Name, Cipher);

        String returnObject[] = new String[2];
        returnObject[0] = calculatedhash;
        returnObject[1] = Cipher;





        return returnObject;
    }






}
