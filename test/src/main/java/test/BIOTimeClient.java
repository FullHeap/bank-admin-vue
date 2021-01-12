package test;

import java.io.*;
import java.net.Socket;

public class BIOTimeClient {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if(args != null && args.length > 0){
            try{
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){

            }
        }

        Socket socket = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try{
            socket = new Socket("127.0.0.1", port);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
            bufferedWriter.write("QUERY TIME ORDER");
            bufferedWriter.flush();
            System.out.println("Send order 2 server succeed.");
            char[] chars = new char[1024];
            bufferedReader.read(chars);
            System.out.print("Now is : " + String.valueOf(chars));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(bufferedReader != null){
                try {
                    bufferedReader.close();
                    bufferedReader = null;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if(bufferedWriter != null){
                bufferedWriter.close();
            }

            if(socket != null){
                try {
                    socket.close();
                    socket = null;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        }

    }
}