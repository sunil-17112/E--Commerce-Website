
class Item {
	
	final private int id;
	final private String name;
	private int price;
	private int Qty;
	private int offer;
	final private String Category;
	final private Merchant m; // class level reference of merchant 
	
	public Item(int id,String name, int price , int Qty , String Cat , Merchant m) {
		this.offer=0;
		this.id=id;
		this.name=name;
		this.price=price;
		this.Qty=Qty;
		this.Category=Cat;
		this.m=m; // associative relation
		
	}
	
	public int get_id() {
		return this.id;
	}
	
	public String get_name() {
		return this.name;
	}
	
	public int get_price() {
		return this.price;
	}
	
	public int get_Qty() {
		return this.Qty;
	}
	
	public String get_Category() {
		return this.Category;
	}
	
	public int get_offer() {
		return this.offer;
	}
	
	public void set_offer(int val) {
		this.offer=val;
	}
	
	public String print_offer() {
		int i=get_offer();
		if(i==0) {
			return "None";
		}
		else if(i==1) {
			return "Buy one get one free";
		}
		else {
			return "25% off";
		}
	}
	
	public void set_price(int val) {
		this.price=val;
	}
	
	public void set_Qty(int val) {
		this.Qty=val; // please check updated Qty should not be negative
	}
	
	public Merchant get_merchant() {
		return this.m;
	}
	
	

}
