package fozzie;

import fozzie.fuzzer.CommandFactory;

import java.io.*;
import java.net.*;

public class Fozzie {
	
	private static final int DRONE_PORT = 5556;
	private static final int RECEIVE_PORT = 5554;
	private static final int DRONE_NAVDATA_PORT = 5554;
	private static final int PERIOD = 2000;
	private static final String DRONE_IP_ADDRESS = "192.168.1.1";

  public static void main(String[] args) throws Exception {
	  
  	//setup serverSocket
  	DatagramSocket serverSocket = new DatagramSocket(RECEIVE_PORT);
  	serverSocket.setSoTimeout(PERIOD);
  	byte[] buf = new byte[2048];
    DatagramPacket dp = new DatagramPacket(buf, buf.length);
    
    //setup clientSocket
    DatagramSocket clientSocket = new DatagramSocket();
  	InetAddress IPAddress = InetAddress.getByName(DRONE_IP_ADDRESS);
    
    long startTime;
    long endTime = 0;
    
    
  	
    for (int i = 0; i < 1; i++){
    	//String commandString = CommandFactory.makeRandomCommand(i).toString();
    	String commandString = "AT*REF=1,290717696\rAT*REF=2,290717952\rAT*REF=3,290717696\r";
    	
      System.out.println(commandString.replace("\r", "\\r"));
      
    	byte[] commandBytes =  commandString.getBytes();
    	
    	//constructor arguments: (byte[] buf, int length, InetAddress address, int port)
    	DatagramPacket packetToSend = new DatagramPacket(commandBytes, commandBytes.length, IPAddress, DRONE_PORT);
    	clientSocket.send(packetToSend);
    	
    	
    	
    	//https://stackoverflow.com/questions/10055913/set-timeout-for-socket-receive
    	
    	//send 0001 to the navdata port
    	DatagramPacket navPacketToSend = new DatagramPacket(new byte[]{0, 0, 0, 1}, 4, IPAddress, DRONE_NAVDATA_PORT);
    	clientSocket.send(navPacketToSend);
    	
    	startTime = System.currentTimeMillis();
    	while(true){
    		try{
    			serverSocket.receive(dp);
          String rcvd = "rcvd from " + dp.getAddress() + ", " + dp.getPort() + ": "+ new String(dp.getData(), 0, dp.getLength());
          System.out.println(rcvd);
          endTime = System.currentTimeMillis();
          break;
    		}
    		catch (SocketTimeoutException e) {
          System.out.println("Timeout reached!!! " + e);
          break;
    		}
    	}
    	
    	//Ensure for loop runs with a frequency of 1/PERIOD
    	if(!((endTime - startTime) > PERIOD) && endTime != 0){
    		Thread.sleep(PERIOD-(endTime-startTime));
    	}
    }
    clientSocket.close();
    serverSocket.close();
  }
}