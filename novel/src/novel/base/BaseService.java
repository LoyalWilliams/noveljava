package novel.base;

import java.util.List;

public interface BaseService<T> {
	public T selectByPrimaryKey(int id);
	public void insert(T t);
	public void deleteByPrimaryKey(int id);
	public void updateByPrimaryKeySelective(T t);
	public List<T> selectPageQuery(Page<T> page);
	public int selectPageQueryTotalCount(Page<T> page);

}
