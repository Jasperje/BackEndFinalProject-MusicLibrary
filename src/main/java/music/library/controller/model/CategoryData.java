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
public class CategoryData {

	private Long categoryId;
	private String categoryName;
	private Set<ItemResponse> items = new HashSet<>();
	
	public CategoryData(Category category) {
		categoryId = category.getCategoryId();
		categoryName = category.getCategoryName();
	
	
	for(Item item : category.getItems()) {
		items.add(new ItemResponse(item));
	}

}



@Data
@NoArgsConstructor
static class ItemResponse {

	private Long itemId;
	private String itemComposer;
	private String itemTitle;
	private String itemNotes;
	private Store store;
	private Set<CategoryResponse> categories = new HashSet<>();
	
	//this constructor converts an item Object into an item DATA object,
	//including the list of categories of that item
	//as CategoryResponse objects (it's called CategoryResponse
	//rather than CategoryData, (which prevents recursion)) 
	public ItemResponse(Item item) {  
		itemId = item.getItemId();
		itemComposer = item.getItemComposer();
		itemTitle = item.getItemTitle();
		itemNotes = item.getItemNotes();

  }

}
}


