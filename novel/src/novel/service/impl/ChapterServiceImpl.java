package novel.service.impl;

import java.util.ArrayList;
import java.util.List;

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
import novel.vo.ChapterList;
import novel.vo.EncryptedChapter;
import novel.vo.EncryptedChapterDetail;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

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
		List<Chapter> chapters = chapterSpider.getChapters(url);
		return EncryptUtils.encryptChapters(chapters);
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

	@RedisAnontation(clazz=ChapterList.class,serialType=SerialType.OBJ)
	@Override
	public ChapterList getChapters(String url, int offset, int length) {
		IChapterSpider chapterSpider = NovelSpiderFactory.getChapterSpider(url);
		Elements elements = chapterSpider.getChapterElements(url);
		List<Chapter> chapters = chapterSpider.getChapterFromElements(elements, offset, length);
		int size = elements.size();
		chapters = chapterSpider.getChapterFromElements(elements, offset, length);
		ChapterList chapterList = new ChapterList();
		chapterList.setChapters(EncryptUtils.encryptChapters(chapters));
		chapterList.setTotal(size);
		return chapterList;
	}

	@RedisAnontation(clazz=Chapter.class,serialType=SerialType.LIST)
	@Override
	public List<Chapter> getChaptersByOffset(String url, int offset,
			int length) {
		IChapterSpider chapterSpider = NovelSpiderFactory.getChapterSpider(url);
		List<Chapter> chapters = chapterSpider.getChapters(url,offset,length);
		return chapters;
	}
}
