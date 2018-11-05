package novel.model;

import java.util.Date;

public class NovelInfo {
	private String name;

    private String author;

    private String img;

    private Integer collection;

    private Integer length;

    private Integer totalClick;

    private Integer monthClick;

    private Integer weekClick;

    private Integer totalRecommend;

    private Integer monthRecommend;

    private Integer weekRecommend;

    private String introduction;

    private String comment;

    private String novelUrl;

    private String chapterUrl;

    private String type;

    private String lastUpdateChapter;

    private Date lastUpdateTime;

    private Integer status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getCollection() {
		return collection;
	}

	public void setCollection(Integer collection) {
		this.collection = collection;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getTotalClick() {
		return totalClick;
	}

	public void setTotalClick(Integer totalClick) {
		this.totalClick = totalClick;
	}

	public Integer getMonthClick() {
		return monthClick;
	}

	public void setMonthClick(Integer monthClick) {
		this.monthClick = monthClick;
	}

	public Integer getWeekClick() {
		return weekClick;
	}

	public void setWeekClick(Integer weekClick) {
		this.weekClick = weekClick;
	}

	public Integer getTotalRecommend() {
		return totalRecommend;
	}

	public void setTotalRecommend(Integer totalRecommend) {
		this.totalRecommend = totalRecommend;
	}

	public Integer getMonthRecommend() {
		return monthRecommend;
	}

	public void setMonthRecommend(Integer monthRecommend) {
		this.monthRecommend = monthRecommend;
	}

	public Integer getWeekRecommend() {
		return weekRecommend;
	}

	public void setWeekRecommend(Integer weekRecommend) {
		this.weekRecommend = weekRecommend;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getNovelUrl() {
		return novelUrl;
	}

	public void setNovelUrl(String novelUrl) {
		this.novelUrl = novelUrl;
	}

	public String getChapterUrl() {
		return chapterUrl;
	}

	public void setChapterUrl(String chapterUrl) {
		this.chapterUrl = chapterUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLastUpdateChapter() {
		return lastUpdateChapter;
	}

	public void setLastUpdateChapter(String lastUpdateChapter) {
		this.lastUpdateChapter = lastUpdateChapter;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public void set(String name,
			String author,
			String img,
			Integer collection,
			Integer length,
			Integer totalClick,
			Integer monthClick,
			Integer weekClick,
			Integer totalRecommend,
			Integer monthRecommend,
			Integer weekRecommend,
			String introduction,
			String comment,
			String novelUrl,
			String chapterUrl,
			String type,
			String lastUpdateChapter,
			Date lastUpdateTime,
			Integer status){
		this.name                =name                 ;
		this.author              =author               ;
		this.img                 =img                  ;
		this. collection         = collection          ;
		this. length             = length              ;
		this. totalClick         = totalClick          ;
		this. monthClick         = monthClick          ;
		this. weekClick          = weekClick           ;
		this. totalRecommend     = totalRecommend      ;
		this. monthRecommend     = monthRecommend      ;
		this. weekRecommend      = weekRecommend       ;
		this.introduction        =introduction         ;
		this.comment             =comment              ;
		this.novelUrl            =novelUrl             ;
		this.chapterUrl          =chapterUrl           ;
		this.type                =type                 ;
		this.lastUpdateChapter   =lastUpdateChapter    ;
		this.lastUpdateTime      =lastUpdateTime       ;
		this.status              =status               ;
		
	}

	@Override
	public String toString() {
		return "NovelInfo [name=" + name + ", author=" + author + ", img=" + img + ", collection=" + collection
				+ ", length=" + length + ", totalClick=" + totalClick + ", monthClick=" + monthClick + ", weekClick="
				+ weekClick + ", totalRecommend=" + totalRecommend + ", monthRecommend=" + monthRecommend
				+ ", weekRecommend=" + weekRecommend + ", introduction=" + introduction + ", comment=" + comment
				+ ", novelUrl=" + novelUrl + ", chapterUrl=" + chapterUrl + ", type=" + type + ", lastUpdateChapter="
				+ lastUpdateChapter + ", lastUpdateTime=" + lastUpdateTime + ", status=" + status + "]";
	}

}
