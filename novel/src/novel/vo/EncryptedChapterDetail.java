package novel.vo;

import java.util.List;
import java.util.Map;

import novel.model.ChapterDetail;

public class EncryptedChapterDetail {
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
