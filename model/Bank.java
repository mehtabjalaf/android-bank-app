package eecs1022.lab7.bank.model;

public class Bank {

    private String status = "Accounts: {}";
    private Client[] list = new Client[6];
    private Client[] clients;
    private int noa = 0; // Number of Accounts

    public String getStatus() {
        return this.status;
    }

    public String[] getStatement(String name) {

        boolean here = false;
        for (int i = 0; i < this.noa; i++) {
            if (this.list[i].getClientName().equals(name)){
                here = true;
                break;
            }
        }

        if (!here) {
            this.status = String.format("Error: From-Account %s does not exist", name);
        }

        String[] result = null;

        if (this.noa == 0) {
            this.status = String.format("Error: From-Account %s does not exist", name);
        }

        else {

//            result = new String[this.noa];

            for (int i = 0; i < this.noa; i++) {
                if (this.list[i].getClientName().equals(name)) {
                    result = this.list[i].getStatement();
                }
            }
        }
        return result;
    }

    public void deposit(String name, double amount) {

        boolean here = false;
        for (int i = 0; i < this.noa; i++) {
            if (this.list[i].getClientName().equals(name)){
                here = true;
                break;
            }
        }

        if (!here) {
            this.status = String.format("Error: To-Account %s does not exist", name);
        }

        else if (amount <= 0) {
            this.status = "Error: Non-Positive Amount";
        }

        else {
            for (int i = 0; i < this.noa; i++) {

                if (this.list[i].getClientName().equals(name)) {
                    this.list[i].deposit(amount);
                    break;
                }
            }
            String result = "";
            for (int m = 0; m < this.noa; m++) {

                if (m < this.noa - 1) {
                    result += this.list[m].getStatus();
                    result += ", ";
                }

                else {
                    result += this.list[m].getStatus();
                }

            }
            this.status = String.format("Accounts: {%s}", result);
        }
    }

    public void withdraw(String name, double amount) {

        boolean here = false;
        for (int i = 0; i < this.noa; i++) {
            if (this.list[i].getClientName().equals(name)){
                here = true;
                break;
            }
        }

        if (!here) {
            this.status = String.format("Error: From-Account %s does not exist", name);
        }

        else if (amount <= 0) {
            this.status = "Error: Non-Positive Amount";
        }

        else {
            for (int i = 0; i < this.noa; i++) {
                if (this.list[i].getClientName().equals(name)) {
                        if(this.list[i].getClientBalance() < amount) {
                            this.status = "Error: Amount too large to withdraw";
                            break;
                        }
                    this.list[i].withdraw(amount);
                    String result = "";
                    for (int m = 0; m < this.noa; m++) {

                        if (m < this.noa - 1) {
                            result += this.list[m].getStatus();
                            result += ", ";
                        }

                        else {
                            result += this.list[m].getStatus();
                        }

                    }
                    this.status = String.format("Accounts: {%s}", result);
                }
            }
        }
    }

    public void transfer(String from,String to, double amount) {

        boolean fromM = false;
        for (int i = 0; i < this.noa; i++) {
            if (this.list[i].getClientName().equals(from)){
                fromM = true;
                break;
            }
        }

       boolean toO = false;
       for (int i = 0; i < this.noa; i++) {
           if (this.list[i].getClientName().equals(to)){
               toO = true;
               break;
           }
        }


        if (!fromM) {
            this.status = String.format("Error: From-Account %s does not exist", from);
        }

        else if (!toO) {
            this.status = String.format("Error: To-Account %s does not exist", to);
        }

        else if (amount <= 0) {
           this.status = "Error: Non-Positive Amount";
        }

        else {

            for (int i = 0; i < this.noa; i++) {

                if (this.list[i].getClientName().equals(from)) {
                        if (this.list[i].getClientBalance() < amount) {
                            this.status = "Error: Amount too large to transfer";
                            break;
                        }
                    this.list[i].withdraw(amount);

                    for (int x = 0; x < this.noa; x++) {

                        if (this.list[x].getClientName().equals(to)) {
                            this.list[x].deposit(amount);
                            String result = "";
                            for (int m = 0; m < this.noa; m++) {

                                if (m < this.noa - 1) {
                                    result += this.list[m].getStatus();
                                    result += ", ";
                                }

                                else {
                                    result += this.list[m].getStatus();
                                }

                            }
                            this.status = String.format("Accounts: {%s}", result);
                        }


                    }
                }
            }

        }
    }

    public void addClient(String name, double balance) {

        boolean here = false;
        for (int i = 0; i < this.noa; i++) {
            if (this.list[i].getClientName().equals(name)){
                here = true;
                break;
            }
        }


        if (this.noa == 6) {
            this.status = "Error: Maximum Number of Accounts Reached";
        }

        else if (here) {
            this.status = String.format("Error: Client %s already exists", name);
        }


        else if  (balance <= 0) {
            this.status = "Error: Non-Positive Initial Balance";
        }

        else {
            this.list[this.noa] = new Client (name, balance);
            this.noa++;

            String result = "";
            for (int m = 0; m < this.noa; m++) {

                if (m < this.noa - 1) {
                    result += this.list[m].getStatus();
                    result += ", ";
                }

                else {
                    result += this.list[m].getStatus();
                }

            }

            this.status = String.format("Accounts: {%s}", result);



        }


    }




}
