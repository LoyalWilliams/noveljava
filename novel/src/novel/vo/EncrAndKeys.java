package novel.vo;

import java.util.List;
import java.util.Map;

public class EncrAndKeys {
	private String encr;
	private Map<String, List<Integer>> keys;
	public String getEncr() {
		return encr;
	}
	public void setEncr(String encr) {
		this.encr = encr;
	}
	public Map<String, List<Integer>> getKeys() {
		return keys;
	}
	public void setKeys(Map<String, List<Integer>> keys) {
		this.keys = keys;
	}
	@Override
	public String toString() {
		return "EncrAndKeys [encr=" + encr + ", keys=" + keys + "]";
	}
	
}
