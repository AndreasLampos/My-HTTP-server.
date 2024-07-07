import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class Main {
  public static void main(String[] args) {
    // You can use print statements as follows for debugging, they'll be visible when running tests.
    System.out.println("Logs from your program will appear here!");

    // Uncomment this block to pass the first stage
    
     ServerSocket serverSocket = null;
     Socket clientSocket = null;
    
     try {
      serverSocket = new ServerSocket(4221);
      // Since the tester restarts your program quite often, setting SO_REUSEADDR
      // ensures that we don't run into 'Address already in use' errors
      serverSocket.setReuseAddress(true);
      clientSocket = serverSocket.accept(); // Wait for connection from client.`
      InputStream inputStream = clientSocket.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      String line = reader.readLine();
      System.out.println(line);
      String[] httpPath = line.split(" ", 0);
      System.out.println(httpPath[1]);
      OutputStream output = clientSocket.getOutputStream();
      if (httpPath[1].equals("/")) {
        output.write(("HTTP/1.1 200 OK\r\n\r\n").getBytes());
      } else if (httpPath[1].startsWith("/echo/")) {
        output.write(("HTTP/1.1 200 OK\r\n\r\n").getBytes());
        output.write(httpPath[1].getBytes());
      } 
      else {
        output.write(("HTTP/1.1 404 Not Found\r\n\r\n").getBytes());
      }
       System.out.println("accepted new connection");
       clientSocket.getOutputStream().write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
      } catch (IOException e) {
       System.out.println("IOException: " + e.getMessage());
    }
  }
}
