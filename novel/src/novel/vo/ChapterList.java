package novel.vo;

import java.io.Serializable;
import java.util.List;

public class ChapterList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<EncryptedChapter> chapters; 
	private int total;
	public List<EncryptedChapter> getChapters() {
		return chapters;
	}
	public void setChapters(List<EncryptedChapter> chapters) {
		this.chapters = chapters;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "ChapterList [chapters=" + chapters + ", total=" + total + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chapters == null) ? 0 : chapters.hashCode());
		result = prime * result + total;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChapterList other = (ChapterList) obj;
		if (chapters == null) {
			if (other.chapters != null)
				return false;
		} else if (!chapters.equals(other.chapters))
			return false;
		if (total != other.total)
			return false;
		return true;
	}
	

}
