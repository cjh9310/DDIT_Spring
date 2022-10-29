package test2.java0;

import java.util.HashMap;

public class ParamMap extends HashMap<String, Object> {
	
	private ParamMap() {}
	public static ParamMap init() {
		return new ParamMap(); 
	}
	
	public String getString(String key) {
		Object object = this.get(key);
		if(object==null) {
			return null;
		}else {
			return String.valueOf(object);
		}
	}
	
	public Integer getInt(String key) {
		Object object = this.get(key);
		if(object==null) {
			return null;
		}else {
			return (Integer) object;
		}
	}
	
	public <T> T get(String key, Class<T> clazz) {
		Object object = this.get(key);
		if(object==null) {
			return null;
		}else {
			return (T) object;
		}
	}
	
	
}
