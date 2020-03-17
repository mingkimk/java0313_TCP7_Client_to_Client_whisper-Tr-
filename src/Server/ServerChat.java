package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class ServerChat extends Thread {

	private Socket withClient = null;
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;
	private String id = null;
	private ServerCenter sc = null;
	
	public String getMyId() {
		return id;
		
	}

	private ArrayList<Thread>tList= new ArrayList<>();
	// 왜 한지 모르겠지만, 선생님꺼에 있음(필요없는듯 한데 선생님꺼에 있어서 적은것)
	
	ServerChat(Socket c, ServerCenter s) {
		this.withClient = c;
		this.sc = s;
		// streamSet();
	}

	@Override
	public void run() {
		// ServerChat(Socket c){ 에 있는 streamSet();을 멀티스래드 만들려고 override 만들어서 돌리기
		streamSet();
		receive();
		// send();

	}

	private void receive() {
		// recevie() 만 별도로 쓰레드 처리
		new Thread(new Runnable() {

			@Override
			public void run() { // 독자적인 스레드 받는기능만
				// TODO Auto-generated method stub
				try {
					System.out.println("receive start!");
					while (true) {
						reMsg = withClient.getInputStream();
						byte[] reBuffer = new byte[100];
						reMsg.read(reBuffer);
						String msg = new String(reBuffer);
						msg = msg.trim();
						sc.reMsg(msg, id);
						//System.out.println("[" + id + "]" + msg);
					}
				} catch (Exception e) {
					System.out.println("receive End");
					return;
				}

			}
		}).start();

	}

	public void send(String reMsg) {
		// serverCenter에 reMsg를 보내야 하기 때문에 public과 reMsg 매개 변수
		// 단독적인 thread 가 필요럾어서 지워줌

		try {

			sendMsg = withClient.getOutputStream();
			sendMsg.write(reMsg.getBytes());
		

		} catch (Exception e) {
			System.out.println("send End");
			return;
		}
	}

	private void streamSet() {
		try {
			reMsg = withClient.getInputStream();
			byte[] reBuffer = new byte[100];
			reMsg.read(reBuffer);
			id = new String(reBuffer);
			id = id.trim(); // 트림 공백 제거

			InetAddress c_ip = withClient.getInetAddress();
			// withClient 라는 소캣으로 getInetAddress 을 받아서 c_ip에 저장
			String ip = c_ip.getHostAddress();
			// c_ip를 string으로 변경
			System.out.println(id + "님 로그인 하셨습니다");

			String reMsg = "정상접속 되었습니다";
			sendMsg = withClient.getOutputStream();
			sendMsg.write(reMsg.getBytes());

		} catch (IOException e) {
			
		}
	}
}
