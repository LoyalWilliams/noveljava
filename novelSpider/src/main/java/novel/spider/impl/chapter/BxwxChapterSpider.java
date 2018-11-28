package novel.spider.impl.chapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jsoup.select.Elements;

import novel.model.Chapter;
import novel.spider.NovelSiteEnum;
import novel.spider.util.NovelSpiderUtil;

public class BxwxChapterSpider extends AbstractChapterSpider {
	public List<Chapter> getChapters(String url) {
		List<Chapter> chapters = super.getChapters(url);
		Collections.sort(chapters, new Comparator<Chapter>() {
			@Override
			public int compare(Chapter o1, Chapter o2) {
				String o1Url = o1.getUrl();
				String o2Url = o2.getUrl();
				String o1Index = o1Url.substring(o1Url.lastIndexOf('/') + 1, o1Url.lastIndexOf('.'));
				String o2Index = o2Url.substring(o2Url.lastIndexOf('/') + 1, o2Url.lastIndexOf('.'));
				return o1Index.compareTo(o2Index);
			}
		});
		return chapters;
	}

	@Override
	public List<Chapter> getChapters(String url, int offset, int length) {
		List<Chapter> chapters = super.getChapters(url, 0, length);
		Collections.sort(chapters, new Comparator<Chapter>() {
			@Override
			public int compare(Chapter o1, Chapter o2) {
				String o1Url = o1.getUrl();
				String o2Url = o2.getUrl();
				String o1Index = o1Url.substring(o1Url.lastIndexOf('/') + 1, o1Url.lastIndexOf('.'));
				String o2Index = o2Url.substring(o2Url.lastIndexOf('/') + 1, o2Url.lastIndexOf('.'));
				return o1Index.compareTo(o2Index);
			}
		});
		List<Chapter> list = new ArrayList<Chapter>();
		for(int i=offset;i<offset+length;i++){
			list.add(chapters.get(i));
		}
		return list;
	}
	
	@Override
	public List<Chapter> getChapterFromElements(Elements e,int offset,int length) {
		try {
			Elements as = e;
			int len = as.size();
			if(length<0){}
			else if(offset+length+8<=as.size() && offset+length+8>0){
				len=offset+length+8;
			}
			List<Chapter> chapters = new ArrayList<>();
			
			for (int i=0;i<len;i++) {
				Chapter chapter = new Chapter();
				chapter.setTitle(as.get(i).text());
				chapter.setUrl(as.get(i).absUrl("href"));
				chapters.add(chapter);
			}
			Collections.sort(chapters, new Comparator<Chapter>() {
				@Override
				public int compare(Chapter o1, Chapter o2) {
					String o1Url = o1.getUrl();
					String o2Url = o2.getUrl();
					String o1Index = o1Url.substring(o1Url.lastIndexOf('/') + 1, o1Url.lastIndexOf('.'));
					String o2Index = o2Url.substring(o2Url.lastIndexOf('/') + 1, o2Url.lastIndexOf('.'));
					return o1Index.compareTo(o2Index);
				}
			});
			List<Chapter> list = new ArrayList<Chapter>();
//			System.out.println(chapters.size());
//			chapters=chapters.subList(offset, offset+length);
//			for (Chapter chapter : chapters) {
//				list.add(chapter);
//			}
			len=offset+length>as.size()?as.size():offset+length;
			for(int i=offset;i<len;i++){
				list.add(chapters.get(i));
			}
			return list;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

}
