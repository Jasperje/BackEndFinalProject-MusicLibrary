package music.library.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long itemId;
	private String itemComposer;
	private String itemTitle;
	private String itemNotes;
	
	
	//store ID is the FK...
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "store_id")
	private Store store;
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "item_category", joinColumns =
	@JoinColumn(name = "item_id"), inverseJoinColumns = 
	@JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();
	
}
