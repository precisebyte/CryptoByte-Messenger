package com.company;

import java.util.Date;

public class Block {

    public String hash;
    public static String previousHash;
    private long timeStamp;
    private String data;


    //Block Constructor.
    public Block(String Peer_Name, String data, String prevHash, String cipherKey,  boolean writeToFile ) throws Exception {
        this.data = data;
        this.previousHash = prevHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash(Peer_Name, cipherKey, Long.toString(timeStamp), previousHash, data, writeToFile);


    }

    public String calculateHash(String Peer_Name, String Cipher_Key,  String timeStamp, String previousHash, String data, boolean writeToFile) throws Exception {

        SHA_Hash SHA = new SHA_Hash();
        File_Handler File_Handler = new File_Handler() ;
        String Cipher;
        String calculatedhash = SHA.applySha256(
                previousHash +
                        Long.parseLong(String.valueOf(timeStamp)) + data

        );

        if (writeToFile) {
            Cipher = Crypt.encrypt("{" + calculatedhash + "}" + previousHash + "*" +
                    timeStamp + data, Cipher_Key);

            File_Handler.Write_Peer_File(Peer_Name, Cipher);

        }


        return calculatedhash;
    }








}
