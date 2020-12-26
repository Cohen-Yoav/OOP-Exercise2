package homework2;

public class NodeNotInGraphException extends Exception {
   public NodeNotInGraphException(){ super(); }
   public NodeNotInGraphException(String msg){ super(msg); }
   public NodeNotInGraphException(Throwable cause){ super(cause); }
   public NodeNotInGraphException(String msg, Throwable cause){ super(msg, cause); }
}
