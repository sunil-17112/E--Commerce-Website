import java.util.*;
public class Mercury_Inc {

	private ArrayList<String> Category;
	private boolean exit;
	private double balance;
	private int PCM;
	private int PCC;
	private  ArrayList<Customer> customer;
	private ArrayList<Merchant> merchant;
	private ArrayList<Integer> get_profit;
	
	public Mercury_Inc() {
		this.balance=0;
		this.PCM=8000;
		this.PCC=5000;
		this.customer=new ArrayList<Customer>(5);
		this.merchant=new ArrayList<Merchant>(5);
		this.get_profit=new ArrayList<Integer>(5);
		this.Category=new ArrayList<String>();
		this.exit=true;
	}
	private void Use_Searching(Manage manage) { //Interface
		manage.Search();
	}
	private void Use_Reward(Manage manage) {   //Interface
		manage.Reward_won();
	}
	private double get_balance() {
		return this.balance;
	}
	
	private void set_balance(double val) {
		this.balance=val;
	}
	
	private boolean get_exit() {
		return this.exit;
	}
	
	private void set_exit(boolean e) {
		this.exit=e;
	}
	private void Add_customer(String name,String add) {  // public
		customer.add(new Customer(++PCC,name,add,this.Category,this.merchant));  // associative relation
	}

	private void Add_merchant(String name,String add) {  //  public
		merchant.add(new Merchant(++PCM,name,add,this.Category,this.merchant));  
	}
	
	private void go_to_merchant_query() {  // public
		System.out.println("Choose Merchant");
		for(int i=0;i<5;i++) {
			System.out.println(merchant.get(i).get_id() + " " + merchant.get(i).get_name());
		}
		System.out.print("Enter the selected merchant ID : ");
		Scanner pc=new Scanner(System.in);
		Merchant m=merchant.get(0);
		int f=0;
		int id ;
		while(f==0) {
			id=pc.nextInt();
			int flag=-1;
			for(int i=0;i<5;i++) {
				if(merchant.get(i).get_id()==id) {
					flag=i;
					break;
				}
				else {
					continue;
				}
			}
			if(flag==-1) {
				System.out.println("INVALID ID");
				System.out.println("Please Enter correct ID");
			}
			else {
				m=merchant.get(flag);
				f=1;
			}
		}
		boolean merc_exit=true;
		while(merc_exit) {
			System.out.println("Welcome " + m.get_name());
			Print_Merchant_Detail();
			System.out.print("Enter your Type : ");
			int q=pc.nextInt();
			System.out.println("");
			if(q==1) {
				System.out.print("Enter Item name : ");
				String name=pc.next();
				System.out.println("");
				System.out.print("Enter price : ");
				int price=pc.nextInt();
				System.out.println("");
				System.out.print("Enter Qty : ");
				int Qty=pc.nextInt();
				System.out.println("");
				System.out.print("Enter Category : ");
				String Cat=pc.next();
				System.out.println("");
				m.Add_Item(name, price, Qty, Cat);
			}
			else if(q==2) {
				
				if(m.get_item().size()==0) {
					System.out.println("No Items are available for editing");
					System.out.println("First Add the Item ");
				}
				else {
					System.out.println("Choose Item By Code");
					int flag=-1;
					for(int i=0;i<m.get_item().size();i++) {
						
						System.out.println(m.get_item().get(i).get_id()+  " " + m.get_item().get(i).get_name()+ " " +m.get_item().get(i).get_price()+ " " +m.get_item().get(i).get_Qty()+ " " +m.get_item().get(i).print_offer()+ " " +m.get_item().get(i).get_Category());
					}
					
					System.out.print("Enter Item code : ");
					int code=pc.nextInt();
					System.out.println("");
					for(int i=0;i<m.get_item().size();i++) {
						if(m.get_item().get(i).get_id()==code) {
							flag=i;
							break;
						}
					}
					if(flag==-1) {
						System.out.println("INVALID CODE");
					}
					else {
						System.out.println("Enter Edit Details");
						System.out.print("Enter Price : ");
						int price=pc.nextInt();
						System.out.println("");
						System.out.print("Enter Qty : ");
						int Qty=pc.nextInt();
						System.out.println("");
						m.Edit_Item(code, price, Qty);
					}
				}
				
			}
			else if(q==3) {
				Use_Searching(m); // Interface concept
			}
			else if(q==4) {
				if(m.get_item().size()==0) {
					System.out.println("No Item are Available in Merchant Profile");
				}
				else {
					System.out.println("Choose Item By Code");
					for(int i=0;i<m.get_item().size();i++) {
						System.out.println(m.get_item().get(i).get_id()+  " " + m.get_item().get(i).get_name()+ " " +m.get_item().get(i).get_price()+ " " +m.get_item().get(i).get_Qty()+ " " +m.get_item().get(i).print_offer()+ " " +m.get_item().get(i).get_Category());
					}
					System.out.print("Enter Item code : ");
					int code=pc.nextInt();
					System.out.println("");
					int flag=-1;
					for(int i=0;i<m.get_item().size();i++) {
						if(m.get_item().get(i).get_id()==code) {
							flag=i;
							break;
						}
						else {
							continue;
						}
					}
					if(flag==-1) {
						System.out.println("INVALID CODE");
					}
					else {
						System.out.println("Choose Offer");
						System.out.println(" 1 For Buy one Gte One Free");
						System.out.println(" 2 For 25% Free");
						System.out.println(" 0 For Remove the Offer");
						System.out.print("Enter Offer code : ");
						int off=pc.nextInt();
						System.out.println("");
						m.Add_offer(code,off);
					}
				}
				
			}
			else if(q==5) {
				Use_Reward(m);   // Interface concept
			}
			else if(q==6) {
				merc_exit=m.Exit();
			}
			else {
				System.out.println("-------INVALID INPUT-------");
			}
		}
		
	}
	
