public class SalesTaxCalculator {
    public static void main(String[] args) {
        String stateName = args[0].trim(); //gets the name of the state from the command line
        double saleAmount = Double.parseDouble(args[1]); //gets the amount of the sale from the command line
        State state; //variable to store the state object

        if (stateName.equalsIgnoreCase("Alaska")) { //if the state is Alaska
            state = new Alaska(); //create a new Alaska object
        }
        else if (stateName.equalsIgnoreCase("Indiana")) { //if the state is Indiana
            state = new Indiana(); //create a new Indiana object
        }
        else if (stateName.equalsIgnoreCase("Hawaii")) { //if the state is Hawaii
            state = new Hawaii(); //create a new Hawaii object
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

    public void showTax(Double value) { //method to show the sales tax for the state
        SalesTaxBehavior salesTaxBehavior; //variable to store the behavior for calculating sales tax

        if (getName().equalsIgnoreCase("Alaska")) { //if the state is Alaska
            salesTaxBehavior = new NoTax(); //set the behavior to NoTax
        }
        else if (getName().equalsIgnoreCase("Indiana")) { //if the state is Indiana
            salesTaxBehavior = new SevenPercent(); //set the behavior to SevenPercent
        }
        else if (getName().equalsIgnoreCase("Hawaii")) { //if the state is Hawaii
            salesTaxBehavior = new FourPercent(); //set the behavior to FourPercent
        }
        else { //if the state is not Alaska or Indiana
            System.out.println("Invalid state name "); //print an error message
            return; //exit the method
        }

        double tax = salesTaxBehavior.compute(value); //calculate the sales tax using the appropriate behavior
        System.out.printf("The sales tax on $%.2f in %s is $%.2f.%n", value, getName(), tax); //print the sales tax on a value for the specific state
    }
}

class Alaska extends State { //class to set up the Alaska object
    @Override //override the method in State
    public void showTax(Double value) { //method to show the sales tax for Alaska
        super.showTax(value);
    }
}

class Indiana extends State { //class to set up the Indiana object
    @Override //override the method in State
    public void showTax(Double value) { //method to show the sales tax for Indiana
        super.showTax(value);
    }
}

class Hawaii extends State { //class to set up the Hawaii object
    @Override //override the method in State
    public void showTax(Double value) { //method to show the sales tax for Hawaii
        super.showTax(value);
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

class FourPercent implements SalesTaxBehavior { //this method is used to calculate the amount of sales tax for a state that has a 4% sales tax
    @Override //override the method in SalesTaxBehavior
    public Double compute(Double value) { //the method from SalesTaxBehavior but with the appropriate calculation for the state
        return value * 0.04; //return the value multiplied by 0.04, as the sales tax is 4%
    }
}
