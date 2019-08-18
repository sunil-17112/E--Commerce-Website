import java.util.*;
class Merchant implements Manage{
	private static int PCI=1000;
	final private int id;
	final private String name;
	final private String Address;
	private int reward;
	private double Contribution_to_Mercury;
	private ArrayList<Item> item;
	private ArrayList<Item> reward_slot;
	private ArrayList<String> Category;
	private ArrayList<Merchant> merchant;
	public Merchant(int id,String name,String add,ArrayList<String> Category,ArrayList<Merchant> merchant) {
		this.item=new ArrayList<Item>();
		this.reward_slot=new ArrayList<Item>();
		this.Category=Category;  // association relation
		this.id=id;
		this.name=name;
		this.Address=add;
		this.Contribution_to_Mercury=0;
		this.reward=0;
		this.merchant=merchant;
	}
	
	public int get_id() {
		return this.id;
	}
	
	public String get_name() {
		return this.name;
	}
	
	public String get_address() {
		return this.Address;
	}
	
	public double get_Contribution_to_Mercury() {
		return this.Contribution_to_Mercury;
	}
	
	public void set_Contribution_to_Mercury(double val) {
		
		this.Contribution_to_Mercury+=val;
		if(this.get_Contribution_to_Mercury()>=100.0) {
			this.reward+=(get_Contribution_to_Mercury()/100);
			this.Contribution_to_Mercury=get_Contribution_to_Mercury()%100;
		}
	}
	
	public ArrayList<Item> get_item(){
		return this.item;
	}
	
	//query 1
	public void Add_Item(String name , int price , int Qty , String Cat) {
		int flag=-1;
		for(int i=0;i<this.item.size();i++) {
			if(this.item.get(i).get_name().toUpperCase().equals(name.toUpperCase())&&this.item.get(i).get_Category().toUpperCase().equals(Cat.toUpperCase())) {
				flag=i;
				break;
			}
		}
		if(flag==-1) {
			if(this.item.size()<=10+reward) {
				Item i=new Item(++PCI, name, price, Qty, Cat, this); // associative relation
				if(item.size()>=10) {
					this.reward_slot.add(i);  // extra slot
				}
				int ptr=-1;
				for(int j=0;j<Category.size();j++) {
					if(!Category.get(j).toUpperCase().equals(Cat.toUpperCase())) {
						continue;
					}
					else {
						ptr=j;
						break;
					}
				}
				if(ptr==-1) {
					//System.out.println("Hi sunil");
					Category.add(Cat);
				}
				this.item.add(i); 
				System.out.println("Item added successfully");
				System.out.println(i.get_id()+  " " + i.get_name()+ " " +i.get_price()+ " " +i.get_Qty()+ " " +i.print_offer()+ " " +i.get_Category());
			}
			else {
				System.out.println("NO more item added to merchant");
			}
		}
		else {
			System.out.println("This item are already exist in merchant account");
		}
	}
	
	//query 2
	public void Edit_Item(int code , int price , int Qty) {
		
		for(int i=0;i<this.item.size();i++) {
			if(this.item.get(i).get_id()==code) {
				
				this.item.get(i).set_price(price);
				this.item.get(i).set_Qty(Qty);
				System.out.println(this.item.get(i).get_id()+  " " + this.item.get(i).get_name()+ " " +this.item.get(i).get_price()+ " " +this.item.get(i).get_Qty()+ " " +this.item.get(i).print_offer()+ " " +this.item.get(i).get_Category());
				System.out.println("Item edit successfully");
				break;
			}
			
		}
		
		
	}
	
	//query 4
	public void Add_offer(int code ,int offer_code) {
		
		for(int i=0;i<this.item.size();i++) {
			if(this.item.get(i).get_id()==code) {
				if(this.item.get(i).get_Qty()!=0) {
					if(offer_code==1) {
						if(this.item.get(i).get_Qty()==1) {
							System.out.println(this.item.get(i).get_id()+  " " + this.item.get(i).get_name()+ " " +this.item.get(i).get_price()+ " " +this.item.get(i).get_Qty()+ " " +this.item.get(i).print_offer()+ " " +this.item.get(i).get_Category());
							break;
						}
						else {
							this.item.get(i).set_offer(offer_code);
							System.out.println(this.item.get(i).get_id()+  " " + this.item.get(i).get_name()+ " " +this.item.get(i).get_price()+ " " +this.item.get(i).get_Qty()+ " " +this.item.get(i).print_offer()+ " " +this.item.get(i).get_Category());
							break;
						}
					}
					else {
						this.item.get(i).set_offer(offer_code);
						System.out.println(this.item.get(i).get_id()+  " " + this.item.get(i).get_name()+ " " +this.item.get(i).get_price()+ " " +this.item.get(i).get_Qty()+ " " +this.item.get(i).print_offer()+ " " +this.item.get(i).get_Category());
						break;
					}
				}
				else {
					System.out.println("First Add the Quantity");
					System.out.println("Otherwise Offer won't be Added");
				}
			}
		}
	}
	
	//query 6
	public boolean Exit() {
		return false;
	}
	
	//query 3
	@Override
	public void Search() {
		System.out.println("Choose the Category");
		for(int i=0;i<this.Category.size();i++) {
			System.out.println((i+1) + " For " + this.Category.get(i));
		}
		
		Scanner sc=new Scanner(System.in);
		int ind=sc.nextInt();
		if(ind>=1&&ind<=this.Category.size()) {
			String s=Category.get(ind-1);
			ArrayList<Item> item=new ArrayList<Item>();
			for(int i=0;i<this.merchant.size();i++) {
				for(int j=0;j<this.merchant.get(i).get_item().size();j++) {
					if(this.merchant.get(i).get_item().get(j).get_Category().toUpperCase().equals(s.toUpperCase())){
						item.add(this.merchant.get(i).get_item().get(j));
						System.out.println(this.merchant.get(i).get_item().get(j).get_id()+  " " + this.merchant.get(i).get_item().get(j).get_name()+ " " +this.merchant.get(i).get_item().get(j).get_price()+ " " +this.merchant.get(i).get_item().get(j).get_Qty()+ " " +this.merchant.get(i).get_item().get(j).print_offer()+ " " +this.merchant.get(i).get_item().get(j).get_Category() + " " + this.merchant.get(i).get_name());
					}
				}
			}
		}
		else{
			
			System.out.println("INVALID CATEGORY");
		}
	}
	
	//query 5
	@Override
	public void Reward_won() {
		
		if(this.reward_slot.size()==0) {
			if(this.reward>0) {
				System.out.println(this.reward_slot.size());
				System.out.println("Merchant haven't used Any Reward until now");
			}
			else {
				System.out.println("Merchnat haven't got any reward");
			}
		}
		else {
			System.out.println(this.reward_slot.size());
			for(int i=0;i<this.reward_slot.size();i++) {
				System.out.println(this.reward_slot.get(i).get_id() + " " + this.reward_slot.get(i).get_name()+ " " + this.reward_slot.get(i).get_Category());
			}
			
		}
	}
	

}
