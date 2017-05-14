package com.qqserver.service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.qq.bean.TextMessage;
import com.qqserver.tool.MessageType;

public class SerConCliService implements Runnable {

	Socket socket = null;

	public SerConCliService(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			while (true) {

				ObjectInputStream ois = new ObjectInputStream(
						socket.getInputStream());
				TextMessage tm = (TextMessage) ois.readObject();
				// ×ª·¢
				if (tm.getMessageType().equals(MessageType.message)) {
					SerConCliService sccs = SocketMap.getSocket(tm
							.getReceiver());
					ObjectOutputStream oos = new ObjectOutputStream(
							sccs.socket.getOutputStream());
					oos.writeObject(tm);
				} else if (tm.getMessageType().equals(
						MessageType.OnlinefriendListMessage)) {
					String list = SocketMap.getOnLineFriendList().trim();
					String[] onlineUser = list.split(" ");
					for (int i = 0; i < onlineUser.length; i++) {
						tm.setMessage(list);
						tm.setReceiver(onlineUser[i]);
						SerConCliService sccs = SocketMap
								.getSocket(onlineUser[i]);
						ObjectOutputStream oos = new ObjectOutputStream(
								sccs.socket.getOutputStream());
						oos.writeObject(tm);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

	}

}
