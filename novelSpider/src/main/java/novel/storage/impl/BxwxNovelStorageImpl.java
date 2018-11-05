package novel.storage.impl;

import java.io.FileNotFoundException;

public class BxwxNovelStorageImpl extends AbstractNovelStorage {
	public BxwxNovelStorageImpl() throws FileNotFoundException {
		tasks.put("0","https://www.bxwx9.org/binitial1/0/1.htm");
		for(int i=0;i<=25;i++){
			String letter=(char)(i+'A')+"";	
			System.out.println("https://www.bxwx9.org/binitial"+letter+"/0/1.htm");
			tasks.put(letter,"https://www.bxwx9.org/binitial"+letter+"/0/1.htm");
		}
	}
	public BxwxNovelStorageImpl(String confPath) throws FileNotFoundException {
		super(confPath);
		tasks.put("0","https://www.bxwx9.org/binitial1/0/1.htm");
		for(int i=0;i<=25;i++){
			String letter=(char)(i+'A')+"";	
			System.out.println("https://www.bxwx9.org/binitial"+letter+"/0/1.htm");
			tasks.put(letter,"https://www.bxwx9.org/binitial"+letter+"/0/1.htm");
		}
	}

}
