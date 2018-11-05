package novel.dao;

import java.util.List;

import novel.model.Novel;

public interface NovelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Novel record);

    int insertSelective(Novel record);

    Novel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Novel record);

    int updateByPrimaryKey(Novel record);
    
    void batchInsert(List<Novel> novels);
}