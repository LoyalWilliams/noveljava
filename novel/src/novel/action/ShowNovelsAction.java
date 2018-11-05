package novel.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import novel.base.Pager;
import novel.model.Novel;
import novel.service.NovelService;
import novel.util.EncryptUtils;
import novel.util.NovelUtils;
import novel.vo.EncryptedNovel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShowNovelsAction {
	@Resource
	private NovelService novelService;
	
	@RequestMapping("searchNovel.action")
	public  String searchNovel(HttpServletRequest request,String kw,int index){
		Pager pager = new Pager();
		pager.setOffset(index*30);
		pager.setPageSize(30);
		List<Novel> novels=novelService.searchNovelByNameAuthor(kw,pager);
		List<EncryptedNovel> encryptNovels = EncryptUtils.encryptNovels(novels);
		request.setAttribute("encryptNovels", encryptNovels);
		return "search";
	}
	


	@ResponseBody
	@RequestMapping("getNovel.action")
	public  List<EncryptedNovel> getNovel(int index){
		List<Novel> recommendNovels =null;
		Pager pager = new Pager();
		if(index==0){
			pager.setOffset(index);
			pager.setPageSize(8);
			recommendNovels = novelService.getRecommendNovels(pager);
		}else{
			pager.setOffset(8+(index-1)*4);
			pager.setPageSize(4);
			recommendNovels=novelService.getRecommendNovels(pager);
		}
		return EncryptUtils.encryptNovels(recommendNovels);
	}
	
//	首页推荐小说
	@ResponseBody
	@RequestMapping("getIndexRecommendNovels.action")
	public List<EncryptedNovel> getIndexRecommendNovels(int index){
		List<Novel> recommendNovels =null;
		Pager pager = new Pager();
		if(index==0){
			pager.setOffset(index);
			pager.setPageSize(8);
			recommendNovels = novelService.getRecommendNovels(pager);
		}else{
			pager.setOffset(8+(index-1)*4);
			pager.setPageSize(4);
			recommendNovels=novelService.getRecommendNovels(pager);
		}
		return EncryptUtils.encryptNovels(recommendNovels);
	}
	
//	热门小说推荐
	@ResponseBody
	@RequestMapping("getHotNovels.action")
	public List<EncryptedNovel> getHotNovels(int index){
		List<Novel> hotNovels =null;
		Pager pager = new Pager();
		if(index==0){
			pager.setOffset(index);
			pager.setPageSize(5);
			hotNovels = novelService.getHotNovels(pager);
		}else{
			pager.setOffset(5+(index-1)*4);
			pager.setPageSize(4);
			hotNovels=novelService.getHotNovels(pager);
		}
		return EncryptUtils.encryptNovels(hotNovels);
	}
	
//	粉丝推荐
	@ResponseBody
	@RequestMapping("getFanNovels.action")
	public List<EncryptedNovel> getFanNovels(int index){
		List<Novel> FanNovels =null;
		Pager pager = new Pager();
		if(index==0){
			pager.setOffset(index);
			pager.setPageSize(5);
			FanNovels = novelService.getFanNovels(pager);
		}else{
			pager.setOffset(5+(index-1)*4);
			pager.setPageSize(4);
			FanNovels=novelService.getFanNovels(pager);
		}
		return EncryptUtils.encryptNovels(FanNovels);
	}
	
//	男生推荐
	@ResponseBody
	@RequestMapping("getBoyNovels.action")
	public List<EncryptedNovel> getBoyNovels(int index){
		List<Novel> BoyNovels =null;
		Pager pager = new Pager();
		if(index==0){
			pager.setOffset(index);
			pager.setPageSize(5);
			BoyNovels = novelService.getBoyNovels(pager);
		}else{
			pager.setOffset(5+(index-1)*4);
			pager.setPageSize(4);
			BoyNovels=novelService.getBoyNovels(pager);
		}
		return EncryptUtils.encryptNovels(BoyNovels);
	}
//	女生推荐
	@ResponseBody
	@RequestMapping("getGirlNovels.action")
	public List<EncryptedNovel> getGirlNovels(int index){
		List<Novel> FanNovels =null;
		Pager pager = new Pager();
		if(index==0){
			pager.setOffset(index);
			pager.setPageSize(5);
			FanNovels = novelService.getGirlNovels(pager);
		}else{
			pager.setOffset(5+(index-1)*4);
			pager.setPageSize(4);
			FanNovels=novelService.getGirlNovels(pager);
		}
		return EncryptUtils.encryptNovels(FanNovels);
	}
//	免费小说看不停
	@ResponseBody
	@RequestMapping("getOtherNovels.action")
	public List<EncryptedNovel> getOtherNovels(int index){
		List<Novel> otherNovels =null;
		Pager pager = new Pager();
		if(index==0){
			pager.setOffset(index);
			pager.setPageSize(5);
			otherNovels = novelService.getOtherNovels(pager);
		}else{
			pager.setOffset(5+(index-1)*4);
			pager.setPageSize(4);
			otherNovels=novelService.getOtherNovels(pager);
		}
		return EncryptUtils.encryptNovels(otherNovels);
	}
	
	

}