	private void go_to_customer_query() {  // public
		System.out.println("Choose Customer");
		for(int i=0;i<5;i++) {
			System.out.println(customer.get(i).get_id() + " " + customer.get(i).get_name());
		}
		System.out.print("Enter the selected customer ID : ");
		Scanner pc=new Scanner(System.in);
		Customer c=customer.get(0);
		int f=0;
		int id ;
		while(f==0) {
			id=pc.nextInt();
			int flag=-1;
			for(int i=0;i<5;i++) {
				if(customer.get(i).get_id()==id) {
					flag=i;
					break;
				}
				else {
					continue;
				}
			}
			if(flag==-1) {
				System.out.println("INVALID ID");
				System.out.println("Please Enter correct ID");
			}
			else {
				c=customer.get(flag);
				f=1;
			}
		}
		boolean cust_exit=true;
		while(cust_exit) {
			System.out.println("Welcome " + c.get_name());
			Print_Customer_Detail();
			System.out.print("Enter your Type : ");
			int q=pc.nextInt();
			System.out.println("");
			if(q==1) {
				Use_Searching(c);   // Interface concept
			}
			else if(q==2) {
				c.Checkout();
			}
			else if(q==3) {
				Use_Reward(c);     // Interface concept
			}
			else if(q==4) {
				c.Latest_Ordered();
			}
			else if(q==5) {
				cust_exit=c.Exit();
			}
			else {
				System.out.println("-------INVALID INPUT-------");
			}
		}
		
		
	}
	
