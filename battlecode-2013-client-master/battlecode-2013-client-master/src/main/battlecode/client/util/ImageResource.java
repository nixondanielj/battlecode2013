package battlecode.client.util;

import java.util.*;

public class ImageResource<E> {

	private Map<E, ImageFile> cache;

	public ImageResource() {
		cache = new HashMap<E, ImageFile>();
	}

	public ImageFile getResource(E key, String path) {
		ImageFile img = cache.get(key);
		if (img != null) {
			return img;
		}
		//System.out.println("caching " + path);
		img = new ImageFile(path);
		cache.put(key, img);
		return img;
	}
}
