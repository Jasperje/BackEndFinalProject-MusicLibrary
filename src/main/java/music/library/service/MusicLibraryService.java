package music.library.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import music.library.controller.model.CategoryData;
import music.library.controller.model.ItemData;
import music.library.controller.model.StoreData;
import music.library.dao.CategoryDao;
import music.library.dao.ItemDao;
import music.library.dao.StoreDao;
import music.library.entity.Category;
import music.library.entity.Item;
import music.library.entity.Store;


@Service
public class MusicLibraryService {

	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private StoreDao storeDao;
	
	@Autowired
	private CategoryDao categoryDao;
	


	
	//The saveItem method is for inserting AND updating items:
	@Transactional(readOnly = false)
	public ItemData saveItem(ItemData itemData, Long storeId) {
		   Store store = findStoreById(storeId);
	      
		   Long itemId = itemData.getItemId();
		   
	       Item item = findOrCreateItem(itemId);  
		
	       setItemFields(item, itemData);
	       
	       store.getItems().add(item);
	       item.setStore(store);
	       
	     
	       Item dbItem = itemDao.save(item);
	       return new ItemData(dbItem);
	    		   
	    }
	
	
		

	private void setItemFields(Item item, ItemData itemData) {
		item.setItemId(itemData.getItemId());      
		item.setItemComposer(itemData.getItemComposer());
		item.setItemTitle(itemData.getItemTitle());
		item.setItemNotes(itemData.getItemNotes());

		
	}

	private Item findOrCreateItem(Long itemId) {
		Item item;
		
		if(Objects.isNull(itemId)) {
			item = new Item();
		}
		else {
			item = findItemById(itemId);
		}
		return item;
	}

	private Item findItemById(Long itemId) {
		return itemDao.findById(itemId).orElseThrow(()
				-> new NoSuchElementException("Item with ID=" 
						+ itemId + " was not found."));
	}


   @Transactional(readOnly = true)
	public List<ItemData> retrieveAllItems() {

	    List<Item> items = itemDao.findAll();
		List<ItemData> response = new LinkedList<>();
		
		for(Item item : items) {
			response.add(new ItemData(item));
		}
		return response;
   
	}

   
@Transactional(readOnly = true)
public ItemData retrieveItemById(Long itemId) {
	Item item = findItemById(itemId);
	return new ItemData(item);
}


@Transactional(readOnly = false)
public void deleteItemById(Long itemId) {
	Item item  = findItemById(itemId);
	itemDao.delete(item);
}

   




@Transactional(readOnly = true)
public StoreData retrieveStoreById(Long storeId) {
	Store store = findStoreById(storeId);
	return new StoreData(store);
}

@Transactional(readOnly = true)
private Store findStoreById(Long storeId) {
	
	// Store store = findStoreById(storeId);
	return storeDao.findById(storeId).orElseThrow(  
			() -> new NoSuchElementException("Store with ID=" 
	         + storeId + "was not found.")); 
}


//In this method, you can add an Item to a pre-existing Category
// AND/or add a category to a pre-existing item.
public void  joinItemAndCategory(Long itemId, Long categoryId) {
	Item item = findItemById(itemId);
	Category category = findCategoryById(categoryId);
	category.getItems().add(item);
	item.getCategories().add(category);
	categoryDao.save(category);
}




public CategoryData saveCategory(CategoryData categoryData) {
	Long categoryId = categoryData.getCategoryId();
	Category category = findOrCreateCategory(categoryId);
    copyCategoryFields(category, categoryData);
	return new CategoryData(categoryDao.save(category));
	 
}
	

private void copyCategoryFields(Category category, CategoryData categoryData) {
	category.setCategoryId(categoryData.getCategoryId());
	category.setCategoryName(categoryData.getCategoryName());
 
  
}


private Category findOrCreateCategory(Long categoryId) {
	
    if(Objects.isNull(categoryId)) {
    	return new Category();
    }
    else {
    	return findCategoryById(categoryId);
    	
    }
}


private Category findCategoryById(Long categoryId) {
    return categoryDao.findById(categoryId).orElseThrow(() -> new 
    		NoSuchElementException(
    		"Category with ID=" + categoryId + " was not found." ));
	
}


@Transactional(readOnly = true)
	public List<CategoryData> retrieveAllCategories() {

	    List<Category> categories = categoryDao.findAll();
		List<CategoryData> response = new LinkedList<>();
		
		for(Category category : categories) {
			response.add(new CategoryData(category));
		}
		return response;

	}


@Transactional(readOnly = true)
public CategoryData retrieveCategoryById(Long categoryId) {
	Category category = findCategoryById(categoryId);
	return new CategoryData(category);
}







}
	

	
	




