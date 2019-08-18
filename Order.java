
class Order {
	private String Item_name;
	private int Item_Qty;
	private Merchant m;
	private int price;
	private int discount;
	private double total;
	public Order(String name , int Qty , int price , int discount , Merchant m) {
		this.Item_name=name;
		this.Item_Qty=Qty;
		this.price=price;
		this.discount=discount;
		this.m=m; // association relation
		this.total=0;
	}
	public String  get_name() {
		return this.Item_name;
	}
	public int get_Qty() {
		return this.Item_Qty;
	}
	public double get_total_price() {
		return this.total;
	}
	public void set_total_price() {
		if(this.discount==1) {
			this.total=(price*Item_Qty/2);
		}
		else if(this.discount==2) {
			this.total=(price*Item_Qty-(0.25*price*Item_Qty));
		}
		else {
			this.total=(price*Item_Qty);
		}

	}
	public int get_discount() {
		return this.discount;
	}
	public String print_offer(int val) {
		if(val==0) {
			return "None";
		}
		else if(val==1) {
			return "Buy 1 Get 1 Free";
		}
		else {
			return "25% off";
		}
	}
	public Merchant get_merchant() {
		return this.m;
	}
	
}
