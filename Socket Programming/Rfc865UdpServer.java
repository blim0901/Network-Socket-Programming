package lab_2;//server side

import java.net.*;
import java.io.*;

public class Rfc865UdpServer {
    public static void main(String[] argv) throws SocketException {
        //
        // 1. Open UDP socket at well-known port
        //
        System.out.println("Starting Server...");
        DatagramSocket socket = new DatagramSocket();
        try {
            socket = new DatagramSocket(17);
        } catch (SocketException e) {
            System.out.println("Socket exception" + e.getMessage());
        }

        while (true) {
            try {
                //
                // 2. Listen for UDP request from client
                //
                byte[] reqBuffer = new byte[512]; //max is 512 characters
                DatagramPacket request = new DatagramPacket(reqBuffer, reqBuffer.length);
                socket.receive(request);

                // Print requested data into string
                String requestStr = new String(reqBuffer, 0, request.getLength());
                System.out.println(requestStr);

                String data = "Quote Of The Day Hello";
                byte[] quote = data.getBytes();
                InetAddress ipAddress;
                ipAddress = request.getAddress();
                int clientPort;
                clientPort = request.getPort();

                //
                // 3. Send UDP reply to client
                //
                DatagramPacket reply = new DatagramPacket(quote, quote.length, ipAddress, clientPort);
                socket.send(reply);
            } catch (IOException e) {
                System.out.println("I/O exception" + e.getMessage());
            }
        }
    }
}