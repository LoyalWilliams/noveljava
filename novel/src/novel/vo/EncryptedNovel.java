package novel.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import novel.model.Novel;

public class EncryptedNovel  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
