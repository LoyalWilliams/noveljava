package novel.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import novel.annotation.RedisAnontation;
import novel.annotation.RedisAnontation.SerialType;
import novel.model.Chapter;
import novel.model.ChapterDetail;
import novel.service.ChapterService;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.util.NovelSpiderFactory;
import novel.spider.util.NovelSpiderUtil;
import novel.util.EncryptUtils;
import novel.vo.EncryptedChapter;
import novel.vo.EncryptedChapterDetail;

@Service
public class ChapterServiceImpl implements ChapterService {
	{
		NovelSpiderUtil.setConfPath("D:/code/webdevelop/conf/Spider-Rule.xml");
//	NovelSpiderUtil.setConfPath("/conf/novelSpider/Spider-Rule.xml");
	}

	@RedisAnontation(clazz=EncryptedChapter.class,serialType=SerialType.LIST)
	@Override
	public List<EncryptedChapter> getChapters(String url) {
		IChapterSpider chapterSpider = NovelSpiderFactory.getChapterSpider(url);
		List<Chapter> chapters = chapterSpider.getsChapter(url);
		return EncryptUtils.encryptChapters(chapters);
	}

	@RedisAnontation(clazz=EncryptedChapter.class,serialType=SerialType.LIST)
	@Override
	public List<Chapter> getChaptersgetsChapter(String url, int offset,
			int length) {
		IChapterSpider chapterSpider = NovelSpiderFactory.getChapterSpider(url);
		List<Chapter> chapters = chapterSpider.getsChapter(url,offset,length);
		return chapters;
	}

	@RedisAnontation(clazz=EncryptedChapterDetail.class,serialType=SerialType.OBJ)
	@Override
	public EncryptedChapterDetail getChapterDetail(String url) {
		IChapterDetailSpider spider = NovelSpiderFactory.getChapterDetailSpider(url);
		ChapterDetail chapterDetail = spider.getChapterDetail(url);
		chapterDetail.setContent(chapterDetail.getContent().replace("\n", "<br/>"));
		EncryptedChapterDetail encryptChapterDetail = EncryptUtils.encryptChapterDetail(chapterDetail);
		return encryptChapterDetail;
	}

}
