package fozzie;

import fozzie.fuzzer.CommandFactory;

import java.io.*;
import java.net.*;

public class Fozzie {

  public static void main(String[] args) throws Exception {
	  
  	DatagramSocket clientSocket = new DatagramSocket();
  	InetAddress IPAddress = InetAddress.getByName("192.168.1.1");
  	
    for (int i = 0; i < 1; i++){
    	//String commandString = CommandFactory.makeRandomCommand(i).toString();
    	String commandString = "AT*REF=1,290717696\rAT*REF=2,290717952\rAT*REF=3,290717696\r";
    	
      System.out.println(commandString.replace("\r", "\\r"));
    	byte[] commandBytes =  commandString.getBytes();
    	
    	//constructor arguments: (byte[] buf, int length, InetAddress address, int port)
    	DatagramPacket sendPacket = new DatagramPacket(commandBytes, commandBytes.length, IPAddress, 5556);
    	clientSocket.send(sendPacket);
    	Thread.sleep(1000);
    }
    clientSocket.close();
  }
}