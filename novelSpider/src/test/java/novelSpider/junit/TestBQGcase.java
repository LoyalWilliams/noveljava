package novelSpider.junit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import novel.model.Chapter;
import novel.model.ChapterDetail;
import novel.model.Novel;
import novel.spider.NovelSiteEnum;
import novel.spider.impl.chapter.AbstractChapterDetailSpider;
import novel.spider.impl.chapter.AbstractChapterSpider;
import novel.spider.interfaces.INovelSpider;
import novel.spider.util.NovelSpiderFactory;
import novel.spider.util.NovelSpiderHttpGet;
import novel.spider.util.NovelSpiderUtil;
import novel.storage.Processor;
import novel.storage.impl.BxwxNovelStorageImpl;

public class TestBQGcase {
	
//	@Test
//	public void testGetSite(){
//		Map<String, String> contextMap = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl("https://www.bxwx9.org/"));
//		for (String k:contextMap.keySet()){
//			System.out.println(k+":"+contextMap.get(k));
//		}
//	}
//	@Test
//	public void testHttpGet(){
//		NovelSpiderHttpGet novelSpiderHttpGet = new NovelSpiderHttpGet();
//		String url="https://www.bxwx9.org/";
//		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//				 CloseableHttpResponse httpResponse = httpClient.execute(new NovelSpiderHttpGet(url))) {
//				String result = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
//				System.out.println(result);
//			} catch (Exception e) {
//				throw new RuntimeException(e);
//			}
//	}
//	@Test
//	public void testGetChapters(){
//		AbstractChapterSpider chapterSpider = new AbstractChapterSpider() {
//		};
//		List<Chapter> getsChapter = chapterSpider.getsChapter("https://www.bxwx9.org/b/21/21385/index.html");
//		for(Chapter chapter:getsChapter){
//			System.out.println(chapter.getTitle()+chapter.getUrl());
//			
//		}
//	}
//	@Test
//	public void testGetChapterDetails(){
////		https://www.bxwx9.org/b/21/21385/3798993.html
//		AbstractChapterDetailSpider abstractChapterDetailSpider = new AbstractChapterDetailSpider() {
//		};
//		ChapterDetail chapterDetail = abstractChapterDetailSpider.getChapterDetail("https://www.bxwx9.org/b/21/21385/3798993.html");
//		System.out.println(chapterDetail.getContent());
//	}
//	
//	@Test
//	public void testBxwxGetsNovel() {
//		INovelSpider spider = NovelSpiderFactory.getNovelSpider("https://www.bxwx9.org/binitialE/0/1.htm");
//		List<Novel> novels = spider.getsNovel("https://www.bxwx9.org/binitialE/0/1.htm", 10);
//		for (Novel novel : novels) {
//			System.out.println(novel);
//		}
//	}
//	@Test
//	public void testMyBaits() throws Exception{
//		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(new FileInputStream("conf/SqlMapConfig.xml"));
//		NovelMapper mapper = factory.openSession().getMapper(NovelMapper.class);
//		System.out.println(mapper.selectByPrimaryKey(1l));
//	}
//	
//	@Test
//	public void test(){
////		https://www.bxwx9.org/binitialA/0/1.htm
//		for(int i=0;i<=25;i++){
//			String letter=(char)(i+'A')+"";
//			
//			System.out.println("https://www.bxwx9.org/binitial"+letter+"/0/1.htm");
//		}
//		
//	}
//	@Test
//	public void testBxwxProcess() throws FileNotFoundException {
//		Processor processor = new BxwxNovelStorageImpl();
//		processor.process();
//	}
	

}
