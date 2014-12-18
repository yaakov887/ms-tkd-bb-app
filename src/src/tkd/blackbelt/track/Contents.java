package tkd.blackbelt.track;

import java.util.ArrayList;
import java.util.List;
import android.util.SparseArray;


public class Contents {
		
	public Contents () {}
	
	/**
	 * An array of Content items.
	 */
	public static List<ContentItem> ITEMS = new ArrayList<ContentItem>();

	/**
	 * A map of Content items, by ID.
	 */
	public static SparseArray<ContentItem> ITEM_MAP = new SparseArray<ContentItem>();

	public static void addItem(ContentItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}
	
	public class ContentItem {
		private int id;
		private String content;
		
		public ContentItem (int i, String c) {
			id = i;
			content = c;
		}
		
		public String getContent () {
			return content;
		}
		
		public int getId () {
			return id;
		}
	}

}
