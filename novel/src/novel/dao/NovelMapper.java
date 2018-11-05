package novel.dao;

import java.util.List;

import novel.base.Page;
import novel.base.Pager;
import novel.model.Novel;

public interface NovelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Novel record);

    int insertSelective(Novel record);

    Novel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Novel record);

    int updateByPrimaryKey(Novel record);

	List<Novel> selectPageNovelsByRecommend(Pager pager);

	List<Novel> selectPageNovelsByMonthRecommend(Pager pager);

	List<Novel> selectPageNovelsByWeekRecommend(Pager pager);

	List<Novel> selectPageNovelsByBoyRecommend(Pager pager);

	List<Novel> selectPageNovelsByGirlRecommend(Pager pager);

	List<Novel> selectPageNovelsByOther(Pager pager);

	Novel selectByNovelUrl(String url);

	List<Novel> selectPageNovelsByKeyWord(Page<String> page);
}