package novel.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import novel.model.Chapter;
import novel.model.ChapterDetail;
import novel.model.Novel;
import novel.vo.EncryptedChapter;
import novel.vo.EncryptedChapterDetail;
import novel.vo.EncryptedNovel;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 对url加密的工具类
 * 
 * @author qq
 * 
 */
public class EncryptUtils {
	// 加密
	public static String encrypt(String key) {
		BASE64Encoder encoder = new BASE64Encoder();
		String encode = encoder.encode(key.getBytes());
		return encode;
	}

	// 解密
	public static String decrypt(String key) throws IOException {

		BASE64Decoder decoder = new BASE64Decoder();
		String decode = new String(decoder.decodeBuffer(key));
		return decode;

	}

	/**
	 * 根据list里面的序号给url加上=或者&
	 * 
	 * @param url
	 * @param endStr
	 * @param list
	 * @return
	 */
	public static String decryptNovelUrl(String url, String endStr,
			List<Integer> list) {
		String url2 = "";
		char[] urlArray = url.toCharArray();
		if (!list.isEmpty()) {
			int index = 0;
			int listIndex = 0;
			for (int i = 0; i < urlArray.length; i++) {
				if (index == list.get(listIndex)) {
					url2 += endStr;
					index++;
					listIndex++;
				}
				url2 += urlArray[i];
				index++;
			}
			if (url2.length() <= list.get(list.size() - 1)) {
				while (url2.length() <= list.get(list.size() - 1)) {
					url2 += endStr;
				}
			}
		} else {
			url2 = url;
		}
		return url2;
	}

	public static String decryptNovelUrl(String encryptUrl, String key,
			Map<String, List<Integer>> keys) {
		String novelUrld = decryptNovelUrl(encryptUrl, "=", keys.get(key + "2"));
		String novelUrl = decryptNovelUrl(novelUrld, "&", keys.get(key + "1"));
		return novelUrl;
	}

	public static Novel decryptNovel(EncryptedNovel encryptedNovel)
			throws IOException {
		Map<String, List<Integer>> keys = encryptedNovel.getKeys();
		Novel novel = encryptedNovel.getNovel();
		String novelUrl = decryptNovelUrl(novel.getNovelUrl(), "novelUrl", keys);
		novel.setNovelUrl(novelUrl);
		String chapterUrl = decryptNovelUrl(novel.getNovelUrl(), "chapterUrl",
				keys);
		novel.setChapterUrl(chapterUrl);
		String lastUpdateChapterUrl = decryptNovelUrl(novel.getNovelUrl(),
				"lastUpdateChapterUrl", keys);
		novel.setLastUpdateChapterUrl(lastUpdateChapterUrl);
		return novel;
	}

	public static List<Novel> decryptNovels(List<EncryptedNovel> encryptedNovels)
			throws IOException {
		ArrayList<Novel> list = new ArrayList<Novel>();
		for (int i = 0; i < encryptedNovels.size(); i++) {
			list.add(decryptNovel(encryptedNovels.get(i)));
		}
		return list;
	}

