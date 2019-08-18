
class Cart {
	private Item item;
	private int Qty;
	private Merchant m;
	 public Cart(Item i ,int Qty ,Merchant m) {
		 this.item=i;
		 this.Qty=Qty;
		 this.m=m; // association relation
		 
	 }
	 
	 public Item get_item() {
		 return this.item;
	 }
	 
	 public int get_Qty() {
		 return this.Qty;
	 }
	 public void set_Qty(int val) {
		 this.Qty+=val;
	 }
	 public Merchant get_merchant() {
		 return this.m;
	 }
	
	
	

}
