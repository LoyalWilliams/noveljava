package novel.spider.interfaces;

import java.util.List;

import novel.model.NovelInfo;

public interface INovelInfoSpider {
	/**
	 * 给我们一个完整的url，我们就给你返回小说信息
	 * @param url
	 * @return
	 */
	NovelInfo getNovelInfo(String url);
}
