package novel.action;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import novel.model.Chapter;
import novel.model.ChapterDetail;
import novel.model.Novel;
import novel.service.NovelService;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.util.NovelSpiderFactory;
import novel.spider.util.NovelSpiderUtil;
import novel.util.ChapterCallable;
import novel.util.EncryptUtils;
import novel.util.NovelUtils;
import novel.vo.EncryptedChapter;
import novel.vo.EncryptedChapterDetail;
import novel.vo.EncryptedNovel;

import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.select.Elements;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShowNovelDetailsAction {
	{
		NovelSpiderUtil.setConfPath("D:/code/webdevelop/conf/Spider-Rule.xml");
//	NovelSpiderUtil.setConfPath("/conf/novelSpider/Spider-Rule.xml");
	}
	@Resource
	private NovelService novelService;
	@Resource(name = "taskExecutor")
	private ThreadPoolTaskExecutor taskExecutor;

	@ResponseBody
	@RequestMapping("binfo.action")
//	展示某本小说的信息
	public EncryptedNovel showNovel(long key){
		Novel novel = novelService.getNovelById(key);
		return EncryptUtils.encryptNovel(novel);
	}
//	@ResponseBody
//	@RequestMapping(value="chapterList.action",method=RequestMethod.POST)
////	展示某本小说的章节列表
//	public List<EncryptedChapter> showNovelChapters(String encr, String keys){
//		ObjectMapper mapper = NovelUtils.mapper;
//		String url;
//		try {
//			Map<String,List<Integer>> keyMap = mapper.readValue(keys, Map.class);
//			String encrChapterUrl = EncryptUtils.decryptNovelUrl(encr,"chapterUrl",keyMap);
//			url = EncryptUtils.decrypt(encrChapterUrl);
//		} catch (IOException e) {
//			return new ArrayList<EncryptedChapter>();
//		}
//		IChapterSpider chapterSpider = NovelSpiderFactory.getChapterSpider(url);
//		Elements elements = chapterSpider.getChapterElements(url);
//		int taskSize=4;
//		ThreadPoolTaskExecutor service = taskExecutor;
//		List<Future<List<Chapter>>> futures0 = new ArrayList<>(taskSize);
//		for(int i=0;i<taskSize;i++){
//			if(i!=taskSize-1){
//				ChapterCallable callable0 = new ChapterCallable(elements, chapterSpider, (elements.size()/taskSize)*i, elements.size()/taskSize);
//				System.out.println( ((elements.size()/taskSize)*i)+":"+((elements.size()/taskSize)*(i+1)));
//				Future<List<Chapter>> t1 = service.submit(callable0);
//				futures0.add(t1);
//			}else{
//				ChapterCallable callable0 = new ChapterCallable(elements, chapterSpider, (elements.size()/taskSize)*i, elements.size()-(elements.size()/taskSize)*i);
//				System.out.println( ((elements.size()/taskSize)*i)+":"+(elements.size()-(elements.size()/taskSize)*i));
//				Future<List<Chapter>> t1 = service.submit(callable0);
//				futures0.add(t1);
//			}
//				
//		}
//		List<Chapter> chapters = new ArrayList<Chapter>();
//		for (Future<List<Chapter>> future : futures0) {
//			
//			List<Chapter> list=null;
//			try {
//				list = future.get();
//				chapters.addAll(list);
//			} catch (InterruptedException | ExecutionException e) {
//				e.printStackTrace();
//			}
//		}
//		Collections.sort(chapters, new Comparator<Chapter>() {
//			@Override
//			public int compare(Chapter o1, Chapter o2) {
//				String o1Url = o1.getUrl();
//				String o2Url = o2.getUrl();
//				String o1Index = o1Url.substring(o1Url.lastIndexOf('/') + 1, o1Url.lastIndexOf('.'));
//				String o2Index = o2Url.substring(o2Url.lastIndexOf('/') + 1, o2Url.lastIndexOf('.'));
//				return o1Index.compareTo(o2Index);
//			}
//		});
//		return EncryptUtils.encryptChapters(chapters);
//	}
	
	
	@ResponseBody
	@RequestMapping(value="chapterList.action",method=RequestMethod.POST)
//	展示某本小说的章节列表
	public List<EncryptedChapter> showNovelChapters(String encr, String keys){
		ObjectMapper mapper = NovelUtils.mapper;
		String url;
		try {
			Map<String,List<Integer>> keyMap = mapper.readValue(keys, Map.class);
			String encrChapterUrl = EncryptUtils.decryptNovelUrl(encr,"chapterUrl",keyMap);
			url = EncryptUtils.decrypt(encrChapterUrl);
		} catch (IOException e) {
			return new ArrayList<EncryptedChapter>();
		}
		IChapterSpider chapterSpider = NovelSpiderFactory.getChapterSpider(url);
		List<Chapter> chapters = chapterSpider.getsChapter(url);
		return EncryptUtils.encryptChapters(chapters);
	}
	
	@ResponseBody
	@RequestMapping(value="chapterIndexList.action",method=RequestMethod.POST)
//	展示某本小说的章节列表
	public List<EncryptedChapter> showNovelChapters(String encr, String keys,int offset,int length){
		ObjectMapper mapper = NovelUtils.mapper;
		String url;
		try {
			Map<String,List<Integer>> keyMap = mapper.readValue(keys, Map.class);
			String encrChapterUrl = EncryptUtils.decryptNovelUrl(encr,"chapterUrl",keyMap);
			url = EncryptUtils.decrypt(encrChapterUrl);
		} catch (IOException e) {
			return new ArrayList<EncryptedChapter>();
		}
		IChapterSpider chapterSpider = NovelSpiderFactory.getChapterSpider(url);
		List<Chapter> chapters = chapterSpider.getsChapter(url,offset,length);
		return EncryptUtils.encryptChapters(chapters);
	}
	
	@ResponseBody
	@RequestMapping("chapterContent.action")
//	展示某本小说某个章节的具体内容
	public EncryptedChapterDetail showChapterContent(String encr, String keys){
		ObjectMapper mapper = NovelUtils.mapper;
		String url;
		try {
			Map<String,List<Integer>> keyMap = mapper.readValue(keys, Map.class);
			String encrChapterUrl = EncryptUtils.decryptNovelUrl(encr,"chapterUrl",keyMap);
			url = EncryptUtils.decrypt(encrChapterUrl);
		} catch (IOException e) {
			return new EncryptedChapterDetail();
		}
		IChapterDetailSpider spider = NovelSpiderFactory.getChapterDetailSpider(url);
		ChapterDetail chapterDetail = spider.getChapterDetail(url);
		chapterDetail.setContent(chapterDetail.getContent().replace("\n", "<br/>"));
		EncryptedChapterDetail encryptChapterDetail = EncryptUtils.encryptChapterDetail(chapterDetail);
		return encryptChapterDetail;
	}
	
}
