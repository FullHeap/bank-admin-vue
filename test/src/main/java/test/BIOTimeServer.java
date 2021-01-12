package test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class BIOTimeServer {

    public static void main(String[] args){
        int port = 8080;
        if(args != null && args.length > 0){
            try{
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                throw new RuntimeException("Input params must be String of Integer");
            }
        }

        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("The time server is starting in port :" + port);
            Socket socket = null;
            TimeServerHandlerExecutePool timeServerHandlerExecutePool = new TimeServerHandlerExecutePool(50,1000);
            while (true){
                socket = serverSocket.accept();
                timeServerHandlerExecutePool.excute(new TimeServerHandler(socket));
            }
        }catch (IOException e){
            throw new RuntimeException("serverSocket IOException");
        }finally {
            if(serverSocket != null){
                System.out.println("The time server be closed");
                try {
                    serverSocket.close();
                } catch (IOException e) {
                }
                serverSocket = null;
            }
        }
    }
}

class TimeServerHandlerExecutePool{
    private ExecutorService executorService;

    public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize){
        executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),maxPoolSize,
                120L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public void excute(Runnable task){
        executorService.execute(task);
    }
}

class TimeServerHandler implements Runnable{
    private Socket socket = null;

    public TimeServerHandler(Socket socket){
        this.socket = socket;
    }
    
    public void run() {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
            char[] chars = new char[1024];
            bufferedReader.read(chars);
            String req = String.valueOf(chars);
            System.out.println("The time server receive order : " + req+"11111");
            System.out.println("1"+req.toLowerCase()+"1");
            
            if(req.indexOf("QUERY")!=-1) {
            	System.out.println(true);
            }
            
            	String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(req.trim()) ?
                        new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                        bufferedWriter.write(currentTime);
                        bufferedWriter.flush();
            
           
        }catch (IOException e){
            if(bufferedReader != null){
                try {
                    bufferedReader.close();
                    bufferedReader = null;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if(bufferedWriter != null){
                try {
                    bufferedWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                bufferedWriter = null;
            }

            if(socket != null){
                try {
                    socket.close();
                    socket = null;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
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
                try {
                    bufferedWriter.close();
                    bufferedWriter = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }

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
