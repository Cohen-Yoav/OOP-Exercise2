package homework2;

public class EmptyGraphException extends Exception {
   public EmptyGraphException(){ super(); }
   public EmptyGraphException(String msg){ super(msg); }
   public EmptyGraphException(Throwable cause){ super(cause); }
   public EmptyGraphException(String msg, Throwable cause){ super(msg, cause); }
}
