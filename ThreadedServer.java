package devoir2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ThreadedServer {
	public static void main(String[] args)
    {
        File myfile = null ;
        Scanner scanner = new Scanner(System.in);
        String  clientinput = null ;
        String rep = null; 
		try {
            ServerSocket serverSocket = new ServerSocket(9091);
            serverSocket.setReuseAddress(true);
            System.out.println("waiting for clients...");
            
            //the main thread is accepting new connections
            while (true) {
                Socket socket = serverSocket.accept();
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                ThreadTask client_socket = new ThreadTask(socket ) ; 
                //theard will start 
                new Thread(client_socket).start();
                //get port number 
                out.print("Server Listening on Port :" + socket.getLocalPort() );
              
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 	             clientinput =input.readLine() ; 
 	              rep = input.readLine() ; 
 	          
 	           		//FOR LIST COMMAND
	            if(clientinput.equals("List")) {
	            	System.out.println("the command list script will begin now ");
	            	out.print("give me your rep");
	            	out.print(scanner.nextLine());
	            	rep = input.readLine() ;
	            	try {
	            		 myfile = new File (rep);
			            String[] mylist = myfile.list() ; 
			            System.out.println("List of files and directories in the specified directory:");
			            for(int i=0; i<mylist.length; i++ ) {
			            	out.println(mylist[i]) ; 
			            }
	            	}catch(NullPointerException e3) {System.out.println("ERROR THIS rep DOESN'T EXIST !");}
	            	
	            	     //FOR GET COMMAND
		            }else if(clientinput.equals("Get")) {
		            	out.print("give me your rep");
		            	out.print(scanner.nextLine());
		            	System.out.println("give me a file name  : ");
		            	String fname = input.readLine() ; 
		            	try {
		            		 myfile = new File (rep);
		            		BufferedReader br  = new BufferedReader(new FileReader(myfile));
		                   String st;
		                while ((st = br.readLine()) != null) {
		                    System.out.println(st);
		            }
		            	}catch(FileNotFoundException e3) {
		            		System.out.println("ERROR THIS file DOESN'T EXIST !");}
		            	
		            }
                		//FOR QUIT 
	            	if(clientinput.equals("Quit")) {
	            		System.out.println("Session Closed ");
	            		out.close();
	                    socket.close();
	            		
	            	}
                
	            }
	            
		}
         
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    
   }
	 private static class ThreadTask implements Runnable {
		private Socket socket ; 
		File myfile = null ;
        Scanner scanner = new Scanner(System.in);
        String  clientinput = null ;
        String rep = null;
		public  ThreadTask(Socket so ) {
			this.socket =so ; 
		}
	@Override
	public void run() {
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream());
	         
	         //get port number 
	         out.print("Server Listening on Port :" + socket.getLocalPort() );
	       
	         BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	           clientinput =input.readLine() ; 
	            rep = input.readLine() ; 
	        
	         		//FOR LIST COMMAND
	         if(clientinput.equals("List")) {
	         	System.out.println("the command list script will begin now ");
	         	out.print("give me your rep");
	         	out.print(scanner.nextLine());
	         	rep = input.readLine() ;
	         	try {
	         		 myfile = new File (rep);
			            String[] mylist = myfile.list() ; 
			            System.out.println("List of files and directories in the specified directory:");
			            for(int i=0; i<mylist.length; i++ ) {
			            	out.println(mylist[i]) ; 
			            }
	         	}catch(NullPointerException e3) {System.out.println("ERROR THIS rep DOESN'T EXIST !");}
	         	
	         	     //FOR GET COMMAND
		            }else if(clientinput.equals("Get")) {
		            	out.print("give me your rep");
		            	out.print(scanner.nextLine());
		            	System.out.println("give me a file name  : ");
		            	String fname = input.readLine() ; 
		            	try {
		            		 myfile = new File (rep);
		            		BufferedReader br  = new BufferedReader(new FileReader(myfile));
		                   String st;
		                while ((st = br.readLine()) != null) {
		                    System.out.println(st);
		            }
		            	}catch(FileNotFoundException e3) {
		            		System.out.println("ERROR THIS file DOESN'T EXIST !");}
		            	
		            }
	         		//FOR QUIT 
	         	if(clientinput.equals("Quit")) {
	         		System.out.println("Session Closed ");
	         		out.close();
	                 socket.close();
	         		
	         	}
		}
		 
         	catch(IOException e)
         	 {
         	     System.out.println(e.toString());
         	 }
         }
         
	}
  
 
	
	



}



