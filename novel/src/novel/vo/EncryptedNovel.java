package novel.vo;

import java.util.List;
import java.util.Map;

import novel.model.Novel;

public class EncryptedNovel {
	private Novel novel;
	private Map<String,List<Integer>> keys;
	public Novel getNovel() {
		return novel;
	}
	public void setNovel(Novel novel) {
		this.novel = novel;
	}
	public Map<String, List<Integer>> getKeys() {
		return keys;
	}
	public void setKeys(Map<String, List<Integer>> keys) {
		this.keys = keys;
	}
	@Override
	public String toString() {
		return "EncryptedNovel [novel=" + novel + ", keys=" + keys + "]";
	}
	
	
	
}
