package music.library.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import music.library.controller.model.ItemData.CategoryResponse;
import music.library.entity.Category;
import music.library.entity.Item;
import music.library.entity.Store;

@Data
@NoArgsConstructor
public class StoreData {

	private Long storeId;
	private String storeName;
	private String storePhone;
	private String storeCity;
	private String storeState;
	private Set<ItemResponse> itemResponses = new HashSet<>();

	public StoreData(Store store) {
		storeId = store.getStoreId();
		storeName = store.getStoreName();
		storePhone = store.getStorePhone();
		storeCity = store.getStoreCity();
		storeState = store.getStoreState();

		for (Item item : store.getItems()) {
			itemResponses.add(new ItemResponse(item));
		}

	}

	@Data
	@NoArgsConstructor
	public class ItemResponse {

		private Long itemId;
		private String itemComposer;
		private String itemTitle;
		private String itemNotes;
		private Store store;
		private Set<CategoryResponse> categories = new HashSet<>();

		public ItemResponse(Item item) {
			itemId = item.getItemId();
			itemComposer = item.getItemComposer();
			itemTitle = item.getItemTitle();
			itemNotes = item.getItemNotes();

			// here we are looping through the diff. categories of an item
			for (Category category : item.getCategories()) {
				categories.add(new CategoryResponse(category));
			}
		}

	}
}
