package com.qqserver.service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.qq.bean.UserMessage;

public class server {

	public static void main(String[] args)  {
		server server=new server();
	}
   
	public server(){

		try {			
			
			ServerSocket ss = new ServerSocket(8856);			
           
			while(true){	
             Socket s=ss.accept();
            
             ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
             UserMessage um=(UserMessage) ois.readObject();
             ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
             
             if(um.getPw().equals("123456"))
             { 
            	 um.setHasUser(true);
            	 oos.writeObject(um);
				 SerConCliService sccs=new SerConCliService(s);
				 SocketMap.addSocket(um.getUserId(), sccs);
	             Thread t=new Thread(sccs);
	             t.start();
              }else
              {
            	  oos.writeObject(um);
            	  s.close();
              }
           }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
   }
}
