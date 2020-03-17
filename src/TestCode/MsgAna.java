package TestCode;

import org.junit.Test;

public class MsgAna {
	
	@Test
	public void kkk() {
		String msg="/to kkk hi! good!!";
		//System.out.println(msg.indexOf(" "));
		//System.out.println(msg.indexOf(" ",4));
		//4번째 부터 공백까지  출력 하면 7 
		int firstInt = msg.indexOf(" ")+1;
		int endInt= msg.indexOf(" ",firstInt);
		String id = msg.substring(firstInt, endInt);
		System.out.println(id);
		//7을 string으로 바꾸면 kkk가 출력
		String idMsg=msg.substring(endInt+1);
		System.out.println(idMsg);
		// 메세지는 kkk 공백 다음 부터 출력 hi! good!!

	}

}