	private void User_Detail() {  // public
		System.out.println("M For Merchant Detail");
		System.out.println("C for Customer Detail");
		System.out.print("Enter your Type : ");
		Scanner pc=new Scanner(System.in);
		String s=pc.next();
		System.out.println("");
		if(s.toUpperCase().equals("M")) {
			System.out.print("Enter Merchant ID : ");
			int id=pc.nextInt();
			System.out.println("");
			int flag=-1;
			for(int i=0;i<5;i++) {
				if(merchant.get(i).get_id()==id) {
					flag=i;
					break;
				}
				else {
					continue;
				}
			}
			if(flag==-1) {
				System.out.println("INVALID ID");
			}
			else {
				System.out.println("Details of Merchant ID : " + id + " are here");
				System.out.println("Name: " + merchant.get(flag).get_name() + " Address : " + merchant.get(flag).get_address() + " Total contribution to Mercury's account : " + merchant.get(flag).get_Contribution_to_Mercury());
			}
			
		}
		else if(s.toUpperCase().equals("C")) {
			System.out.print("Enter Customer ID : ");
			int id=pc.nextInt();
			System.out.println("");
			int flag=-1;
			for(int i=0;i<5;i++) {
				if(customer.get(i).get_id()==id) {
					flag=i;
					break;
				}
				else {
					continue;
				}
			}
			if(flag==-1) {
				
				System.out.println("INVALID ID");
			}
			else {
				System.out.println("Details of Customer ID : " + id + " are here");
				System.out.println("Name : " + customer.get(flag).get_name() + " Address : " + customer.get(flag).get_address() + " Number of orders placed via the application : " + customer.get(flag).get_number_of_order_placed());
			}
			
		}
		else {
			System.out.println("----INVALID TYPE----");
		}
	}
	
	private void Account_balance() { // public
		double sum=0;
		for(int i=0;i<this.merchant.size();i++) {
			sum+=this.merchant.get(i).get_Contribution_to_Mercury();
		}
		this.set_balance(sum);
		System.out.println("Account Balance of Mercury_Inc : " +  2*this.get_balance());
	}
	
	private boolean Exit() {
		return this.get_exit();
	}
	
	private void Print_Merchant_Detail() {
		System.out.println("-----Welcome to Merchant Menu-----");
		System.out.println(" 1 For Add Item-------------------");
		System.out.println(" 2 For Edit Item------------------");
		System.out.println(" 3 For Search By Category---------");
		System.out.println(" 4 For Add Offer------------------");
		System.out.println(" 5 For Rewards Won----------------");
		System.out.println(" 6 For Exit-----------------------");
	}
	
	private void Print_Customer_Detail() {
		System.out.println("-----Welcome to Customer Menu-----");
		System.out.println(" 1 For Search Item----------------");
		System.out.println(" 2 For Checkout Cart--------------");
		System.out.println(" 3 For Reward Won-----------------");
		System.out.println(" 4 For Print Latest Order---------");
		System.out.println(" 5 For Exit-----------------------");
	}
	
	private void Print_Mercury_Detail() {  // public
		System.out.println("------Welcome to Mercury_Inc------");
		System.out.println(" 1 For Enter as Merchant----------");
		System.out.println(" 2 For Enter as Customer----------");
		System.out.println(" 3 For See User Detail------------");
		System.out.println(" 4 For Company Account Balance----");
		System.out.println(" 5 For Exit-----------------------");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		Mercury_Inc main=new Mercury_Inc();
		// add merchant
		main.Add_merchant("Jack" , "Delhi");
		main.Add_merchant("John" , "Mumbai");
		main.Add_merchant("James" , "Bangalore");
		main.Add_merchant("Jeff" , "Hyderabad");
		main.Add_merchant("Joseph" , "Gujrat");
		// add customer
		main.Add_customer("Ali" , "Bhopal");
		main.Add_customer("Nobby" , "Delhi");
		main.Add_customer("Bruno" , "Chennai");
		main.Add_customer("Borat" , "Goa");
		main.Add_customer("Aladeen" , "Kota");
		// merchant and customer added successfully
		
		// Application will be start
		while(main.Exit()) {
			main.Print_Mercury_Detail();
			System.out.print("Enter your Type : ");
			int q=sc.nextInt();
			System.out.println("");
			if(q==1) {
				main.go_to_merchant_query();
			}
			else if(q==2) {
				main.go_to_customer_query();
			}
			else if(q==3) {
				main.User_Detail();	
			}
			else if(q==4) {
				main.Account_balance();
			}
			else if(q==5) {
				main.set_exit(false);
			}
			else {
				System.out.println("-------INVALID INPUT-------");
			}
		}
		System.out.println("-------Application has been Terminated-------");
		
		
		

	}

}
