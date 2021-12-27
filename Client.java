package devoir2;

import java.io.IOException;


import java.io.FileNotFoundException ; 
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.DataOutputStream;
import java.io.File;
import java.io.DataInputStream;
import java.util.Scanner;
import java.io.FileReader;  


public class Client {
	 public static void main(String [] args) throws IOException
	    {
		  
		  Scanner scanner = new Scanner(System.in);
		 try
	        {
	   
	        	InetAddress serverAddress = InetAddress.getByName("localhost");
	            Socket socket = new Socket(serverAddress, 9091);
	            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	                      
	           
	           System.out.println("put 'List' or 'Get' or 'Quit' ");
	            out.println(scanner.nextLine());
	            //result of List command 
	            System.out.println(input.readLine()); 
	            
	      
	            input.close();
	            socket.close();

	        }
	        catch(UnknownHostException e1)
	        {
	            System.out.println("Unknown host exception " + e1.toString());
	        }
	        catch(FileNotFoundException e2 ) {
	        	System.out.println("ERROR");
	        }
	        
	        	
	        
	   
}
	 }
