package music.library.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import music.library.controller.model.CategoryData;
import music.library.controller.model.ItemData;
import music.library.controller.model.StoreData;
import music.library.service.MusicLibraryService;

@RestController
@RequestMapping("/music_library")
@Slf4j

public class MusicLibraryController {
	
	
	@Autowired
	public MusicLibraryService musicLibraryService;
	


	//This method POSTs a new item to the Music Library database
	@PostMapping("/{storeId}/item")  
	public ItemData insertItem (@RequestBody ItemData itemData, @PathVariable Long storeId) {
		log.info("Adding Item {} to Store with ID={}", itemData, storeId);
			return musicLibraryService.saveItem(itemData, storeId);
			
	}
	
	//This method PUTs (updates) the details of a specified item in the database
	@PutMapping("/{storeId}/item/{itemId}")
	public ItemData updateItem(@PathVariable Long itemId,
			@RequestBody ItemData itemData, @PathVariable Long storeId) {
		
		itemData.setItemId(itemId);
		// I added "itemId" in here is this correct??
		log.info("Updating item {}", itemData, itemId); 
		
		// and here as well? 
		return musicLibraryService.saveItem(itemData, storeId);
		}

	//This method retrieves all items that are currently listed in the database
	@GetMapping("/item")
	public List<ItemData> retrieveAllItems() {
		log.info("Retrieving all items...");
		
		return musicLibraryService.retrieveAllItems();
	}
	
	//This method GETs/retrieves the details of a specified item, by Item ID, from the database
	@GetMapping("/item/{itemId}")
	public ItemData retrieveItemById(@PathVariable Long itemId) {
		log.info("Retrieving item with ID={}", itemId);    
	    return musicLibraryService.retrieveItemById(itemId);
	}
	
	//This method DELETEs an item, by Item ID, from the database
	@DeleteMapping("/item/{itemId}")
	public Map<String, String> deleteItemById(@PathVariable Long itemId) {
		log.info("Deleting item with ID{}", itemId);
		musicLibraryService.deleteItemById(itemId);
		
		return Map.of("message", "Deletion of item with ID=" + itemId + 
				" was successful!");
	}
	
	
	//This method retrieves the details of the music library "store" 
	@GetMapping("/store/{storeId}")
	public StoreData retrieveStoreById(
			//@PathVariable Long itemId,
			@PathVariable Long storeId) {
		log.info("Retrieving store with ID={} for item with ID={}", 
				storeId);
				//, itemId);
		
		return musicLibraryService.retrieveStoreById(storeId);
	}
	

	
	
	//This method can POST a category ID and assign it to a certain Item ID in order to categorize it
	//It can also POST an item to a category, through the JOIN table
	@PostMapping("/{categoryId}/{itemId}") 
	public Map<String, String> addItemToCategory( 
			@PathVariable Long categoryId, 
			@PathVariable Long itemId) {
		log.info("Adding item {} to Category {}", itemId, categoryId);
		musicLibraryService.joinItemAndCategory(itemId, categoryId);
		
		return Map.of("Message", "The item with ID = " + itemId 
				+ "was added to category with ID = " + categoryId);	
		}

	

//This method POSTs/creates a new category, under which items may be categorized
@PostMapping("/category")
@ResponseStatus(code = HttpStatus.CREATED) 
public CategoryData insertCategory(
		@RequestBody CategoryData categoryData) {
	log.info("Creating category {}", categoryData);
	return musicLibraryService.saveCategory(categoryData);
	
}

//This method GETs/retrieves all categories
@GetMapping("/category")
public List<CategoryData> retrieveAllCategories() {
	log.info("Retrieving all categories...");
	
	return musicLibraryService.retrieveAllCategories();
}

//This method GETs/retrieves all items within a specified category
@GetMapping("/category/{categoryId}")
public CategoryData retrieveCategoryById(@PathVariable Long categoryId) {
	log.info("Retrieving category with ID={}", categoryId);    
    return musicLibraryService.retrieveCategoryById(categoryId);
}

}




	
	

