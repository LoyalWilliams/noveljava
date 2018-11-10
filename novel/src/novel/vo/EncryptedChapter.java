package novel.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import novel.model.Chapter;

public class EncryptedChapter implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Chapter chapter;
	private Map<String,List<Integer>> keys;
	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	public Map<String, List<Integer>> getKeys() {
		return keys;
	}
	public void setKeys(Map<String, List<Integer>> keys) {
		this.keys = keys;
	}
	@Override
	public String toString() {
		return "EncryptedChapter [chapter=" + chapter + ", keys=" + keys + "]";
	}

}
