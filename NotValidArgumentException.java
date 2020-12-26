package homework2;

public class NotValidArgumentException extends Exception {
   public NotValidArgumentException(){ super(); }
   public NotValidArgumentException(String msg){ super(msg); }
   public NotValidArgumentException(Throwable cause){ super(cause); }
   public NotValidArgumentException(String msg, Throwable cause){ super(msg, cause); }
}
