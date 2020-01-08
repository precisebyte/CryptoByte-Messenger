package com.company;

public class Validator {

    private static String Prev_Hash;
    protected static String P_HashforNext;

    public Boolean isChainValid(String Hash, String P_Hash, String timeStamp, String data) throws Exception {

        //Block currentBlock = null;
        Block currentBlock = new Block("","","","", false);


        getHash(Hash);




        if(!Hash.equals(currentBlock.calculateHash("", "", timeStamp,P_Hash, data, false)) ){
                System.out.println("ERROR!: CURRENT HASHES NOT EQUAL, YOUR FILE WAS BROKEN!!! :(");
                System.out.println("ERROR!: YOU CAN'T COMMUNICATE IN THIS PEER!");
            SC_Handler SC_Handler = new SC_Handler();
            SC_Handler.inputCommand(">>");


            return false;
            }else {
            Prev_Hash = P_Hash;


        }
            //compare previous hash and registered previous hash

            if(!P_Hash.equals(Prev_Hash) ) {
                System.out.println("ERROR!: PREVIOUS HASHES NOT EQUAL, YOUR FILE WAS BROKEN!!! :(");
                System.out.println("ERROR!: YOU CAN'T COMMUNICATE IN THIS PEER!");
                SC_Handler SC_Handler = new SC_Handler();
                SC_Handler.inputCommand(">>");
                return false;
            }


        return true;
    }

    public Boolean isRemoteHashValid(String P_Hash) throws Exception {

        if(!P_Hash.equals(P_HashforNext) ) {
            System.out.println("WARNING!: RECIEVED HASH IS NOT EQUAL TO THIS PEER BLOCKCHAIN!!! :(");
            System.out.println("DANGER!: THIS PEER IS BROKEN AND WE NEED TO CLOSE THIS PEER!");
            System.out.println("DANGER!: FOR SECURITY REASONS, PLEASE CONTACT WITH THE PERSON WHO USES THIS PEER!");
            System.exit(0);
            return false;
        }


        return true;
    }

    public void getHash(String Hash){
        this.P_HashforNext = Hash;
    }

    public String retValue (){
        return this.P_HashforNext;
    }





}