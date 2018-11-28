package novel.spider.impl.chapter;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import novel.model.Chapter;
import novel.spider.NovelSiteEnum;
import novel.spider.impl.AbstractSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.util.NovelSpiderUtil;


public abstract class AbstractChapterSpider extends AbstractSpider implements IChapterSpider {
	@Override
	public List<Chapter> getChapters(String url) {
		try {
			String result = crawl(url);
			Document doc = Jsoup.parse(result);
			doc.setBaseUri(url);
			Elements as = doc.select(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("chapter-list-selector"));
			List<Chapter> chapters = new ArrayList<>();
			
			for (Element a : as) {
				Chapter chapter = new Chapter();
				chapter.setTitle(a.text());
				chapter.setUrl(a.absUrl("href"));
				chapters.add(chapter);
			}
			return chapters;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public List<Chapter> getChapters(String url, int offset, int length) {
		try {
			String result = crawl(url);
			Document doc = Jsoup.parse(result);
			doc.setBaseUri(url);
			Elements as = doc.select(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("chapter-list-selector"));
			int len = as.size();
			if(length<0){}
			else if(offset+length<=as.size() && offset+length>0){
				len=offset+length;
			}
			List<Chapter> chapters = new ArrayList<>();
			
			for (int i=offset;i<len;i++) {
				Chapter chapter = new Chapter();
				chapter.setTitle(as.get(i).text());
				chapter.setUrl(as.get(i).absUrl("href"));
				chapters.add(chapter);
			}
			return chapters;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Elements getChapterElements(String url) {
		try {
			String result = crawl(url);
			Document doc = Jsoup.parse(result);
			doc.setBaseUri(url);
			Elements as = doc.select(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("chapter-list-selector"));
			return as;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public Chapter getChapterFromElement(Element e) {
		Chapter chapter = new Chapter();
		chapter.setTitle(e.text());
		chapter.setUrl(e.absUrl("href"));
		return chapter;
	}
	
	@Override
	public List<Chapter> getChapterFromElements(Elements e,int offset,int length) {
		try {
			Elements as = e;
			List<Chapter> chapters = new ArrayList<>();
			
			for (int i=offset;i<offset+length;i++) {
				Chapter chapter = new Chapter();
				chapter.setTitle(as.get(i).text());
				chapter.setUrl(as.get(i).absUrl("href"));
				chapters.add(chapter);
			}
			return chapters;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}



}
