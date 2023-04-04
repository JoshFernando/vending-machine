
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class vendingMachine extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int give = 0;

	private JLabel allowedCoinslb;
	private JLabel allowedDrinksln;

	private JTextField balancelb;
	private JTextField selectedDrinklb;
	//private JTextField giveItem;
	//private JTextField giveChange;

	private JButton coinButton1;
	private JButton coinButton2;
	private JButton coinButton3;

	private JButton itemButton1;
	private JButton itemButton3;
	private JButton itemButton2;

	public class Customer{
		private int balance = 0;
		private String customerSelection = "";
		private int allow = 0;
		

		public void setBalance(int balance){
			this.balance = this.balance + balance;
		}

		public int getBalance(){
			return balance;
		}
		
		public void resetBalance() {
			this.balance = 0;
		}

		public void setCustomerSelected(String customerSelection){
			this.customerSelection = customerSelection;
		}

		public String getCustomerSelected(){
			return customerSelection;
		}

		public void setAllow(int allow){
			this.allow = allow;
		}

		public int getAllow(){
			return allow;
		}
	}

	Customer customer = new Customer();

	public class Items{
		private String itemName;
		private int itemPrice = 0;

		public Items(String itemName, int itemPrice){
			this.itemName = itemName;
			this.itemPrice = itemPrice;
		}

		public String getItemName(){
			return itemName;
		}

		public int getItemPrice(){
			return itemPrice;
		}
	}

	Items item1 = new Items("Coke", 10);
	Items item2 = new Items("Pepsi", 15);
	Items item3 = new Items("Royal", 25);

	public vendingMachine(){
		super("Vending Machine With GUI");
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);

		allowedCoinslb = new JLabel("Insert Coin/s");
		add(allowedCoinslb);

		coinButton1 = new JButton("P5 coin");
		coinButton2 = new JButton("P10 coin");
		coinButton3 = new JButton("P15 coin");

		add(coinButton1);
		add(coinButton2);
		add(coinButton3);

		allowedDrinksln = new JLabel("Menu");
		add(allowedDrinksln);

		itemButton1 = new JButton(item1.getItemName() + " P10");
		itemButton2 = new JButton(item2.getItemName() + " P15");
		itemButton3 = new JButton(item3.getItemName() + " P25");

		add(itemButton1);
		add(itemButton2);
		add(itemButton3);
		
		balancelb = new JTextField("Balance", 10);
		add(balancelb);
		
		selectedDrinklb = new JTextField("Selected Drink", 10);
		add(selectedDrinklb);
		
		/*giveItem = new JTextField("", 10);
		add(giveItem);
		
		giveChange = new JTextField("", 10);
		add(giveChange);*/

		theHandler handler = new theHandler();

		coinButton1.addActionListener(handler);
		coinButton2.addActionListener(handler);
		coinButton3.addActionListener(handler);

		itemButton1.addActionListener(handler);
		itemButton2.addActionListener(handler);
		itemButton3.addActionListener(handler);
	}

	public class theHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			
			String string = "";

			if(event.getSource()==coinButton1){
				balancelb.setText("");
				customer.setBalance(5);
				string = Integer.toString(customer.getBalance());
				balancelb.setText("Balance: " + string);
				System.out.println("Balance: " + customer.getBalance());
			}

			if(event.getSource()==coinButton2){
				balancelb.setText("");
				customer.setBalance(10);
				string = Integer.toString(customer.getBalance());
				balancelb.setText("Balance: " + string);
				System.out.println("Balance: " + customer.getBalance());
			}

			if(event.getSource()==coinButton3){
				balancelb.setText("");
				customer.setBalance(15);
				string = Integer.toString(customer.getBalance());
				balancelb.setText("Balance: " + string);
				System.out.println("Balance: " + customer.getBalance());
			}
			

			if(event.getSource()==itemButton1){
				selectedDrinklb.setText("");
				customer.setCustomerSelected(item1.getItemName());
				customer.setAllow(item1.getItemPrice());
				System.out.println("Allow " + customer.getAllow());
				selectedDrinklb.setText((customer.getCustomerSelected()));
				System.out.println("Selected: " + customer.getCustomerSelected());
				giveItem();
				
			}

			if(event.getSource()==itemButton2){
				selectedDrinklb.setText("");
				customer.setCustomerSelected(item2.getItemName());
				customer.setAllow(item2.getItemPrice());
				System.out.println("Allow " + customer.getAllow());
				selectedDrinklb.setText((customer.getCustomerSelected()));
				System.out.println("Selected: " + customer.getCustomerSelected());
				giveItem();
			}

			if(event.getSource()==itemButton3){
				selectedDrinklb.setText("");
				customer.setCustomerSelected(item3.getItemName());
				customer.setAllow(item3.getItemPrice());
				System.out.println("Allow " + customer.getAllow());
				selectedDrinklb.setText((customer.getCustomerSelected()));
				System.out.println("Selected: " + customer.getCustomerSelected());
				giveItem();
			}
		}
	} 

	public void giveItem(){
		int change = 0;
		
		//if(give == 1) {
		
			if((customer.getAllow()!=0)&&customer.getBalance()>=customer.getAllow()){
				change = customer.getBalance() - customer.getAllow();
				//giveItem.setText("Here is your " + customer.getCustomerSelected());
				JOptionPane.showMessageDialog(null, "Here is your " + customer.getCustomerSelected());
				if(change>0){
					//giveChange.setText("Change: " + Integer.toString(change));
					JOptionPane.showMessageDialog(null, "Here is your change " + Integer.toString(change));
					change = 0;
				}
				selectedDrinklb.setText("");
				balancelb.setText("");
				customer.resetBalance();
				give = 0;
			}
			else {
				JOptionPane.showMessageDialog(null, "You have insufficient balance! Add " + (customer.getAllow()-customer.getBalance()) + " coin/s");
				selectedDrinklb.setText("");
			}
		//}
		
		//else {
			//give = 0;
		//}
	}

	public static void main(String[] args){

		vendingMachine vm = new vendingMachine();
		vm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vm.setSize(350, 200);
		vm.setVisible(true);
	}
}
