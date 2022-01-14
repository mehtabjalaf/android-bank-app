package eecs1022.lab7.bank.model;

public class Client {

    private String clientName;
    private double clientBalance;
    private final String[] tempStatements = new String[10];
    private String[] statements;
    private int not = 1; // Number of Transactions

    public Client(String name, double bal){
        this.clientName = name;
        this.clientBalance = bal;
    }

    public String getStatus(){
        String result = String.format("%s: $%.2f", this.clientName, this.clientBalance);
        tempStatements[0] = result;
        return result;

    }

    public String getClientName() {
        return this.clientName;
    }

    public double getClientBalance() {
        return this.clientBalance;
    }

    public String[] getStatement(){

        this.statements = new String[this.not];
        for (int i = 0; i < this.not; i++) {
            this.statements[i] = this.tempStatements[i];
        }
        return this.statements;
    }

    public void deposit(double amount) {
        this.clientBalance += amount;
        this.tempStatements[this.not] = String.format("Transaction DEPOSIT: $%.2f", amount);
        this.not++;
    }

    public void withdraw(double amount) {
        this.clientBalance -= amount;
        this.tempStatements[this.not] = String.format("Transaction WITHDRAW: $%.2f", amount);
        this.not++;
    }


}
