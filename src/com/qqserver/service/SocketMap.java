package com.qqserver.service;

import java.util.HashMap;
import java.util.Map;

public class SocketMap {
  static Map<String,SerConCliService> sm=new HashMap<String,SerConCliService>();
 
  public static void addSocket(String Id,SerConCliService sccs){
	  sm.put(Id,sccs);
  }
  public static SerConCliService getSocket(String Id){
	  return sm.get(Id);
  }
  public static String getOnLineFriendList(){
	  String list="";
	  
	  for(int i=0;i<50;i++){
		  if(sm.containsKey(String.valueOf(i))){
			  list=list+" "+String.valueOf(i);
		  }
	  }	  
	  return list;
  }
}
