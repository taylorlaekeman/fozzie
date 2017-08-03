package fozzie;

import fozzie.fuzzer.CommandFactory;

import java.io.*;
import java.net.*;
import java.lang.*;

public class Fozzie {
	
	private static final int DRONE_PORT = 5556;
	private static final int RECEIVE_PORT = 5554;
	private static final int DRONE_NAVDATA_PORT = 5554;
	private static final String DRONE_IP_ADDRESS = "192.168.1.1";
  private static final String SAFE_MODE = "AT*PCMD=1,0,0,0,0,0\r"; // PCMD hovering mode

  public static void main(String[] args) throws Exception {
    try {
  	  String cmd = args[0].toUpperCase();
      int period = Int.parseInt(args[1]);
      int valid = Int.parseInt(args[2]);

      String commandString = CommandFactory.makeCommand(CommandFactory.CommandType.valueof(cmd), boolean valid!=0).toString();
      if (valid) {
        commandString += SAFE_MODE;
      }
    }

    catch (Exception e) {
      if (args.length != 0) {
        System.out.println(e.getMessage());
        System.out.println("Generate random command instead.");
        int period = 50;
      }
      String commandString = CommandFactory.makeCommand();
    }
  	//setup serverSocket
  	DatagramSocket serverSocket = new DatagramSocket(RECEIVE_PORT);
  	serverSocket.setSoTimeout(period);
  	byte[] buf = new byte[2048];
    DatagramPacket dp = new DatagramPacket(buf, buf.length);
    
    //setup clientSocket
    DatagramSocket clientSocket = new DatagramSocket();
  	InetAddress IPAddress = InetAddress.getByName(DRONE_IP_ADDRESS);
    
    long startTime;
    long endTime = 0;
    

  	
  	//String commandString = "AT*REF=1,290717696\rAT*REF=2,290717952\rAT*REF=3,290717696\r";
  	//String commandString = "AT*CONFIG=\"general:navdata_demo\",\"TRUE\"\\r";
  	
    System.out.println(commandString.replace("\r", "\\r"));
    
  	byte[] commandBytes =  commandString.getBytes();
  	
  	//send 0001 to the navdata port
  	DatagramPacket navPacketToSend = new DatagramPacket(new byte[]{1, 0, 0, 0}, 4, IPAddress, DRONE_NAVDATA_PORT);
  	clientSocket.send(navPacketToSend);
  	
  	//constructor arguments: (byte[] buf, int length, InetAddress address, int port)
  	DatagramPacket packetToSend = new DatagramPacket(commandBytes, commandBytes.length, IPAddress, DRONE_PORT);
  	clientSocket.send(packetToSend);
  	
  	
  	
  	//https://stackoverflow.com/questions/10055913/set-timeout-for-socket-receive
  	
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
  	if(!((endTime - startTime) > period) && endTime != 0){
  		Thread.sleep(period-(endTime-startTime));
  	}
    
    clientSocket.close();
    serverSocket.close();
  }
}
