package novel.base;

public class Pager {
	
	
	  
		private int pageSize=10;
	
		private int pageNum;
		private int offset=0;
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		public int getPageNum() {
			return pageNum;
		}
		public void setPageNum(int pageNum) {
			this.pageNum = pageNum;
		}
		public int getOffset() {
			return offset;
		}
		public void setOffset(int offset) {
			this.offset = offset;
		}
		@Override
		public String toString() {
			return "Pager [pageSize=" + pageSize + ", pageNum=" + pageNum
					+ ", offset=" + offset + "]";
		}
		
		
		
		
}
