package tjenkinson.caspar_serverconnection;

import java.io.IOException;

import tjenkinson.caspar_serverconnection.commands.*;

public class Example {

	public static void main(String[] args) throws IOException {
		String caspAddress = "localhost";
		int caspPort = 5250;
		CaspSocket socket = new CaspSocket(caspAddress, caspPort);
		
		// loads AMB.? onto channel 1 layer 1
		CaspReturn response = socket.runCmd(new LoadBg("1-1 \"AMB\""));
		// gets the return code from the command
		System.out.println("Return code: "+response.getStatus());
		
		// plays the video
		// all calls to runCmd are self blocking and only finish after a confirmation has been received from the server
		socket.runCmd(new Play("1-1"));
		
		// gets the information about the video
		CaspReturn response2 = socket.runCmd(new Info("1-1"));
		System.out.println("Return code: "+response2.getStatus());
		// this will contain the xml string containing the information from caspar in this case.
		System.out.println("Response data: "+response2.getResponse());
		
		// opens the caspar diagnostics window
		socket.runCmd(new Diag());
	
		// close the socket
		socket.close();
	}

}
