package novel.model;

import java.io.Serializable;
import java.util.Date;

public class Novel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

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

    private String lastUpdateChapterUrl;

    private Date lastUpdateTime;

    private Integer status;

    private String firstLetter;

    private Integer platformId;

    private Date addTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
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
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getNovelUrl() {
        return novelUrl;
    }

    public void setNovelUrl(String novelUrl) {
        this.novelUrl = novelUrl == null ? null : novelUrl.trim();
    }

    public String getChapterUrl() {
        return chapterUrl;
    }

    public void setChapterUrl(String chapterUrl) {
        this.chapterUrl = chapterUrl == null ? null : chapterUrl.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getLastUpdateChapter() {
        return lastUpdateChapter;
    }

    public void setLastUpdateChapter(String lastUpdateChapter) {
        this.lastUpdateChapter = lastUpdateChapter == null ? null : lastUpdateChapter.trim();
    }

    public String getLastUpdateChapterUrl() {
        return lastUpdateChapterUrl;
    }

    public void setLastUpdateChapterUrl(String lastUpdateChapterUrl) {
        this.lastUpdateChapterUrl = lastUpdateChapterUrl == null ? null : lastUpdateChapterUrl.trim();
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

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter == null ? null : firstLetter.trim();
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    

    @Override
	public String toString() {
		return "Novel [id=" + id + ", name=" + name + ", author=" + author
				+ ", img=" + img + ", collection=" + collection + ", length="
				+ length + ", totalClick=" + totalClick + ", monthClick="
				+ monthClick + ", weekClick=" + weekClick + ", totalRecommend="
				+ totalRecommend + ", monthRecommend=" + monthRecommend
				+ ", weekRecommend=" + weekRecommend + ", introduction="
				+ introduction + ", comment=" + comment + ", novelUrl="
				+ novelUrl + ", chapterUrl=" + chapterUrl + ", type=" + type
				+ ", lastUpdateChapter=" + lastUpdateChapter
				+ ", lastUpdateChapterUrl=" + lastUpdateChapterUrl
				+ ", lastUpdateTime=" + lastUpdateTime + ", status=" + status
				+ ", firstLetter=" + firstLetter + ", platformId=" + platformId
				+ ", addTime=" + addTime + "]";
	}

}