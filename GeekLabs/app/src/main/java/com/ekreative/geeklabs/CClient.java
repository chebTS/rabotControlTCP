package com.ekreative.geeklabs;

/**
 * Created by cheb on 7/26/17.
 */


import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class CClient implements Runnable {
    private Socket socket;
    private String ServerIP = "192.168.1.1";
    private static final int ServerPort = 23;

    @Override
    public void run() {
        try {
            socket = new Socket(ServerIP, ServerPort);
        } catch (Exception e) {
            System.out.print("Whoops! It didn't work!:");
            System.out.print(e.getLocalizedMessage());
            System.out.print("\n");
        }
    }

    public void Send(String s) {
        try {
            PrintWriter outToServer = new PrintWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream()));
            outToServer.print(s + "\r");
            outToServer.flush();


        } catch (UnknownHostException e) {
            System.out.print(e.toString());
        } catch (IOException e) {
            System.out.print(e.toString());
        } catch (Exception e) {
            System.out.print(e.toString());
        }

    }
}