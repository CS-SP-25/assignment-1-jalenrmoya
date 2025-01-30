public class SalesTaxCalculator {
    public static void main(String[] args) {
        String stateName = args[0].trim(); //gets the name of the state from the command line
        double saleAmount = Double.parseDouble(args[1]); //gets the amount of the sale from the command line
        State state; //variable to store the state object

        if (stateName.equalsIgnoreCase("Alaska")) { //if the state is Alaska
            state = new State(stateName, new NoTax()); //create a new state object with the name and the appropriate behavior
        }
        else if (stateName.equalsIgnoreCase("Indiana")) { //if the state is Indiana
            state = new State(stateName, new SevenPercent()); //create a new state object with the name and the appropriate behavior
        }
        else if (stateName.equalsIgnoreCase("Hawaii")) { //if the state is Hawaii
            state = new State(stateName, new FourPointFivePercent()); //create a new state object with the name and the appropriate behavior
        }
        else { //if the state is not Alaska or Indiana then print an error message
            System.out.println(stateName);
            System.out.println("Invalid state name " + stateName);
            return;
        }
        state.setName(stateName); //set the name of the state
        state.showTax(saleAmount); //show the sales tax for the state
    }
}

class State { //class to set up the state object
    private String name; //variable to store the name of the state
    public String getName(){ //method to get the name of the state
        return name;
    }
    public void setName(String name){ //method to set the name of the state
        this.name = name;
    }
    public SalesTaxBehavior salesTaxBehavior;

    public void showTax(Double value) { //method to show the sales tax for the state
        double tax = salesTaxBehavior.compute(value); //calculate the sales tax using the appropriate behavior
        System.out.printf("The sales tax on $%.2f in %s is $%.2f.%n", value, getName(), tax); //print the sales tax on a value for the specific state
    }

    public State(String name, SalesTaxBehavior salesTaxBehavior) { //constructor for the State object
        this.name = name; //set the name of the state
        this.salesTaxBehavior = salesTaxBehavior; //set the behavior for calculating sales tax
    }
}

interface SalesTaxBehavior { //interface to set up the behavior for calculating Sales Tax, depending on the state
    Double compute(Double value); //method to calculate the sales tax
}

class NoTax implements SalesTaxBehavior { //this method is used to calculate the amount of sales tax for a state that has no sales tax
    @Override //override the method in SalesTaxBehavior
    public Double compute(Double value) { //the method from SalesTaxBehavior but with the appropriate calculation for the state
        return 0.00; //return 0.00, as there is no sales tax
    }
}

class SevenPercent implements SalesTaxBehavior { //this method is used to calculate the amount of sales tax for a state that has a 7% sales tax
    @Override //override the method in SalesTaxBehavior
    public Double compute(Double value) { //the method from SalesTaxBehavior but with the appropriate calculation for the state
        return value * 0.07; //return the value multiplied by 0.07, as the sales tax is 7%
    }
}

class FourPointFivePercent implements SalesTaxBehavior { //this method is used to calculate the amount of sales tax for a state that has a 4% sales tax
    @Override //override the method in SalesTaxBehavior
    public Double compute(Double value) { //the method from SalesTaxBehavior but with the appropriate calculation for the state
        return value * 0.045; //return the value multiplied by 0.04, as the sales tax is 4%
    }
}
