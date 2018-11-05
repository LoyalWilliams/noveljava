package novel.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.jsoup.select.Elements;

import novel.model.Chapter;
import novel.spider.interfaces.IChapterSpider;

public class ChapterCallable implements Callable<List<Chapter>> {
	private Elements elements;
	private IChapterSpider spider;
	private int offset;
	private int size;


	public ChapterCallable(Elements elements, IChapterSpider spider, int offset, int size) {
		super();
		this.elements = elements;
		this.spider = spider;
		this.offset = offset;
		this.size = size;
	}


	@Override
	public List<Chapter> call() throws Exception {
		List<Chapter> chapters=new ArrayList<Chapter>();
		for(int i=offset;i<offset+size;i++){
			Chapter e = spider.getChapterFromElement(elements.get(i));
			chapters.add(e);
		}
		return chapters;
	}

}