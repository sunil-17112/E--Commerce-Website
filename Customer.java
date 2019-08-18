import java.util.ArrayList;
import java.util.Scanner;

class Customer implements Manage{
	private double main_account;
	private double cust_reward_account;
	private double cust_reward_won;
	final private int id;
	final private String name;
	final private String Address;
	private int number_of_order_placed;
	private ArrayList<Cart> add_to_Cart;
	private ArrayList<Order> transaction_history;
	private ArrayList<String> Category;
	private ArrayList<Merchant> merchant;
	public Customer(int id , String name,String add,ArrayList<String> Category , ArrayList<Merchant> merchant) {
		this.main_account=100;
		this.cust_reward_account=0;
		this.cust_reward_won=0;
		this.id=id;
		this.name=name;
		this.Address=add;
		this.number_of_order_placed=0;
		this.Category=Category;  // association relation
		this.merchant=merchant;
		
		this.add_to_Cart=new ArrayList<Cart>();
		this.transaction_history=new ArrayList<Order>();
	}
	
	public double get_reward_won() {
		return this.cust_reward_won;
	}
	
	public void set_reward_won(double val) {
		this.cust_reward_won+=val;
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
	
	public int get_number_of_order_placed() {
		return this.number_of_order_placed;
	}
	
	public void set_number_of_order_placed() {
		this.number_of_order_placed++;
		if(get_number_of_order_placed()%5==0&&get_number_of_order_placed()>0) {
			set_reward_won(10);
			cust_reward_account+=10;
			
		}
	}
	
	//query 2
	public void Checkout() {
		if(add_to_Cart.size()!=0) {
			int flag=-1;
			for(int i=0;i<add_to_Cart.size();i++) {
				if(add_to_Cart.size()!=0) {
					if(add_to_Cart.get(i).get_item().get_offer()==1) {
						add_to_Cart.get(i).set_Qty(add_to_Cart.get(i).get_Qty());
					}
					if(add_to_Cart.get(i).get_item().get_Qty()>=add_to_Cart.get(i).get_Qty()) {
						//add_to_Cart.get(i).get_item().set_Qty(add_to_Cart.get(i).get_item().get_Qty()-add_to_Cart.get(i).get_Qty());
						
						if(add_to_Cart.get(i).get_item().get_offer()==1) {
							
							double res=add_to_Cart.get(i).get_item().get_price()*add_to_Cart.get(i).get_Qty()/2;
							
							
							if((res+0.005*res)<=this.main_account) {
								this.main_account-=(res+0.005*res);
								this.set_number_of_order_placed();
								double  d=0.005*res;
								add_to_Cart.get(i).get_merchant().set_Contribution_to_Mercury(d);
								
								Order o=new Order(add_to_Cart.get(i).get_item().get_name(), add_to_Cart.get(i).get_Qty(), add_to_Cart.get(i).get_item().get_price(), 1,add_to_Cart.get(i).get_merchant());
								o.set_total_price();
								
								add_to_Cart.get(i).get_item().set_Qty(add_to_Cart.get(i).get_item().get_Qty()-add_to_Cart.get(i).get_Qty());
								if(add_to_Cart.get(i).get_item().get_Qty()<=1) {
									add_to_Cart.get(i).get_item().set_offer(0);
								}
								add_to_Cart.remove(i--);
							
								if(this.transaction_history.size()==10) {
									this.transaction_history.remove(0);
									this.transaction_history.add(o);
								}
								else {
									this.transaction_history.add(o);
								}
							}
							else {
								if((res+0.005*res)<=(this.main_account+this.cust_reward_account)) {
									this.main_account=0;
									this.cust_reward_account-=(res+0.005*res-this.main_account);
									this.set_number_of_order_placed();
									double  d=0.005*res;
									add_to_Cart.get(i).get_merchant().set_Contribution_to_Mercury(d);
									
									Order o=new Order(add_to_Cart.get(i).get_item().get_name(), add_to_Cart.get(i).get_Qty(), add_to_Cart.get(i).get_item().get_price(), 1,add_to_Cart.get(i).get_merchant());
									o.set_total_price();
									
									add_to_Cart.get(i).get_item().set_Qty(add_to_Cart.get(i).get_item().get_Qty()-add_to_Cart.get(i).get_Qty());
									if(add_to_Cart.get(i).get_item().get_Qty()<=1) {
										add_to_Cart.get(i).get_item().set_offer(0);
									}
									add_to_Cart.remove(i--);
									if(this.transaction_history.size()==10) {
										this.transaction_history.remove(0);
										this.transaction_history.add(o);
									}
									else {
										this.transaction_history.add(o);
									}
								}
								else {
									add_to_Cart.get(i).set_Qty(-add_to_Cart.get(i).get_Qty());
									flag=i;
									break;
								}
							}
						}
						else if(add_to_Cart.get(i).get_item().get_offer()==2) {
							
							double res=add_to_Cart.get(i).get_item().get_price()*add_to_Cart.get(i).get_Qty();
							
							
							if((res+0.005*res-0.25*res)<=this.main_account) {
								this.main_account-=(res+0.005*res-0.25*res);
								this.set_number_of_order_placed();
								double  d=0.005*res;
								add_to_Cart.get(i).get_merchant().set_Contribution_to_Mercury(d);
								
								Order o=new Order(add_to_Cart.get(i).get_item().get_name(), add_to_Cart.get(i).get_Qty(), add_to_Cart.get(i).get_item().get_price(), 2,add_to_Cart.get(i).get_merchant());
								o.set_total_price();
								
								add_to_Cart.get(i).get_item().set_Qty(add_to_Cart.get(i).get_item().get_Qty()-add_to_Cart.get(i).get_Qty());
								if(add_to_Cart.get(i).get_item().get_Qty()==0) {
									add_to_Cart.get(i).get_item().set_offer(0);
								}
								add_to_Cart.remove(i--);
								if(this.transaction_history.size()==10) {
									this.transaction_history.remove(0);
									this.transaction_history.add(o);
								}
								else {
									this.transaction_history.add(o);
								}
								
							}
							else {
								if((res+0.005*res-0.25*res)<=(this.main_account+this.cust_reward_account)) {
									this.main_account=0;
									this.cust_reward_account-=(res+0.005*res-0.25*res-this.main_account);
									this.set_number_of_order_placed();
									double  d=0.005*res;
									add_to_Cart.get(i).get_merchant().set_Contribution_to_Mercury(d);
									
									Order o=new Order(add_to_Cart.get(i).get_item().get_name(), add_to_Cart.get(i).get_Qty(), add_to_Cart.get(i).get_item().get_price(), 2,add_to_Cart.get(i).get_merchant());
									o.set_total_price();
									
									add_to_Cart.get(i).get_item().set_Qty(add_to_Cart.get(i).get_item().get_Qty()-add_to_Cart.get(i).get_Qty());
									if(add_to_Cart.get(i).get_item().get_Qty()==0) {
										add_to_Cart.get(i).get_item().set_offer(0);
									}
									add_to_Cart.remove(i--);
									if(this.transaction_history.size()==10) {
										this.transaction_history.remove(0);
										this.transaction_history.add(o);
									}
									else {
										this.transaction_history.add(o);
									}
								}
								else {
									flag=i;
									break;
								}
							}
						}
						else {
							
							double res=add_to_Cart.get(i).get_item().get_price()*add_to_Cart.get(i).get_Qty();
							
							
							if((res+0.005*res)<=this.main_account) {
								this.main_account-=(res+0.005*res);
								this.set_number_of_order_placed();
								double  d=0.005*res;
								add_to_Cart.get(i).get_merchant().set_Contribution_to_Mercury(d);
								
								Order o=new Order(add_to_Cart.get(i).get_item().get_name(), add_to_Cart.get(i).get_Qty(), add_to_Cart.get(i).get_item().get_price(), 0,add_to_Cart.get(i).get_merchant());
								o.set_total_price();
								
								add_to_Cart.get(i).get_item().set_Qty(add_to_Cart.get(i).get_item().get_Qty()-add_to_Cart.get(i).get_Qty());
								add_to_Cart.remove(i--);
								if(this.transaction_history.size()==10) {
									this.transaction_history.remove(0);
									this.transaction_history.add(o);
								}
								else {
									this.transaction_history.add(o);
								}
								
							}
							else {
								if((res+0.005*res)<=(this.main_account+this.cust_reward_account)) {
									this.main_account=0;
									this.cust_reward_account-=(res+0.005*res-this.main_account);
									this.set_number_of_order_placed();
									double  d=0.005*res;
									add_to_Cart.get(i).get_merchant().set_Contribution_to_Mercury(d);
									
									Order o=new Order(add_to_Cart.get(i).get_item().get_name(), add_to_Cart.get(i).get_Qty(), add_to_Cart.get(i).get_item().get_price(), 0,add_to_Cart.get(i).get_merchant());
									o.set_total_price();
									
									add_to_Cart.get(i).get_item().set_Qty(add_to_Cart.get(i).get_item().get_Qty()-add_to_Cart.get(i).get_Qty());
									add_to_Cart.remove(i--);
									if(this.transaction_history.size()==10) {
										this.transaction_history.remove(0);
										this.transaction_history.add(o);
									}
									else {
										this.transaction_history.add(o);
									}
								}
								else {
									flag=i;
									break;
								}
							}
						}
						
					}
					else {
						if(add_to_Cart.get(i).get_item().get_offer()==1) {
							add_to_Cart.get(i).set_Qty(-add_to_Cart.get(i).get_Qty()/2);
						}
						flag=-2;
						break;
					}
				}
				
			}
			if(flag==-1) {
				System.out.println("Successfully checked out all items");
			}
			else {
				if(flag==-2) {
					System.out.println("Out of Stock");
				}
				else {
					System.out.println("Out of Money");
				}
			}
			
		}
		else {
			System.out.println("Cart is Empty");
		}
	}
	
	//query 4
	public void Latest_Ordered() {
		for(int i=0;i<this.transaction_history.size();i++) {
			System.out.println("Bought Item :" +this.transaction_history.get(i).get_name() + " Quantity :" + this.transaction_history.get(i).get_Qty() + " for Rs :"+  this.transaction_history.get(i).get_total_price() + " from Merchant :" + this.transaction_history.get(i).get_merchant().get_name() + " with offer :" + this.transaction_history.get(i).print_offer(this.transaction_history.get(i).get_discount()));
		}
		if(this.transaction_history.size()==0) {
			System.out.println("No history found");
		}
	}
	
	//query 5
	public boolean Exit() {
		return false;
	}
	
	//query 1
	@Override
	public void Search() {
	
		if(this.Category.size()!=0) {
			System.out.println("Choose the Category");
		}
		for(int i=0;i<this.Category.size();i++) {
			System.out.println((i+1) + " For " + this.Category.get(i));
		}
		if(this.Category.size()==0) {
			System.out.println("NO Items Found");
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
						System.out.println(this.merchant.get(i).get_item().get(j).get_id()+  " " + this.merchant.get(i).get_item().get(j).get_name()+ " " +this.merchant.get(i).get_item().get(j).get_price()+ " " +this.merchant.get(i).get_item().get(j).get_Qty()+ " " +this.merchant.get(i).get_item().get(j).print_offer()+ " " +this.merchant.get(i).get_item().get(j).get_Category());
						
					}
				}
			}
			System.out.print("Enter the Item code : ");
			int id=sc.nextInt();
			System.out.println("");
			System.out.print("Enter the Qty : ");
			int Qty=sc.nextInt();
			System.out.println("");
			System.out.println(" 1 For Buy Item------------------------");
			System.out.println(" 2 For Add to Cart---------------------");
			System.out.println(" 3 For Exit----------------------------");
			
