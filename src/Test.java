class Customer{  
int amount=10000;  
  
synchronized void withdraw(int amount){  
System.out.println("going to withdraw...");  
  
while(this.amount<amount){  
System.out.println("Less balance; waiting for deposit...");  
try{wait();}catch(Exception e){}  
}  
this.amount-=amount;  
System.out.println("withdraw completed... amount is "+this.amount);  
}  
  
synchronized void deposit(int amount){  
System.out.println("going to deposit..."+amount);  
this.amount+=amount;  
System.out.println("deposit completed... ");  
notify();  
}  
}  
  
class Test{  
public static void main(String args[]){  
final Customer c=new Customer();  
new Thread(){  
public void run(){c.withdraw(15000);}  
}.start();  
new Thread(){  
public void run(){c.deposit(3000);}  
}.start();  

new Thread(){  
public void run(){c.deposit(4000);}  
}.start();  


}}  