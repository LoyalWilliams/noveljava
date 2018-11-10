package novel.service;

import java.util.List;

import novel.base.Pager;
import novel.model.Novel;
import novel.vo.ChapterContent;
import novel.vo.EncryptedNovel;

public interface NovelService {
	public Novel getOneNovel(Long id);
	public List<EncryptedNovel> getRecommendNovels(Pager pager);
	public List<EncryptedNovel> getHotNovels(Pager pager);
	public List<EncryptedNovel> getFanNovels(Pager pager);
	public List<EncryptedNovel> getBoyNovels(Pager pager);
	public List<EncryptedNovel> getGirlNovels(Pager pager);
	public List<EncryptedNovel> getOtherNovels(Pager pager);
	public Novel getNovelByUrl(String url);
	public EncryptedNovel getNovelById(long key);
	public ChapterContent ChapterContent(String key);
	public List<Novel> searchNovelByNameAuthor(String kw, Pager pager);
}
