package novel.spider.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import novel.spider.NovelSiteEnum;

public final class NovelSpiderUtil {
	private static final Map<NovelSiteEnum, Map<String, String>> CONTEXT_MAP = new HashMap<>();
	private static  NovelSiteEnum novelSiteEnum;
	private static String confPath="conf/Spider-Rule.xml";
	
	public static String getConfPath() {
		return confPath;
	}

	public static void setConfPath(String confPath) {
		NovelSpiderUtil.confPath = confPath;
	}
	

	public static NovelSiteEnum getNovelSiteEnum() {
		return novelSiteEnum;
	}

	public static void setNovelSiteEnum(NovelSiteEnum novelSiteEnum) {
		NovelSpiderUtil.novelSiteEnum = novelSiteEnum;
	}

	private NovelSpiderUtil() {}
	
	@SuppressWarnings("unchecked")
	private static void init() {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new File(confPath));
//			Document doc = reader.read(new File("D:/code/webdevelop/conf/Spider-Rule.xml"));
//			Document doc = reader.read(new File("/home/williams/novel/conf/Spider-Rule.xml"));
			Element root = doc.getRootElement();
			List<Element> sites = root.elements("site");
			for (Element site : sites) {
				List<Element> subs = site.elements();
				Map<String, String> subMap = new HashMap<>();
				for (Element sub : subs) {
					String name = sub.getName();
					String text = sub.getTextTrim();
					subMap.put(name, text);
				}
				CONTEXT_MAP.put(NovelSiteEnum.getEnumByUrl(subMap.get("url")), subMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 拿到对应网站的解析规则
	 */
	public static Map<String, String> getContext(NovelSiteEnum novelSiteEnum) {
		if(CONTEXT_MAP.isEmpty()){
			init();
		}
		return CONTEXT_MAP.get(novelSiteEnum);
	}
	
	/**
	 * 拿到对应网站的解析规则
	 */
	public static Map<String, String> getContext() {
		if(CONTEXT_MAP.isEmpty()){
			init();
		}
		return CONTEXT_MAP.get(novelSiteEnum);
	}
	
	/**
	 * 多个文件合并为一个文件，合并规则：按文件名分割排序
	 * @param path 基础目录，该根目录下的所有文本文件都会被合并到 mergeToFile
	 * @param mergeToFile 被合并的文本文件，这个参数可以为null,合并后的文件保存在path/merge.txt
	 */
	public static void multiFileMerge(String path, String mergeToFile, boolean deleteThisFile) {
		mergeToFile = mergeToFile == null ? path + "/merge.txt" : mergeToFile;
		File[] files = new File(path).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});
		Arrays.sort(files, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				int o1Index = Integer.parseInt(o1.getName().split("\\-")[0]);
				int o2Index = Integer.parseInt(o2.getName().split("\\-")[0]);
				if (o1Index > o2Index) {
					return 1;
				} else if (o1Index == o2Index){
					return 0;
				} else {
					return -1;
				}
			}
		});
		PrintWriter out = null;
		try {
			out = new PrintWriter(new File(mergeToFile), "UTF-8");
			for (File file : files) {
				BufferedReader bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));	
				String line = null;
				while ((line = bufr.readLine()) != null) {
					out.println(line);
				}
				bufr.close();
				
				if (deleteThisFile) {
					file.delete();
				}
			}
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		} finally {
			out.close();
		}
	}
	
	/**
	 * 获取书籍的状态
	 * @param status
	 * @return
	 */
	public static int getNovelStatus(String status) {
		if (status.contains("连载")) {
			return 1;
		} else if (status.contains("完结") || status.contains("完成") || status.contains("完本")) {
			return 2;
		} else {
			return 3;
//			throw new RuntimeException ("不支持的书籍状态：" + status);
		}
	}
	
	/**
	 * 格式化日期字符串为日期对象
	 * @param dateStr
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String dateStr, String pattern) throws ParseException {
		if ("MM-dd".equals(pattern)) {
			pattern = "yyyy-MM-dd";
			dateStr = getDateField(Calendar.YEAR) + "-" + dateStr;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = sdf.parse(dateStr);
		return date;
	}
	
	/**
	 * 获取本时刻的字符量
	 * @param field
	 * @return
	 */
	public static String getDateField(int field) {
		Calendar cal = new GregorianCalendar();
		return cal.get(field) + "";
	}
}
