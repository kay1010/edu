package com.yuxiu.edu.web.controller;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    private Socket socket;

    public SocketClient(){
        try {
            socket = new Socket("localhost", 8088);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(){
        final  Socket socket1=this.socket;
       new Thread(new Runnable() {//创建一条线程，监听获取服务端发送的消息
            @Override
            public void run() {
                try {
                    InputStream in = socket1.getInputStream();
                    InputStreamReader isr = new InputStreamReader(in, "UTF-8");
                    BufferedReader br = new BufferedReader(isr);
                    while(true){
                        System.out.println("服务端说：" + in.read(new byte[1024]));
                    }
                }catch (Exception e){
                }
            }
        }).start();

        try{//向服务端发送消息
            OutputStream out = socket.getOutputStream();

           /* OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
            PrintWriter pw = new PrintWriter(osw, true);*/
            //创建Scanner读取用户输入内容
            Scanner scanner = new Scanner(System.in);
            while(true){
                out.write(scanner.nextLine().getBytes());
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(socket != null){
                try{
                    socket.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SocketClient client = new SocketClient();
        client.start();
    }

}
