package novel.spider.util;

import novel.spider.NovelSiteEnum;
import novel.spider.impl.chapter.BxwxChapterSpider;
import novel.spider.impl.chapter.DefaultChapterDetailSpider;
import novel.spider.impl.novel.BxwxNovelInfoSpider;
import novel.spider.impl.novel.BxwxNovelSpider;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.interfaces.INovelInfoSpider;
import novel.spider.interfaces.INovelSpider;

/**
 * 生产书籍列表的实现类
 */
public final class NovelSpiderFactory {
	private NovelSpiderFactory() {}
	public static BxwxNovelSpider bxwxNovelSpider;
	public static BxwxNovelInfoSpider bxwxNovelInfoSpider;
	public static BxwxChapterSpider bxwxChapterSpider;
	public static DefaultChapterDetailSpider defaultChapterDetailSpider;
	
	public static INovelSpider getNovelSpider(String url) {
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		switch (novelSiteEnum) {
		case Bxwx : return bxwxNovelSpider==null?new BxwxNovelSpider():bxwxNovelSpider;
		default : throw new RuntimeException(url + "暂时不被支持");
		}
	}
	public static INovelInfoSpider getNovelInfoSpider(String url) {
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		switch (novelSiteEnum) {
		case Bxwx : return bxwxNovelInfoSpider==null?new BxwxNovelInfoSpider():bxwxNovelInfoSpider;
		default : throw new RuntimeException(url + "暂时不被支持");
		}
	}
	public static IChapterSpider getChapterSpider(String url) {
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		switch (novelSiteEnum) {
		case Bxwx : return bxwxChapterSpider==null?new BxwxChapterSpider():bxwxChapterSpider;
		default : throw new RuntimeException(url + "暂时不被支持");
		}
	}
	public static IChapterDetailSpider getChapterDetailSpider(String url) {
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		switch (novelSiteEnum) {
		case Bxwx : return defaultChapterDetailSpider==null?new DefaultChapterDetailSpider():defaultChapterDetailSpider;
		default : throw new RuntimeException(url + "暂时不被支持");
		}
	}
}
