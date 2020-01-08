# CryptoByte-Messenger

Introduction
This messenger was based on the idea to store peer data between computers using blockchain approach. All data generated is encrypted and stored with a strong structure of individual hashes, which is also used to compare them between two connected peers [computers]. If the structure of these blocks was violated from one of a peer, then the connection becomes closed due to the danger of data spoofing or any violation of the data.

Encryption is used to store all data secured, it also used to transmit data between peers. Each peer has one secret key, which is set up by peer creator. When one of the peers gets the encrypted data, it decrypts this data with this unique key, and then stores cipher in the blockchain.

Download HERE 

Main Features
P2P – doesn’t require third party

AES256 Encryption

BlockChain data storage

PeerValidation

P2P
CBM-Type doesn’t use any third-party servers, but connecting directly between computers. It gives you more security and freedom in communications.

PeerValidation
Peer validation is a secured blockchain mechanism. When you enter your message, this message generates a block, which includes a hash code of the previous block and current unique generated hash. This blocks compares its hashes and if the blocks are valid, the last generated block will be added to the blockchain. Each peer must compare and validate each message entered by peers and add these to the blockchain storage.

Requirements:
-Java Virtual Machine 8 or higher

-Windows x86/x64

How to use CBM
CryptoByte Messenger Type is a Java application and requires Java Virtual Machine to run it.

You can download Java here:

https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html

If you have downloaded CBM.exe file from our website, you need to open it and if you don’t have a Java installed it will automatically redirect you to Java download page.

When the java virtual machine installed, you can run your application.

Input commands:

help – Shows the help, at the current moment it shows only command list

decrypt – decrypts peer file to see the data

ip – command shows your public ip address

ls – shows *.cbm peer files. Each peer has a unique peer file with encrypted data

peerconf – creates a new peer file with peer data to connect

run – runs the peer

version – version of CBM

exit – close CBM

How to connect
Run CBM, when you will see “>>” that means that Type waits for the input command. To create a new connection peer, enter “peerconf” command, then confirm that you want to create a new peer file. Enter all required data (peer name, the remote IP address to connect with, port number and your secret key), then confirm to create. When .cbm file is created you need to transfer this encrypted file and secret key to the person who you want to communicate. When both computers have these files you can run them and connect with each other.

Enter “run” command, then enter peer name without .cbm part. Enter your secret key, username and then start to connect. If the person, who you want to connect with, running this peer and waits for you, you will be notified that you can communicate. If the person is offline, you will get a message to retry.

Connection troubleshooting
1. Please ensure that you have entered the correct remote peer IP address from the person who you connect. The external IP address of the peer can be found by “ip” command

2. Please ensure that your local port is opened and not used. Please check windows firewall settings and your router settings (if you use Wifi), which can block port that you want to use. Also, ensure that port is not busy by any other service

3. Ensure that your Internet Service provider keeps your ports opened
