package music.library.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Store {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY) 
	//@Column(name = "storeId")
	private Long storeId;
	//storeId = storeId != null ? storeId : 1; 
	//private Long storeId;
	private String storeName;
	private String storePhone;
	private String storeCity;
	private String storeState;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "store", cascade = CascadeType.PERSIST)
	private Set<Item> items = new HashSet<>();
	
	
	//don't need this here because items only joins with Store
//	@EqualsAndHashCode.Exclude
//	@ToString.Exclude
//	@ManyToMany(cascade = CascadeType.PERSIST)
//	private Set<Category> categories = new HashSet<>();
//	
	
	

}
	



