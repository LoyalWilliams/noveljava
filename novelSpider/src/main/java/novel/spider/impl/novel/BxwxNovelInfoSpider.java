package novel.spider.impl.novel;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import novel.model.NovelInfo;
import novel.spider.util.NovelSpiderUtil;

public class BxwxNovelInfoSpider extends AbstractNovelInfoSpider{

	@Override
	public NovelInfo getNovelInfo(String url) {
		try {
			String result = super.crawl(url);
			Document doc = Jsoup.parse(result);
			doc.setBaseUri(url);
			String novelUrl=url;
			String img = doc.select(".picborder").attr("src");
			Elements elements = doc.getElementsByTag("table").get(2).getElementsByTag("table");
			Element table = elements.get(6);
			Element firstTr = table.getElementsByTag("tr").first();
			String name = elements.get(2).getElementsByTag("strong").text().replace("全集下载", "").trim();
			String chapterUrl=elements.get(3).getElementsByTag("a").first().absUrl("href");
			
			String type = firstTr.getElementsByTag("a").first().text();
			String author = firstTr.getElementsByTag("a").get(1).text();
			String status = table.getElementsByTag("tr").get(1).getElementsByTag("td").get(5).text();
			
			Element tr3 = table.getElementsByTag("tr").get(2);
			String collection = tr3.getElementsByTag("td").get(1).text();
			String length = tr3.getElementsByTag("td").get(3).text();
			String lastUpdateTime = tr3.getElementsByTag("td").get(5).text();
			
			Element tr4 = table.getElementsByTag("tr").get(3);
			String totalClick = tr4.getElementsByTag("td").get(1).text();
			String monthClick = tr4.getElementsByTag("td").get(3).text();
			String weekClick = tr4.getElementsByTag("td").get(5).text();
			
			Element tr5 = table.getElementsByTag("tr").get(4);
			String totalRecommend = tr5.getElementsByTag("td").get(1).text();
			String monthRecommend = tr5.getElementsByTag("td").get(3).text();
			String weekRecommend = tr5.getElementsByTag("td").get(5).text();
			
			String lastUpdateChapter=elements.get(7).getElementsByTag("a").get(1).text().trim();
			String introduction = elements.get(7).getElementsByTag("div").first().text().replace("wWw.bxwx9.org", "").replace("bxwx9.org", "").trim();
			NovelInfo novelInfo = new NovelInfo();
			novelInfo.set(name, author, img, Integer.parseInt(collection), Integer.parseInt(length), 
					Integer.parseInt(totalClick), 
					Integer.parseInt(monthClick), 
					Integer.parseInt(weekClick), 
					Integer.parseInt(totalRecommend),
					Integer.parseInt(monthRecommend), 
					Integer.parseInt(weekRecommend), 
					introduction, "", novelUrl, chapterUrl, 
					type, lastUpdateChapter, 
					NovelSpiderUtil.getDate(lastUpdateTime, "yy-MM-dd"), 
					NovelSpiderUtil.getNovelStatus(status));
			
//			.getElementsByTag("td").get(5).text();
			
//			System.out.println("NovelInfo [name="  + ", author=" + author + ", img=" + img + ", collection=" + collection
//					+ ", length=" + length + ", totalClick=" + totalClick + ", monthClick=" + monthClick + ", weekClick="
//					+ weekClick + ", totalRecommend=" + totalRecommend + ", monthRecommend=" + monthRecommend
//					+ ", weekRecommend=" + weekRecommend + ", introduction="  + ", comment=" 
//					+ ", novelUrl="  + ", chapterUrl="  + ", type=" + type + ", lastUpdateChapter="
//					 + ", lastUpdateTime=" + lastUpdateTime + ", status=" + status + "]"
//					 
//		);
//			System.out.println();
			return novelInfo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
