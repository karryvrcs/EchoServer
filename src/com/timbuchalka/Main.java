package com.timbuchalka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        // Sockets can be used to establish connections, send request, and receive responses.
        // A socket is one end-point of the two-way connection. The client will have a socket, and the server will have
        // a socket.

        // Use the ServerSocket class for the server's socket:
        // The port number allocated here is 5000.
        // port can be an integer between 0 and 65535 inclusive. (* some port numbers are already reserved or may already be in use by other applications.

        // we want the server to listen for clients on the port that we've assigned.
        try (ServerSocket serverSocket = new ServerSocket(5000)){
            // the accept method's going to block until a client connects to the server.
            Socket socket = serverSocket.accept();
            // waiting for a client to connect to it
            System.out.println("Client Connected");

            // when a client does connect, the server will use inputStream and outputStream
            // to send data to the client and receive data from the client.
            // (socket.getInputStream / getoutputStream return the input and output stream associated with the server socket instance)
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // PrintWriter 接受outputStream, File, String filename, BufferWriter
            // true: automatically flush the output stream the print writer is using.
            // If we set it as false, we have to call the flush method after every write to the stream!
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // We want the server to remain alive until the client doesn't need it anymore
            while(true) {
                String echoString = input.readLine();
                System.out.println("The server receive: " + echoString);
                if(echoString.equals("exit")) {
                    // terminate
                    break;
                }
                output.println("Echo from server: " + echoString);
            }
            //Drawback: can only accept one connection ant a time.

        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}