	public static List<Integer> getStrIndex(String str, char rp) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == rp)
				arrayList.add(i);
		}
		return arrayList;
	}

	public static String encryptUrl(String url) {
		String encrypt = encrypt(url);
		return null;
	}

	/**
	 * 给novel中含有url的字段都加密
	 * 
	 * @param novel
	 * @return
	 */
	public static EncryptedNovel encryptNovel(Novel novel) {
		EncryptedNovel encryptedNovel = new EncryptedNovel();
		HashMap<String, List<Integer>> hashMap = new HashMap<String, List<Integer>>();
		String encryptUrl = encryptString(hashMap, novel.getNovelUrl(),
				"novelUrl");
		novel.setNovelUrl(encryptUrl);
		encryptUrl = encryptString(hashMap, novel.getChapterUrl(), "chapterUrl");
		novel.setChapterUrl(encryptUrl);
		encryptUrl = encrypt(novel.getLastUpdateChapterUrl());
		encryptUrl = encryptString(hashMap, encryptUrl, "lastUpdateChapterUrl");
		novel.setLastUpdateChapterUrl(encryptUrl);

		encryptedNovel.setNovel(novel);
		encryptedNovel.setKeys(hashMap);

		return encryptedNovel;
	}

	public static EncryptedChapter encryptChapter(Chapter chapter) {
		EncryptedChapter encryptedChapter = new EncryptedChapter();
		HashMap<String, List<Integer>> hashMap = new HashMap<String, List<Integer>>();
		String encryptUrl = encryptString(hashMap, chapter.getUrl(),
				"chapterUrl");
		chapter.setUrl(encryptUrl);
		encryptedChapter.setChapter(chapter);
		encryptedChapter.setKeys(hashMap);
		return encryptedChapter;
	}

	public static List<EncryptedNovel> encryptNovels(List<Novel> novels) {
		ArrayList<EncryptedNovel> list = new ArrayList<EncryptedNovel>();
		for (int i = 0; i < novels.size(); i++) {
			list.add(encryptNovel(novels.get(i)));
		}
		return list;
	}

	public static List<EncryptedChapter> encryptChapters(List<Chapter> chapters) {
		ArrayList<EncryptedChapter> list = new ArrayList<EncryptedChapter>();
		for (int i = 0; i < chapters.size(); i++) {
			list.add(i, encryptChapter(chapters.get(i)));
		}
		return list;
	}

	public static String encryptString(HashMap<String, List<Integer>> hashMap,
			String str, String strKey) {
		String encryptStr = encrypt(str);
		List<Integer> aIndex = getStrIndex(encryptStr, '&');
		hashMap.put(strKey + "1", aIndex);
		encryptStr = encryptStr.replace("&", "");
		List<Integer> dIndex = getStrIndex(encryptStr, '=');
		encryptStr = encryptStr.replace("=", "");
		hashMap.put(strKey + "2", dIndex);
		return encryptStr;
	}

	public static EncryptedChapterDetail encryptChapterDetail(
			ChapterDetail chapterDetail) {
		EncryptedChapterDetail encryptedChapterDetail = new EncryptedChapterDetail();
		HashMap<String, List<Integer>> hashMap = new HashMap<String, List<Integer>>();
		String prev = chapterDetail.getPrev();
		String encPrev = encryptString(hashMap, prev, "prev");
		chapterDetail.setPrev(encPrev);
		String next = chapterDetail.getNext();
		String encNext = encryptString(hashMap, next, "next");
		chapterDetail.setNext(encNext);
		encryptedChapterDetail.setChapterDetail(chapterDetail);
		encryptedChapterDetail.setKeys(hashMap);
		return encryptedChapterDetail;
	}

	public static List<EncryptedChapterDetail> encryptChapterDetail(
			List<ChapterDetail> chapterDetails) {
		return null;
	}

	public static void main(String[] args) throws IOException {
		String json = "{\"novelUrl2\":[51],\"novelUrl1\":[],\"chapterUrl2\":[55],\"lastUpdateChapterUrl2\":[55],\"lastUpdateChapterUrl1\":[],\"chapterUrl1\":[]}";
		ObjectMapper mapper = new ObjectMapper();
		Map<String, List<Integer>> user = mapper.readValue(json, Map.class);
		System.out.println(user.get("novelUrl2").get(0));

		String key = encrypt("aHR0cHM6Ly93d3cuYnh3eDkub3JnL2IvNS81NzQwL2luZGV4Lmh0bWw=");
		String value = decrypt(key);
		// ?abcd=e=g?123=4?1234??
		ArrayList<Integer> list = new ArrayList<Integer>();
		// list.add(4);
		// list.add(6);
		list.add(0);
		list.add(9);
		list.add(15);
		list.add(20);
		list.add(21);
		// list.add(19);
		// "".
		// System.out.println(decryptNovelUrl("abcd=e=g123=41234","?",list));
	}

}
