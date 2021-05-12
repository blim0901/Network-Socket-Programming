package lab_2;//client side

/* Name: Bryan Lim Cheng Yee
   Group: TS4
   IPAddress: 172.21.151.168
 */

import java.net.*;
import java.io.*;

public class Rfc865UdpClient {
    public static void main(String[] argv) {
        //
        // 1. Open UDP socket
        //

        System.out.println("Starting Client...");
        System.out.println("CZ3006 Lab 2 UDP Client");
        DatagramSocket socket= null;

        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            System.out.println("Socket Exception: " + e.getMessage());
        }

        try {
            InetAddress ipAddress;
            ipAddress = InetAddress.getByName("localhost");
            socket.connect(ipAddress,17);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(-1);
        }


        try {
            //
            // 2. Send UDP request to server
            //
            String message = "Bryan Lim, TS4, 172.21.151.168";
            byte[] reqPacket = message.getBytes();
            System.out.println(reqPacket.length);
            DatagramPacket request = new DatagramPacket(reqPacket, reqPacket.length);
            socket.send(request);
            System.out.println("Request sent");

            //
            // 3. Receive UDP reply from server
            //
            byte[] buffer = new byte[512];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            socket.receive(reply);
            System.out.println("Reply received");

            String responseString = new String(buffer, 0, reply.getLength());
            System.out.println(responseString);

        } catch (IOException e) {
            System.out.println("I/O Exception: " + e.getMessage());
        }
    }
}