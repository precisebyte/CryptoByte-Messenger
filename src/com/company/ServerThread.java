package com.company;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.FileHandler;


public class ServerThread extends Thread {


        private ServerSocket serverSocket;
        private Set<ServerThreadThread> serverThreadThreads = new HashSet<ServerThreadThread>();



        public ServerThread(String portNumb) throws IOException {
            serverSocket = new ServerSocket(Integer.valueOf(portNumb));
        }


        public void run() {
            try {
                while (true) {
                    ServerThreadThread serverThreadThread = new ServerThreadThread(serverSocket.accept(), this);
                    serverThreadThreads.add(serverThreadThread);
                    serverThreadThread.start();
                }
            } catch (Exception e) { e.printStackTrace(); }
        }
        void sendMessage(String message) {
            try {



                serverThreadThreads.forEach(t-> {
                    try {
                        t.getPrintWriter(message).println("{" +  "\"cipher\":" + "\"" + message + "\"" + "}");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });


            } catch(Exception e) { e.printStackTrace();

            }
        }
        public Set<ServerThreadThread> getServerThreadThreads() { return serverThreadThreads; }
    }



