package LLD.OOD.Behavioural.Observer;

import java.util.ArrayList;
import java.util.List;

abstract class Subject {

  abstract void registerObserver(Observer o);

  abstract void removeObserver(Observer o);

  abstract void notifyObservers();
}

class ConcreteSubject extends Subject {

  private ArrayList<Observer> observers = new ArrayList<Observer>();
  private int value = 0;

  @Override
  public void registerObserver(Observer o) {
    observers.add(o);
  }

  @Override
  public void removeObserver(Observer o) {
    observers.remove(o);
  }

  @Override
  public void notifyObservers() {
    for (Observer observer : observers) {
      observer.update(value);
    }
  }

  public void setValue(int value) {
    this.value = value;
    notifyObservers();
  }
}

interface Observer {
  public void update(int value);
}

class ConcreteObserver implements Observer {

  private int value;
  private Subject subject;

  public ConcreteObserver(Subject sSub) {
    subject = sSub;
    subject.registerObserver(this);
  }

  @Override
  public void update(int value) {
    this.value = value;
  }
}

/***********Scenario Implementation************/

interface Store {
  void addCustomer(Customer c);

  void removeCustomer(Customer c);

  void notifyCustomers();

  void updateQuantity(int quantity);
}

class BookStore implements Store {

  private List<Customer> customers = new ArrayList<Customer>();
  private int stockQuantity = 0;

  @Override
  public void addCustomer(Customer c) {
    customers.add(c);
  }

  @Override
  public void removeCustomer(Customer c) {
    customers.remove(c);
  }

  @Override
  public void notifyCustomers() {
    for (Customer customer : customers) {
      customer.update(stockQuantity);
    }
  }

  @Override
  public void updateQuantity(int quantity) {
    this.stockQuantity = quantity;
    notifyCustomers();
  }
}

interface Customer {
  public void update(int stockQuantity);
}

class BookCustomer implements Customer {

  private int observedStockQuantity;
  private Store store;

  public BookCustomer(Store store) {
    this.store = store;
    store.addCustomer(this);
  }

  @Override
  public void update(int stockQuantity) {
    this.observedStockQuantity = stockQuantity;
    if (stockQuantity > 0) {
      System.out.println(
        "Hello, A book you are interested in is back in stock!"
      );
    }
  }
}

class Main {

  public static void main(String[] args) {
    Store store = new BookStore();

    // Create email notifiers (observers) for two customers
    Customer customer1 = new BookCustomer(store);
    Customer customer2 = new BookCustomer(store);

    // Initially, the book is out of stock
    System.out.println("Setting stock to 0.");
    store.updateQuantity(0);
    // The book comes back in stock
    System.out.println("\nSetting stock to 5.");
    store.updateQuantity(5);

    // Remove customer1 from the notification list
    store.removeCustomer(customer1);

    // Simulate the situation where the stock changes again
    System.out.println("\nSetting stock to 2.");
    store.updateQuantity(2);
  }
}


