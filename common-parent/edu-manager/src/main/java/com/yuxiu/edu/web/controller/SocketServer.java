package com.yuxiu.edu.web.controller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SocketServer {
    private ServerSocket serverSocket;
    private final Map socketMap=new HashMap();
    private final Map clientSizeMap=new HashMap();


    public SocketServer(){
        try{
            serverSocket = new ServerSocket(8088);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void start(){


        try{
            Socket socket = null;

            new Thread(new Runnable() {//创建一条线程，向客户端发送消息
                @Override
                public void run() {
                    Scanner scanner = new Scanner(System.in);
                    while(true){
                        String demo=scanner.nextLine();
                        String[] demoArray=demo.split(":");
                        String socketIp=demoArray[0];
                        String info=demoArray[1];

                      /*  System.out.println("服务端输入的ip："+socketIp);
                        System.out.println("服务端发送的消息:"+info);*/
                        Socket socket=(Socket)socketMap.get(socketIp);
                        try{
                            OutputStream out = socket.getOutputStream();
                            //OutputStreamWriter osw = new OutputStreamWriter(out, true);
                            /*OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
                            PrintWriter pw = new PrintWriter(osw, true);
                            pw.println(info);*/
                            out.write(info.getBytes());

                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }

                }
            }).start();

            //serverSocket.accept() 方法会产生阻塞，直到某个Socket连接，返回请求连接的Socket
            while ((socket = serverSocket.accept())!=null){//每当有新的客户端连接到服务器，便创建一条线程，获取连接到服务端的客户端，并将之socket保存在Map集合中
                final  Socket socket1 = socket;
                socketMap.put(socket1.getInetAddress().getHostAddress(),socket1);
                System.out.println("===========================================");
                System.out.println("有新的连接，新连接的客户端Ip："+socket1.getInetAddress().getHostAddress());
                System.out.println("当前连接的客户端总数："+socketMap.size()+"条");
                Integer clientsize=socketMap.size();
                clientSizeMap.put(socket1.getInetAddress().getHostAddress(),clientsize);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("客户端已连接！");
                        System.out.println("===========================================");
                        try {
                            Integer number =(Integer) clientSizeMap.get(socket1.getInetAddress().getHostAddress());
                            InputStream in = socket1.getInputStream();
                            InputStreamReader isr = new InputStreamReader(in, "UTF-8");
                            BufferedReader br = new BufferedReader(isr);
                            //不断读取客户端数据
                            while(true){
                                byte[] msg=new byte[1024];
                                in.read(msg);
                                System.out.println("客户端"+number+"说：" +new String(msg));
                                /*OutputStream outputStream = socket1.getOutputStream();
                                OutputStreamWriter osw = new OutputStreamWriter(outputStream, "UTF-8");
                                PrintWriter pw = new PrintWriter(osw, true);
                                pw.println("你好客户端,已经收到了你的消息");*/
                            }
                        }catch (Exception e){
                        }

                    }
                }).start();
            }


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SocketServer server = new SocketServer();
        server.start();
    }
}

