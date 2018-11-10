package novel.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import novel.model.ChapterDetail;

public class EncryptedChapterDetail  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ChapterDetail chapterDetail;
	private Map<String,List<Integer>> keys;
	public ChapterDetail getChapterDetail() {
		return chapterDetail;
	}
	public void setChapterDetail(ChapterDetail chapterDetail) {
		this.chapterDetail = chapterDetail;
	}
	public Map<String, List<Integer>> getKeys() {
		return keys;
	}
	public void setKeys(Map<String, List<Integer>> keys) {
		this.keys = keys;
	}
	@Override
	public String toString() {
		return "EncryptedChapterDetail [chapterDetail=" + chapterDetail
				+ ", keys=" + keys + "]";
	}
	

}
