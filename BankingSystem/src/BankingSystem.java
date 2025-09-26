import java.util.Scanner;

// Abstract class (Abstraction)
abstract class Account {
    private String name;
    private long accountNumber;
    protected double balance; // protected so child classes can access

    public Account(String name, long accountNumber, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Common method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // Abstract method 
    public abstract void withdraw(double amount);

    public void showBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }
}

// SavingsAccount 
class SavingsAccount extends Account //inherit Account class
{
    public SavingsAccount(String name, long accountNumber, double balance) {
        super(name, accountNumber, balance);
    }

    // Normal withdraw
    @Override
    public void withdraw(double amount) {//polymorphism
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully from Savings Account.");
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }
}

// CurrentAccount 
class CurrentAccount extends Account //inherit Account class
{
    private final double MIN_BALANCE = 1000; // must maintain minimum balance

    public CurrentAccount(String name, long accountNumber, double balance) {
        super(name, accountNumber, balance);
    }

    // Withdraw with minimum balance check
    @Override
    public void withdraw(double amount) { //polymorphism 
        if (amount > 0 && (balance - amount) >= MIN_BALANCE) {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully from Current Account.");
        } else {
            System.out.println("Cannot withdraw! Must maintain minimum balance of ₹" + MIN_BALANCE);
        }
    }
}

// user interface
public class BankingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Account account = null; // abstraction: parent reference, child object

        while (true) {
            System.out.println("\n===== Banking System Menu =====");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Current Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Check Balance");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    if (account == null) {
                        System.out.print("Enter Name: ");
                        sc.nextLine(); 
                        String name = sc.nextLine();
                        System.out.print("Enter Account Number: ");
                        long accNum = sc.nextLong();
                        System.out.print("Enter Initial Balance: ");
                        double bal = sc.nextDouble();
                        account = new SavingsAccount(name, accNum, bal);
                        System.out.println("Savings Account created successfully!");
                    } else {
                        System.out.println("Account already exists!");
                    }
                    break;

                case 2:
                    if (account == null) {
                        System.out.print("Enter Name: ");
                        sc.nextLine(); 
                        String name = sc.nextLine();
                        System.out.print("Enter Account Number: ");
                        long accNum = sc.nextLong();
                        System.out.print("Enter Initial Balance: ");
                        double bal = sc.nextDouble();
                        account = new CurrentAccount(name, accNum, bal);
                        System.out.println("Current Account created successfully!");
                    } else {
                        System.out.println("Account already exists!");
                    }
                    break;

                case 3:
                    if (account != null) {
                        System.out.print("Enter deposit amount: ");
                        double dep = sc.nextDouble();
                        account.deposit(dep); // polymorphism in action
                    } else {
                        System.out.println("No account found! Please create an account first.");
                    }
                    break;

                case 4:
                    if (account != null) {
                        System.out.print("Enter withdrawal amount: ");
                        double wd = sc.nextDouble();
                        account.withdraw(wd); // polymorphism in action
                    } else {
                        System.out.println("No account found! Please create an account first.");
                    }
                    break;

                case 5:
                    if (account != null) {
                        account.showBalance();
                    } else {
                        System.out.println("No account found! Please create an account first.");
                    }
                    break;

                case 6:
                    System.out.println("Thank you for using Banking System!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
//Single Responsibility -Each class has only one responsibility (Account for data, SavingsAccount/CurrentAccount for specific rules, BankingSystem for UI).
//Open/Closed Principle -System is open for extension (add new account type) but closed for modification (no need to change Account).
//Liskov Substitution - Child classes can replace parent (SavingsAccount/CurrentAccount can be used wherever Account is expected).
//Interface Segregation -abstract class defines only relevant methods, not forcing unnecessary ones.
//Dependency Inversion - BankingSystem depends on abstraction (Account) not concrete classes.

