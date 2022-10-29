package test2.java0;

public class Test03 {
	public static void main(String[] args) {
		
		// Map 활용 ------------------------> 	// 상속
		// 키는 String                       		// key 제한 -> Generics 
		ParamMap param = ParamMap.init();   	// init() 으로 만들어야 -> static, new ParamMap()
		param.put("test", "value");
		String data = param.getString("test");
		
		param.put("key1", 10);
		int data2 = param.getInt("key1");
		
		StringBuffer sb = new StringBuffer();
		sb.append("test'test");
		param.put("key2", sb);
		StringBuffer sb2 = param.get("key2", StringBuffer.class);  // 동적 반환타입
	}
}
