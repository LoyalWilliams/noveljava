package novel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import novel.annotation.RedisAnontation;
import novel.annotation.RedisAnontation.SerialType;
import novel.base.Page;
import novel.base.Pager;
import novel.dao.NovelMapper;
import novel.model.Novel;
import novel.service.NovelService;
import novel.util.EncryptUtils;
import novel.vo.ChapterContent;
import novel.vo.EncryptedNovel;

import org.springframework.stereotype.Service;

@Service
public class NovelServiceImpl implements NovelService {
	@Resource
	private NovelMapper novelDao;
	
	@RedisAnontation(clazz=Novel.class,serialType=SerialType.OBJ)
	public Novel getOneNovel(Long id) {
		Novel novel = novelDao.selectByPrimaryKey(1l);
		return novel;
	}
	@RedisAnontation(clazz=EncryptedNovel.class,serialType=SerialType.LIST)
	@Override
	public List<EncryptedNovel> getRecommendNovels(Pager pager) {
		List<Novel> novels = novelDao.selectPageNovelsByRecommend(pager);
		return EncryptUtils.encryptNovels(novels);
	}
	@RedisAnontation(clazz=EncryptedNovel.class,serialType=SerialType.LIST)
	@Override
	public List<EncryptedNovel> getHotNovels(Pager pager) {
		List<Novel> novels = novelDao.selectPageNovelsByMonthRecommend(pager);
		return EncryptUtils.encryptNovels(novels);
	}
	@RedisAnontation(clazz=EncryptedNovel.class,serialType=SerialType.LIST)
	@Override
	public List<EncryptedNovel> getFanNovels(Pager pager) {
		List<Novel> novels = novelDao.selectPageNovelsByWeekRecommend(pager);
		return EncryptUtils.encryptNovels(novels);
	}
	@RedisAnontation(clazz=EncryptedNovel.class,serialType=SerialType.LIST)
	@Override
	public List<EncryptedNovel> getBoyNovels(Pager pager) {
		List<Novel> novels = novelDao.selectPageNovelsByBoyRecommend(pager);
		return EncryptUtils.encryptNovels(novels);
	}
	@RedisAnontation(clazz=EncryptedNovel.class,serialType=SerialType.LIST)
	@Override
	public List<EncryptedNovel> getGirlNovels(Pager pager) {
		List<Novel> novels = novelDao.selectPageNovelsByGirlRecommend(pager);
		return EncryptUtils.encryptNovels(novels);
	}
	@RedisAnontation(clazz=EncryptedNovel.class,serialType=SerialType.LIST)
	@Override
	public List<EncryptedNovel> getOtherNovels(Pager pager) {
		List<Novel> novels = novelDao.selectPageNovelsByOther(pager);
		return EncryptUtils.encryptNovels(novels);
	}
	@Override
	public Novel getNovelByUrl(String url) {
		return novelDao.selectByNovelUrl(url);
	}
	@Override
	public EncryptedNovel getNovelById(long key) {
		// TODO Auto-generated method stub
		return EncryptUtils.encryptNovel(novelDao.selectByPrimaryKey(key));
	}
	@Override
	public ChapterContent ChapterContent(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 奇怪
	 * 
	 */
	@RedisAnontation(clazz=Novel.class,serialType=SerialType.LIST)
	@Override
	public List<Novel> searchNovelByNameAuthor(String kw, Pager pager) {
		Page<String> page = new Page<String>();
		page.setT(kw);
		page.setPager(pager);
		return novelDao.selectPageNovelsByKeyWord(page);
	}



}
