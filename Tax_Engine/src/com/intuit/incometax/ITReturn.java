package com.intuit.incometax;

import java.util.Scanner;

public class ITReturn {

	/**
	 * @param args
	 */
	
	 String firstName,lastName;
	 double income,gross, compensation,taxable,eic,healthCare ;
	 int status;

	//Allowance
   	 double singleAllowance = 10300;
   	 double coupleAllowance = 20600;
	
	// Tax rates
	 final double RATE1 = 0.15;      
   	 final double RATE2 = 0.25;
   	 final double RATE3 = 0.31;
	
   	 // Tax brackets for single
   	 final double S1 = 21450.0;      
   	 final double S2 = 51900.0;
   	 
   	 // Tax brackets for married
   	 final double M1 = 35800.0;      
   	 final double M2 = 86500.0;

   	 

   	 
   	 
   	 
	
	public void inputData(){
		Scanner reader=null;
		try{			
		reader = new Scanner(System.in);  
		System.out.print("Please enter your first name and initial: ");
		firstName = reader.nextLine();			
		System.out.print("Please enter your last name : ");
		lastName = reader.next();
		System.out.print("Please enter your status : 0 single 1 Married");
		status = reader.nextInt();
		System.out.print("Please enter your income : ");
		income = reader.nextDouble();
		System.out.println("Please enter your following compensation else enter 0 ): ");
		System.out.print("Unemployment compensation and Alaska permanten fund dividends: ");
		compensation = reader.nextDouble();		
		System.out.println("Please enter your taxable interest not more than 1500 ): ");
		taxable = reader.nextDouble();		 
		 if(taxable>1500){
			 System.out.println("Sorry you cannot use this form  ");
			 System.exit(0);
		 }
			
		System.out.println("Please enter your EIC else enter 0 ): ");
		eic = reader.nextDouble();
		System.out.println("Please enter your HealthCare amount else enter 0 ): ");
		healthCare = reader.nextDouble();
		
		
		}
		catch(Exception ioe){
			System.err.println("Exception is"+ioe);
		}
		finally{
			
			if(reader!=null)
				reader.close();
		}				
	}
	
	
	
 public double calculateFederalTax(double taxableincome){
		
		double ftax;
		 
		if ( status == 0)
    {    // tax for single filers
       if ( taxableincome <= S1 )
          ftax = RATE1 * taxableincome;
       else if ( taxableincome <= S2 )
          ftax = RATE1 * S1 
                + RATE2 * (taxableincome - S1);               
       else
          ftax = RATE1 * S1
                + RATE2 * (S2 - S1)     
                + RATE3 * (taxableincome - S2);
    }
    else
    {  // tax for married
       if ( taxableincome <= M1 )
          ftax = RATE1 * taxableincome;
       else if ( taxableincome <= M2 )
          ftax = RATE1 * M1
                + RATE2 * (taxableincome - M1);
       else
          ftax = RATE1 * M1
                + RATE2 * (M2 - M1)
                + RATE3 * (taxableincome - M2);
    }

			return ftax;
	}
 
 
 public double calculateStateTax(double taxableincome){	 
 
	 final double GA_TAX_1 = 0.06;
	 final double GA_TAX_2 = 230;
	 
	 return (taxableincome * GA_TAX_1) + GA_TAX_2; 
	  
 }
 
 
 
 public void taxReturn(){
	 
	 
	 // income	 
	 gross = income+taxable+compensation;	
	 System.out.printf("Wages, Salary, Tips: $ %8.2f\n", gross);
	 System.out.printf("Taxable Interest: $ %8.2f\n", taxable);

	 
	 //taxable income
	 double taxableincome = 0;
	 if ( status == 0)
	 taxableincome= gross - singleAllowance ;
	 else 
		 taxableincome= gross - coupleAllowance ;
	 
	 	if(taxableincome < 0) taxableincome =0;	 	
	 	
	 //payment credit tax 
	 	
	 	double totalPaymentCredit = this.calculateFederalTax(taxableincome) + eic; 

	// Total Tax
	 	
	  double totalTax = this.calculateStateTax(taxableincome) + healthCare;
	 	
	//Refund
	  
	 // double refund =  totalPaymentCredit - totalTax
			  
		if(totalPaymentCredit > totalTax )	  
			System.out.printf("Refund: $ %8.2f\n",(totalPaymentCredit-totalTax));
		else	
			System.out.printf("Amount You Owe: $ %8.2f\n ",(totalTax-totalPaymentCredit));	 
	 }
	 	 
	 
	
	
 public void display(){	 
		System.out.println("First name is: "+this.firstName);		
		System.out.println("Last name is: "+this.lastName);
		this.taxReturn();
		
 }
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		ITReturn tc=  new ITReturn();
		tc.inputData();		
		tc.display();

	}

}
