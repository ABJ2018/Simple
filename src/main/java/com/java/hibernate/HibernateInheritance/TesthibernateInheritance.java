package com.java.hibernate.HibernateInheritance;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TesthibernateInheritance 
{
public static void main(String[] args) 
{
	Mobile m1=new Mobile(123123, "iphone", 78000);
	Android a1=new Android(123123, "x2", 75000, "playStore");
	Iphone i1=new Iphone(789, "IphoneX", 90000, "appstore");
	
	
	
	
	Configuration con=new Configuration();
   SessionFactory sf=con.configure("hibernate.cfg.xml").buildSessionFactory();
   Session  session=sf.openSession();
   
   
   Transaction tr=session.beginTransaction();
   
   session.save(m1);
   session.save(a1);
   session.save(i1);
  
   session.flush();
   tr.commit();
   
   
  


	
}
}
@Entity
@Table(name="mobile")
@DiscriminatorColumn(name="mobile_info")
//@Inheritance(strategy=InheritanceType.JOINED)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)

class Mobile
{@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
   private int imei;
   private String Model;
   private double price;
   
   
public int getImei() {
	return imei;
}
public void setImei(int imei) {
	this.imei = imei;
}
public String getModel() {
	return Model;
}
public void setModel(String model) {
	Model = model;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
@Override
public String toString() {
	return "\n Mobile [imei=" + imei + ", Model=" + Model + ", price=" + price + "]";
}
public Mobile(int imei, String model, double price) {
	super();
	this.imei = imei;
	Model = model;
	this.price = price;
}
public Mobile() {
	super();
	// TODO Auto-generated constructor stub
}
   
   



}
@Entity

class Android extends Mobile{
	
	private String playStore;

	public String getPlayStore() {
		return playStore;
	}

	public void setPlayStore(String playStore) {
		this.playStore = playStore;
	}

	public Android(int imei, String model, double price, String playStore) {
		super(imei, model, price);
		this.playStore = playStore;
	}

	public Android() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}	
@Entity


class Iphone extends Mobile
{
	
   private String itune;

public String getItune() {
	return itune;
}

public void setItune(String itune) {
	this.itune = itune;
}

@Override
public String toString() {
	return "\n Iphone [itune=" + itune + "]";
}

public Iphone() {
	super();
	// TODO Auto-generated constructor stub
}



public Iphone(int imei, String model, double price, String itune)
{
	super(imei, model, price);
	this.itune = itune;
}
   
   
   


}