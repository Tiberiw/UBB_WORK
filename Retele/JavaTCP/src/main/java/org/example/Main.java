package org.example;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {


        try(ServerSocket serverSocket = new ServerSocket(8888)) {
            System.out.println("Server start on port 8888");

            while(true) {
                Socket client = serverSocket.accept();
                handle_connection(client);
            }


        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void handle_connection(Socket c) throws IOException {
        DataInputStream socketIn = new DataInputStream(c.getInputStream());
        DataOutputStream socketOut = new DataOutputStream(c.getOutputStream());
        int size1 = socketIn.readUnsignedShort();
        System.out.println("Lungime primul sir: " + size1);

        byte[] sir1 = new byte[size1];
        socketIn.read(sir1);
        System.out.println("Primul sir:" + new String(sir1));

//        String message = socketIn.readUTF();
//        System.out.println("Primul sir:" + message);
        int size2 = socketIn.readUnsignedShort();
        System.out.println("Lungime al doile sir: " + size2);

        byte[] sir2 = new byte[size2];
        socketIn.read(sir2);
        System.out.println("Al doilea sir:" + new String(sir2));

        int size = Math.min(size1, size2);

        int[] freq = new int[256];
        for(int i = 0; i < 256; i++)
            freq[i] = 0;

        int solution_frequency = 0;
        char solution_character = '\0';

        for(int i = 0; i < size; i++)
            if(sir2[i] == sir1[i])
                freq[sir1[i]]++;

        for(int i = 0; i < 256; i++) {
            if(solution_frequency < freq[i]) {
                solution_frequency = freq[i];
                solution_character = (char)i;
            }

        }

        System.out.println("solution frequency:" + solution_frequency);
        System.out.println("solution character: " + solution_character);
        if(solution_frequency != 0) {
            int success = 0;

            socketOut.writeShort(success);
            socketOut.flush();
            socketOut.writeShort(solution_frequency);

            socketOut.writeByte(solution_character);
            socketOut.flush();
        } else {
            System.out.println("Trimitem FAIL");
            int fail = 2;
            socketOut.writeShort(fail);
            String message = "No identical characters on the same positions!\n";
            int errorMessageSize = message.length();
            socketOut.writeUTF(String.valueOf(errorMessageSize));
            int l = 4;
            System.out.println(errorMessageSize);
//            socketOut.writeShort(errorMessageSize);

            socketOut.writeUTF(message);
            System.out.println("ccc");
        }

        c.close();
    }

    private static int readUnsignedShort(String message, BufferedReader reader) throws IOException {
        int unsignedShortNumber = 0;
        return 0;
    }



}