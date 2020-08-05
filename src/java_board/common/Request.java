package java_board.common;

import java.util.HashMap;
import java.util.Map;

public class Request {

	private Map<String, Object> parameters;
	private Map<String, Object> attributes;
	
	public Request() {
		parameters = new HashMap<>();
		attributes = new HashMap<>();
	}
	
	public void setParameters(String key, Object value) {
		parameters.put(key, value);
	}
	
	public Object getParameters(String key) {
		return parameters.get(key);
	}
	
	public void setAttribute(String key, Object value) {
		attributes.put(key, value);
	}
	
	public Object getAttribute(String key) {
		return attributes.get(key);
	}
}