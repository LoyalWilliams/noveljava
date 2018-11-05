package novelSpider.junit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import novel.dao.NovelMapper;
import novel.model.Chapter;
import novel.model.ChapterDetail;
import novel.model.Novel;
import novel.model.NovelInfo;
import novel.spider.NovelSiteEnum;
import novel.spider.impl.chapter.AbstractChapterDetailSpider;
import novel.spider.impl.chapter.AbstractChapterSpider;
import novel.spider.impl.chapter.BxwxChapterSpider;
import novel.spider.interfaces.INovelInfoSpider;
import novel.spider.interfaces.INovelSpider;
import novel.spider.util.NovelSpiderFactory;
import novel.spider.util.NovelSpiderHttpGet;
import novel.spider.util.NovelSpiderUtil;
import novel.storage.Processor;
import novel.storage.impl.BxwxNovelStorageImpl;

public class TestBXWXcase {
	
	@Test
	public void testGetSite(){
		Map<String, String> contextMap = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl("https://www.bxwx9.org/"));
		for (String k:contextMap.keySet()){
			System.out.println(k+":"+contextMap.get(k));
		}
	}
	@Test
	public void testHttpGet(){
		NovelSpiderHttpGet novelSpiderHttpGet = new NovelSpiderHttpGet();
		String url="https://www.bxwx9.org/";
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
				 CloseableHttpResponse httpResponse = httpClient.execute(new NovelSpiderHttpGet(url))) {
				String result = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
				System.out.println(result);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	}
	@Test
	public void testGetChaptersThread() throws InterruptedException, ExecutionException{
		AbstractChapterSpider chapterSpider = new AbstractChapterSpider() {
		};
		String url="https://www.bxwx9.org/b/33/33732/index.html";
		Elements elements = chapterSpider.getChapterElements(url);
		elements.size();
		int taskSize=3;
		ExecutorService service = Executors.newFixedThreadPool(taskSize);
		List<Future<List<Chapter>>> futures0 = new ArrayList<>(taskSize);
		for(int i=0;i<taskSize;i++){
			if(i!=taskSize-1){
				ChapterCallable callable0 = new ChapterCallable(elements, chapterSpider, (elements.size()/taskSize)*i, (elements.size()/taskSize));
				Future<List<Chapter>> t1 = service.submit(callable0);
				futures0.add(t1);
			}else{
				ChapterCallable callable0 = new ChapterCallable(elements, chapterSpider, (elements.size()/taskSize)*i, elements.size()-(elements.size()/taskSize)*i);
				Future<List<Chapter>> t1 = service.submit(callable0);
				futures0.add(t1);
			}
				
		}
		service.shutdown();
		List<Chapter> chapters = new ArrayList<Chapter>();
		for (Future<List<Chapter>> future : futures0) {
			
			List<Chapter> list=null;
			try {
				list = future.get();
				chapters.addAll(list);
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Chapter chapter : list) {
				System.out.println(chapter);
				
			}
		}
		
	}
	@Test
	public void test2(){
		AbstractChapterSpider chapterSpider = new AbstractChapterSpider() {
		};
		String url="https://www.bxwx9.org/b/33/33732/index.html";
		Elements elements = chapterSpider.getChapterElements(url);
		for (Element element : elements) {
			Chapter chapter = chapterSpider.getChapterFromElement(element);
			System.out.println(chapter);
		}
	}
	
	@Test
	public void testGetChapters(){
		AbstractChapterSpider chapterSpider = new AbstractChapterSpider() {
		};
		List<Chapter> getsChapter = chapterSpider.getsChapter("https://www.bxwx9.org/b/33/33732/index.html");
		int cont=1;
		for(Chapter chapter:getsChapter){
			System.out.println(cont+""+chapter);
			cont++;
			
		}
	}
	@Test
	public void testGetChapters2(){
		AbstractChapterSpider chapterSpider = new AbstractChapterSpider() {
		};
		List<Chapter> getsChapter = chapterSpider.getsChapter("https://www.bxwx9.org/b/21/21385/index.html",0,20);
		int cont=1;
		for(Chapter chapter:getsChapter){
			System.out.println(cont+""+chapter);
			cont++;
			
		}
	}
	
	@Test
	public void testGetChapters3(){
		AbstractChapterSpider chapterSpider = new BxwxChapterSpider() {
		};
		List<Chapter> getsChapter = chapterSpider.getsChapter("https://www.bxwx9.org/b/21/21385/index.html",0,20);
		int cont=1;
		for(Chapter chapter:getsChapter){
			System.out.println(cont+""+chapter);
			cont++;
			
		}
	}
	
	@Test
	public void testGetChapters4(){
		AbstractChapterSpider chapterSpider = new BxwxChapterSpider() {
		};
		List<Chapter> getsChapter = chapterSpider.getsChapter("https://www.bxwx9.org/b/21/21385/index.html");
		int cont=1;
		for(Chapter chapter:getsChapter){
			System.out.println(cont+""+chapter);
			cont++;
			
		}
	}
	@Test
	public void testGetChapterDetails(){
//		https://www.bxwx9.org/b/21/21385/3798993.html
		AbstractChapterDetailSpider abstractChapterDetailSpider = new AbstractChapterDetailSpider() {
		};
		ChapterDetail chapterDetail = abstractChapterDetailSpider.getChapterDetail("https://www.bxwx9.org/b/21/21385/3798993.html");
		System.out.println(chapterDetail.getContent());
	}
	
	@Test
	public void testBxwxGetsNovel() {
		INovelSpider spider = NovelSpiderFactory.getNovelSpider("https://www.bxwx9.org/binitial1/0/1.htm");
		List<Novel> novels = spider.getsNovel("https://www.bxwx9.org/bsort/0/4.htm", 10);
		for (Novel novel : novels) {
			System.out.println(novel);
		}
	}
	@Test
	public void testMyBaits() throws Exception{
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(new FileInputStream("conf/SqlMapConfig.xml"));
		novel.dao.NovelMapper mapper = factory.openSession().getMapper(novel.dao.NovelMapper.class);
		novel.model.Novel novel = mapper.selectByPrimaryKey(1l);
		System.out.println(mapper.selectByPrimaryKey(1l));
	}
	
	@Test
	public void test(){
//		https://www.bxwx9.org/binitialA/0/1.htm
		for(int i=0;i<=25;i++){
			String letter=(char)(i+'A')+"";
			
			System.out.println("https://www.bxwx9.org/binitial"+letter+"/0/1.htm");
		}
		
	}
	@Test
	public void testBxwxProcess() throws FileNotFoundException {
		Processor processor = new BxwxNovelStorageImpl();
		processor.process();
	}
	@Test
	public void testBxwxNovelInfoSpider() throws Exception{
		INovelInfoSpider infoSpider = NovelSpiderFactory.getNovelInfoSpider("https://www.bxwx9.org/binfo/21");
		NovelInfo novelInfo = infoSpider.getNovelInfo("https://www.bxwx9.org/binfo/212/212313.htm");
		System.out.println(novelInfo);
	}
	
	@Test
	public void testBatchInsert() throws FileNotFoundException{
//		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(new FileInputStream("D:/code/webdevelop/conf/SqlMapConfig.xml"));
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(new FileInputStream("conf/SqlMapConfig.xml"));
		SqlSession session = sqlSessionFactory.openSession();
		INovelSpider spider = NovelSpiderFactory.getNovelSpider("https://www.bxwx9.org/binitial1/0/1.htm");
		List<Novel> novels = spider.getsNovel("https://www.bxwx9.org/bsort/0/4.htm", 10);
		session.getMapper(NovelMapper.class).batchInsert(novels);
		session.commit();
		session.close();

	}
	

}
