package com.company;

import java.util.ArrayList;


public class BC_Handler {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static ArrayList<Brick> bricks = new ArrayList<Brick>();


    public void blockchain(String Peer_Name, String Data, String previousHash) throws Exception {



        /*
        blockchain.add(new Block("Yo im the second block",blockchain.get(blockchain.size()-1).hash));
        blockchain.add(new Block("Hey im the third block",blockchain.get(blockchain.size()-1).hash));
*/

    }

    public boolean Genesis_Block (String Peer_Name, String Data, String previousHash, String Cipher_Key ) throws Exception{

        blockchain.add(new Block(Peer_Name, Data, previousHash, Cipher_Key, true));
        /*
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);
        */
        return true;
    }

    public boolean Bricks (String Peer_Name, String previousHash, String Cipher_Key, String message) throws Exception{

        bricks.add(new Brick(Peer_Name, previousHash, Cipher_Key, message));


        return true;
    }


}
