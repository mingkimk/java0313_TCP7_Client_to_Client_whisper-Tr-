package Server;
import java.util.ArrayList;
public class ServerCenter {
	private ArrayList<ServerChat> sList = new ArrayList<>();
	public void addServerChat(ServerChat s) {
		this.sList.add(s);

	}

	public void reMsg(String msg, String id) {
		// mes type
		// /to kkk hi~ good!
		// /to+id+메세지 내용을 입력 하면  해당 id 한테만 메세지가 전달 된다.
		if (msg.indexOf("/to") == 0) {
			// /to 만 입력 하면 서버에 recive end 라고 뜨면서  해당 client 종료
			int firstInt = msg.indexOf(" ") + 1; 
			//indexOf는 문자열 인덱스 반환 하는거, 메세지 공백 다음의 첫번째 문자열 인덱스를 반환  firstInt
			int endInt = msg.indexOf(" ", firstInt);
			//firstInt에서 부터 공백이 있는데가  endInt 
			String targetID = msg.substring(firstInt, endInt);
			// subString return the fisrtInt till endInt.
			//indexOf로 firstInt 와 endInt의 인덱스 넘버를 찾으면 substring으로 return
			String targetMsg = "[@" + id + "]" + msg.substring(endInt + 1);
			//귓속말 받는 사람한테 @랑 아디디 같이 오면 @로 상대방이 귓속말 보냈구나 알수있음
			sendOne(targetID, targetMsg);
		} else {
	
		sendAll("[" + id + "]" + msg);
	}

	}
	public void sendOne(String targetID,String targetMsg) {
		for (int i = 0; i < sList.size(); i++) {
			if (targetID.equals(sList.get(i).getMyId())) {
				sList.get(i).send(targetMsg);
			} 
		}
	}

	public void sendAll(String msg) {
		for (int i = 0; i < sList.size(); i++) {
			sList.get(i).send(msg);
		}
	}
}
