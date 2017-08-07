package fozzie;

import fozzie.CommandFactory;

import java.net.*;

public class Fozzie {
	private static final int PORT = 5556;
	
	static CommandFactory.CommandType commandType;
	static int period;
	static boolean safeMode;
	static InetAddress ip;

	public static void main(String[] args) throws Exception {
		try {
			ip = InetAddress.getByName("192.168.1.1");
		} catch (UnknownHostException e) {
			System.out.println("cannot find 192.168.1.1");
			return;
		}

		processArguments(args);
		DatagramSocket socket = new DatagramSocket();
		while (true) {
			String command = CommandFactory.makeCommand(1, commandType, safeMode);
			System.out.println(command);
			socket.send(buildPacket(command));
			Thread.sleep(period);
		}
	}

	private static void processArguments(String[] args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-c")) {
				commandType = CommandFactory.CommandType.valueOf(args[i + 1]);
				i++;
			}
			if (args[i].equals("-p")) {
				period = Integer.parseInt(args[i + 1]);
				i++;
			}
			if (args[i].equals("-s")) {
				safeMode = true;
			}
		}
		if (period == 0)
			period = 1000;
	}

	private static DatagramPacket buildPacket(String command) {
		byte[] buffer = command.getBytes();
		return new DatagramPacket(buffer, buffer.length, ip, PORT);
	}
}