			int q=sc.nextInt();
			if(q==1) {
				for(int i=0;i<item.size();i++) {
					if(item.get(i).get_id()==id) {
						Item temp=item.get(i);
						if(item.get(i).get_offer()==1) {
							
							Qty*=2;
							if(temp.get_Qty()>=Qty) {
								double res=(Qty/2)*temp.get_price();
								if(res+0.005*res<=this.main_account) {
									this.main_account-=(res+0.005*res);
									this.set_number_of_order_placed();
									double  d=0.005*res;
									temp.get_merchant().set_Contribution_to_Mercury(d);
									
									Order o=new Order(temp.get_name(), Qty, temp.get_price(), 1, temp.get_merchant());
									o.set_total_price();
									temp.set_Qty(temp.get_Qty()-Qty);
									if(temp.get_Qty()<=1) {
										temp.set_offer(0);
									}
									System.out.println("Item Successfully Bought");
									if(this.transaction_history.size()==10) {
										this.transaction_history.remove(0);
										this.transaction_history.add(o);
									}
									else {
										this.transaction_history.add(o);
									}
									
								}
								else {
									
									if(res+0.005*res<=(this.main_account+this.cust_reward_account)) {
										this.cust_reward_account-=(res+0.005*res-this.main_account);
										this.main_account=0;
										this.set_number_of_order_placed();
										double  d=0.005*res;
										temp.get_merchant().set_Contribution_to_Mercury(d);
										Order o=new Order(temp.get_name(), Qty, temp.get_price(), 1, temp.get_merchant());
										o.set_total_price();
										temp.set_Qty(temp.get_Qty()-Qty);
										if(temp.get_Qty()<=1) {
											temp.set_offer(0);
										}
										
										System.out.println("Item Successfully Bought");
										if(this.transaction_history.size()==10) {
											this.transaction_history.remove(0);
											this.transaction_history.add(o);
										}
										else {
											this.transaction_history.add(o);
										}
									}
									else {
										System.out.println("Out of Money");
									}
								}
							}
							else {
								System.out.println("Out of Stock");
							}
						}
						else if(item.get(i).get_offer()==2){
							
							if(temp.get_Qty()>=Qty) {
								double res=Qty*temp.get_price();
								
								if(res+0.005*res-0.25*res<=this.main_account) {
									this.main_account-=(res+0.005*res-0.25*res);
									this.set_number_of_order_placed();
									double d=0.005*res;
									temp.get_merchant().set_Contribution_to_Mercury(d);
									Order o=new Order(temp.get_name(), Qty, temp.get_price(), 2, temp.get_merchant());
									o.set_total_price();
									temp.set_Qty(temp.get_Qty()-Qty);
									if(temp.get_Qty()==0) {
										temp.set_offer(0);
									}
									
									//System.out.println("sunil");
									System.out.println("Item Successfully Bought");
									if(this.transaction_history.size()==10) {
										this.transaction_history.remove(0);
										this.transaction_history.add(o);
									}
									else {
										this.transaction_history.add(o);
									}
								}
								else {
									if(res+0.005*res-0.25*res<=(this.main_account+this.cust_reward_account)) {
										this.cust_reward_account-=(res+0.005*res-0.25*res-this.main_account);
										this.main_account=0;
										this.set_number_of_order_placed();
										double  d=0.005*res;
										temp.get_merchant().set_Contribution_to_Mercury(d);
										Order o=new Order(temp.get_name(), Qty, temp.get_price(), 2, temp.get_merchant());
										o.set_total_price();
										temp.set_Qty(temp.get_Qty()-Qty);
										if(temp.get_Qty()==0) {
											temp.set_offer(0);
										}
										
										System.out.println("Item Successfully Bought");
										if(this.transaction_history.size()==10) {
											this.transaction_history.remove(0);
											this.transaction_history.add(o);
										}
										else {
											this.transaction_history.add(o);
										}
									}
									else {
										System.out.println("Out of Money");
									}
								}
							}
							else {
								System.out.println("Out of Stock");
							}
						}
						else {
							
							if(temp.get_Qty()>=Qty) {
								double res=Qty*temp.get_price();
								if(res+0.005*res<=this.main_account) {
									this.main_account-=(res+0.005*res);
									this.set_number_of_order_placed();
									double d=0.005*res;
									temp.get_merchant().set_Contribution_to_Mercury(d);
									Order o=new Order(temp.get_name(), Qty, temp.get_price(), 0, temp.get_merchant());
									o.set_total_price();
									temp.set_Qty(temp.get_Qty()-Qty);
									
									//System.out.println("sunil");
									System.out.println("Item Successfully Bought");
									if(this.transaction_history.size()==10) {
										this.transaction_history.remove(0);
										this.transaction_history.add(o);
									}
									else {
										this.transaction_history.add(o);
									}
								}
								else {
									if(res+0.005*res<=(this.main_account+this.cust_reward_account)) {
										this.cust_reward_account-=(res+0.005*res-this.main_account);
										this.main_account=0;
										this.set_number_of_order_placed();
										double  d=0.005*res;
										temp.get_merchant().set_Contribution_to_Mercury(d);
										Order o=new Order(temp.get_name(), Qty, temp.get_price(), 0, temp.get_merchant());
										o.set_total_price();
										temp.set_Qty(temp.get_Qty()-Qty);
										
										System.out.println("Item Successfully Bought");
										if(this.transaction_history.size()==10) {
											this.transaction_history.remove(0);
											this.transaction_history.add(o);
										}
										else {
											this.transaction_history.add(o);
										}
									}
									else {
										System.out.println("Out of Money");
									}
								}
							}
							else {
								System.out.println("Out of Stock");
							}
						}
					}
					else {
						System.out.println("INVALID ITEM CODE");
					}
				}
			}
			else if(q==2) {
				for(int i=0;i<item.size();i++) {
					if(item.get(i).get_id()==id) {
						Cart c=new Cart(item.get(i),Qty, item.get(i).get_merchant());
						this.add_to_Cart.add(c);
						System.out.println("Item Successfully Added into Cart");
						break;
					}
					else {
						System.out.println("INVALID ITEM CODE");
					}
				}
				
			}
			else {
				System.out.println("exit to current position");
			}
			
		}
		else {
			System.out.println("INVALID CATEGORY");
		}
		
	}
	
	//query 3
	@Override
	public void Reward_won() {
		System.out.println(get_reward_won());
	}
	
	

}
