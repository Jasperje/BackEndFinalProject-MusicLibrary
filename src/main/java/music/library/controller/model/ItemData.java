package music.library.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import music.library.entity.Category;
import music.library.entity.Item;
import music.library.entity.Store;

@Data // creates our getters and setters
@NoArgsConstructor
public class ItemData {


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
	public ItemData(Item item) {  
		itemId = item.getItemId();
		itemComposer = item.getItemComposer();
		itemTitle = item.getItemTitle();
		itemNotes = item.getItemNotes();

		// here we are looping through the diff. categories of an item
		for(Category category : item.getCategories()) {
			categories.add(new CategoryResponse(category));
		}
	
	}

	

	@Data
	@NoArgsConstructor
	static class CategoryResponse {

		private Long categoryId;
		private String categoryName;
		
		
		CategoryResponse(Category category) {
		    
			categoryId = category.getCategoryId();  
			categoryName = category.getCategoryName();
			
		
		}
		
		// ? 
//		@EqualsAndHashCode.Exclude
//		@ToString.Exclude
//		@ManyToMany(mappedBy = "categories", cascade = CascadeType.PERSIST)
//		private Set<Item> items = new HashSet<>();
//		
		
		
	}
}
