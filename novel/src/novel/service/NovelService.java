package novel.service;

import java.util.List;

import novel.base.Pager;
import novel.model.Novel;
import novel.vo.ChapterContent;

public interface NovelService {
	public Novel getOneNovel(Long id);
	public List<Novel> getRecommendNovels(Pager pager);
	public List<Novel> getHotNovels(Pager pager);
	public List<Novel> getFanNovels(Pager pager);
	public List<Novel> getBoyNovels(Pager pager);
	public List<Novel> getGirlNovels(Pager pager);
	public List<Novel> getOtherNovels(Pager pager);
	public Novel getNovelByUrl(String url);
	public Novel getNovelById(long key);
	public ChapterContent ChapterContent(String key);
	public List<Novel> searchNovelByNameAuthor(String kw, Pager pager);
}
