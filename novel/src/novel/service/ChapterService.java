package novel.service;

import java.util.List;

import novel.model.Chapter;
import novel.vo.EncryptedChapter;
import novel.vo.EncryptedChapterDetail;

public interface ChapterService {

	List<EncryptedChapter> getChapters(String url);

	List<Chapter> getChaptersgetsChapter(String url, int offset, int length);

	EncryptedChapterDetail getChapterDetail(String url);

}
