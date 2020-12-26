package homework2;

public class NoChildrenException extends Exception {
      public  NoChildrenException(){ super(); }
      public NoChildrenException(String msg){ super(msg); }
      public NoChildrenException(Throwable cause){ super(cause); }
      public  NoChildrenException(String msg, Throwable cause){ super(msg, cause); }
}
