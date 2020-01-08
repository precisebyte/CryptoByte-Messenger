package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//System command handler

public class SC_Handler {

    BufferedReader Command_Reader = new BufferedReader(new InputStreamReader(System.in));

    protected void inputCommand(String System_Messages) throws Exception  {

        String Input_Command;



        System.out.print(System_Messages);
        Input_Command = Command_Reader.readLine();
        commandHandler(Input_Command);

    }

    private void commandHandler(String Input_Command) throws Exception  {

        System_Messages System_Messages = new System_Messages();
        File_Handler File_Handler = new File_Handler();
        Peer_Conf Peer_Conf = new Peer_Conf();
        String peerFileName;

        switch (Input_Command){
            case "decrypt":

                System.out.println("Enter filename to decrypt: ");
                peerFileName = Command_Reader.readLine();
                System.out.println("Enter secret key: ");
                String inputCommand = Command_Reader.readLine();
                try {
                    File_Handler.Decrypt_File(peerFileName, inputCommand, true);
                }catch (IOException e) {
                    System.out.println("File doesn't exist! Please check your filename.");

            }
                inputCommand(System_Messages.Waiting_Command_Message);
                break;

            case "run":
                System.out.println("Enter filename of existing peer: ");
                peerFileName = Command_Reader.readLine();
                File_Handler.Peer_Run(peerFileName);
                inputCommand(System_Messages.Waiting_Command_Message);

                break;
            case "ls":

                File_Handler.List_CBM();
                inputCommand(System_Messages.Waiting_Command_Message);

                break;

            case "ip":

                System.out.println(System_Messages.Local_IP_Message + Peer_Conf.getLocalIP());
                inputCommand(System_Messages.Waiting_Command_Message);

                break;
/*
            case "localip":

                System.out.println(System_Messages.Local_IP_Message + Peer_Conf.getLocalIP2());
                inputCommand(System_Messages.Waiting_Command_Message);

                break;*/



           case "peerconf-internal":


                Peer_Conf.createPeer(true);

                break;

            case "help":
                System.out.println("System command list:");
                System.out.println("[decrypt]: Decrypt peer file");
                System.out.println("[ip]: Show external ip address");
                System.out.println("[ls]: List peer filenames");
                System.out.println("[peerconf]: Configure new peer connection");
                System.out.println("[run]: Run existing peer");
                System.out.println("[version]: Show program version");
                System.out.println("[exit]: Close program");
                inputCommand(System_Messages.Waiting_Command_Message);

                break;

            case "version":
                System.out.println(System_Messages.Version);
                inputCommand(System_Messages.Waiting_Command_Message);

                break;
            case "exit":

                break;
            case "peerconf":


                Peer_Conf.createPeer(false);

                break;

            default:
                System.out.println("Unknown command! Use 'help' to view system command list.");
                inputCommand(System_Messages.Waiting_Command_Message);
        }

    }

    protected void PeerCommandHandler() throws Exception  {

        String Input_Command;

        System_Messages System_Messages = new System_Messages();
        BufferedReader Command_Reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(System_Messages.Cont_Create_Peer);

        Input_Command = Command_Reader.readLine();

        switch (Input_Command){

            case "y":
                return;



            case "n":
                inputCommand(System_Messages.Waiting_Command_Message);
                break;

            default:
                System.out.println("Unknown command!");
                PeerCommandHandler();
        }

    }
}
