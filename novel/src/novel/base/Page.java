package novel.base;

import java.util.List;

/**
 * 
 * 使用泛型来定义分页的对象，Page<Publicmessage>
 * Page<News>
 * 分页对象中包含了泛型对象
 * 分页工具对象，
 * 查询的结果集，
 * 总条数.
 *
 * 
 */
public class Page<T> {
	//
	private T t;
    private Pager pager;

	private List<T> list;
	private int totalRows;
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}
	@Override
	public String toString() {
		return "Page [t=" + t + ", pager=" + pager + ", list=" + list
				+ ", totalRows=" + totalRows + "]";
	}

	
	
	

}
