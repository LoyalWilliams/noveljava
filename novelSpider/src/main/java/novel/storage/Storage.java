package novel.storage;

import java.io.FileNotFoundException;

import novel.storage.impl.BxwxNovelStorageImpl;

public class Storage {

	public static void main(String[] args) throws FileNotFoundException {
		if(args.length==0)
			System.exit(0);
		Processor processor = new BxwxNovelStorageImpl();
		processor.process();
	}
}
