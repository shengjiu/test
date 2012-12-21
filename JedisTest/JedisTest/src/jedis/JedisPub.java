package jedis;



import redis.clients.jedis.Jedis;
import redis.clients.util.SafeEncoder;

public class JedisPub {
	
	private static Jedis createJedis(){
    	Jedis jedis = new Jedis("127.0.0.1", 6379);
	    jedis.connect();
	    return jedis;
	}
    
    public static void main(String [] args) throws InterruptedException{
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                	Jedis jedis = createJedis();
                	
                	for(int i = 0; i < 10000; i++){
                    
	                    Thread.sleep(1000);
	                    jedis.publish("foo", "foo:" + System.currentTimeMillis());
	                    Thread.sleep(1000);
	                    jedis.publish("bar", "bar:" + System.currentTimeMillis());
	                    Thread.sleep(1000);
	                    jedis.publish("foo.bar", "exit");
	                    Thread.sleep(1000);
	                    jedis.publish("bar.123", "exit");
	                    Thread.sleep(1000);
	                    jedis.publish(SafeEncoder.encode("foo"), SafeEncoder
	                            .encode("exit"));
                	}
                } catch (Exception ex) {
                    
                }
            }
        });
        t.start();
        t.join();
    }

    
 
}