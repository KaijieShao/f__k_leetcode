package LLD.OOD.Behavioural.Strategy;

interface Lockable {
  void lock();
  void unlock();
}

class NonLocking implements Lockable {

  public void lock() {
    System.out.println("Door does not lock - ignoring");
  }

  public void unlock() {
    System.out.println("Door cannot unlock because it cannot lock");
  }
}

class Password implements Lockable {

  public void lock() {
    System.out.print("Door locked using password!");
  }

  public void unlock() {
    System.out.print("Door unlocked using password!");
  }
}

class KeyCard implements Lockable {

  public void lock() {
    // ...
  }

  public void unlock() {
    //...
  }
}

interface Openable {
  void open();
  void close();
}

class Standard implements Openable {

  public void open() {
    System.out.println("Pushing door open");
  }

  public void close() {
    System.out.println("Pulling door closed");
  }
}

class Revolving implements Openable {

  public void open() {
    //..
  }

  public void close() {
    //..
  }
}

class Sliding implements Openable {

  public void open() {
    //..
  }

  public void close() {
    //..
  }
}

abstract class Door {

  private Lockable lockBehavior;
  private Openable openBehavior;

  public void setLockBehavior(Lockable l) {
    this.lockBehavior = l;
  }

  public void setOpenBehavior(Openable o) {
    this.openBehavior = o;
  }

  public void performLock() {
    lockBehavior.lock();
  }

  public void performUnlock() {
    lockBehavior.unlock();
  }

  public void performOpen() {
    openBehavior.open();
  }

  public void performClose() {
    openBehavior.close();
  }

  public float getDimensions() {
    return 5;
  }
}

class ClosetDoor extends Door {
  //...
}

class ExternalDoor extends Door {
  //...
}

class SafeDepositDoor extends Door {
  //...
}

class SlidingDoor extends Door {
  //...
}

class Main {

  static void main(String[] args) {
    Door c;

    c = new ClosetDoor();
    c.setOpenBehavior(new Standard());
    c.setLockBehavior(new NonLocking());

    c.performOpen();
    c.performClose();

    c.performLock();
    c.performUnlock();

    // upgrade the door to a password protected door
    c.setLockBehavior(new Password());
    c.performLock();
    c.performUnlock();
  }
}
