package fozzie;

import fozzie.CommandFactory;

import java.io.*;
import java.net.*;
import java.lang.*;

public class Fozzie {
	
	private static final int DRONE_PORT = 5556;
	private static final InetAddress IPAddress = InetAddress.getByName("192.168.1.1");
  	private static final String SAFE_MODE = "AT*REF=1,290717696\rAT*PCMD=1,0,0,0,0,0\r"; // PCMD hovering mode

	public static void main(String[] args) throws Exception {
		try {
			String cmd = args[0].toUpperCase();
			int period = Integer.parseInt(args[1]);
			int valid = Integer.parseInt(args[2]);
			String commandString = "";
			DatagramSocket clientSocket = new DatagramSocket();

			while (true) {
				commandString = CommandFactory.makeCommand(CommandFactory.CommandType.valueof(cmd), valid!=0).toString();
				if (valid != 0  ) {
					commandString += SAFE_MODE;
				}


				System.out.println(commandString.replace("\r", "\\r"));

				byte[] commandBytes =  commandString.getBytes();
				
				//constructor arguments: (byte[] buf, int length, InetAddress address, int port)
				DatagramPacket packetToSend = new DatagramPacket(commandBytes, commandBytes.length, IPAddress, DRONE_PORT);
				clientSocket.send(packetToSend);
				
				Thread.sleep(period);
			}
			clientSocket.close();
		}

		catch (Exception e) {
		    System.out.println("Argument error.");
		}

	}
}
