package novel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import novel.base.Page;
import novel.base.Pager;
import novel.dao.NovelMapper;
import novel.model.Novel;
import novel.service.NovelService;
import novel.vo.ChapterContent;

import org.springframework.stereotype.Service;

@Service
public class NovelServiceImpl implements NovelService {
	@Resource
	private NovelMapper novelDao;
	@Override
	public Novel getOneNovel(Long id) {
		Novel novel = novelDao.selectByPrimaryKey(1l);
		return novel;
	}
	@Override
	public List<Novel> getRecommendNovels(Pager pager) {
		List<Novel> novels = novelDao.selectPageNovelsByRecommend(pager);
		return novels;
	}
	@Override
	public List<Novel> getHotNovels(Pager pager) {
		List<Novel> novels = novelDao.selectPageNovelsByMonthRecommend(pager);
		return novels;
	}
	@Override
	public List<Novel> getFanNovels(Pager pager) {
		List<Novel> novels = novelDao.selectPageNovelsByWeekRecommend(pager);
		return novels;
	}
	@Override
	public List<Novel> getBoyNovels(Pager pager) {
		List<Novel> novels = novelDao.selectPageNovelsByBoyRecommend(pager);
		return novels;
	}
	@Override
	public List<Novel> getGirlNovels(Pager pager) {
		List<Novel> novels = novelDao.selectPageNovelsByGirlRecommend(pager);
		return novels;
	}
	@Override
	public List<Novel> getOtherNovels(Pager pager) {
		List<Novel> novels = novelDao.selectPageNovelsByOther(pager);
		return novels;
	}
	@Override
	public Novel getNovelByUrl(String url) {
		
		return novelDao.selectByNovelUrl(url);
	}
	@Override
	public Novel getNovelById(long key) {
		// TODO Auto-generated method stub
		return novelDao.selectByPrimaryKey(key);
	}
	@Override
	public ChapterContent ChapterContent(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Novel> searchNovelByNameAuthor(String kw, Pager pager) {
		Page<String> page = new Page<String>();
		page.setT(kw);
		page.setPager(pager);
		return novelDao.selectPageNovelsByKeyWord(page);
	}



}
