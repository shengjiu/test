package jedis;

import java.io.IOException;
import java.net.UnknownHostException;

import redis.clients.jedis.BinaryJedisPubSub;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.util.SafeEncoder;

public class JedisSub {

	private Jedis createJedis() {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		jedis.connect();
		return jedis;
	}

	public static void main(String[] args) throws Exception{
		JedisSub sub = new JedisSub();

		sub.subscribeSingle();

		sub.subscribeMany();

		sub.subscribeLazily();

		sub.psubscribe();
		sub.binarySubscribe();
		

	}

	public void subscribeSingle() {
		Jedis jedis = createJedis();
		jedis.subscribe(new JedisPubSub() {
			public void onMessage(String channel, String message) {

				System.out.println("subscribeSingle onMessage Channel: " + channel);
				System.out.println("subscribeSingle onMessage message: " + message);
				unsubscribe();
			}

			public void onSubscribe(String channel, int subscribedChannels) {
				System.out.println("subscribeSingle onSubscribe Channel: " + channel);
				System.out.println("subscribeSingle onSubscribe subscribedChannels: "
						+ subscribedChannels);
			}

			public void onUnsubscribe(String channel, int subscribedChannels) {
				System.out.println("subscribeSingle onUnsubscribe Channel: " + channel);
				System.out.println("subscribeSingle onUnsubscribe subscribedChannels: "
						+ subscribedChannels);
			}

			public void onPSubscribe(String pattern, int subscribedChannels) {
			}

			public void onPUnsubscribe(String pattern, int subscribedChannels) {
			}

			public void onPMessage(String pattern, String channel,
					String message) {
			}
		}, "foo");
	}

	public void subscribeMany() throws UnknownHostException, IOException {
		Jedis jedis = createJedis();
		jedis.subscribe(new JedisPubSub() {
			public void onMessage(String channel, String message) {

				System.out.println("subscribeMany onMessage Channel: " + channel);
				System.out.println("subscribeMany onMessage message: " + message);
				unsubscribe();
			}

			public void onSubscribe(String channel, int subscribedChannels) {
				System.out.println("subscribeMany onSubscribe Channel: " + channel);
				System.out.println("subscribeMany onSubscribe subscribedChannels: "
						+ subscribedChannels);
			}

			public void onUnsubscribe(String channel, int subscribedChannels) {
				System.out.println("subscribeMany onUnsubscribe Channel: " + channel);
				System.out.println("subscribeMany onUnsubscribe subscribedChannels: "
						+ subscribedChannels);
			}

			public void onPSubscribe(String pattern, int subscribedChannels) {
			}

			public void onPUnsubscribe(String pattern, int subscribedChannels) {
			}

			public void onPMessage(String pattern, String channel,
					String message) {
			}
		}, "foo", "bar");

	}

	public void psubscribe() throws UnknownHostException, IOException,
			InterruptedException {

		Jedis jedis = createJedis();

		jedis.psubscribe(new JedisPubSub() {
			public void onMessage(String channel, String message) {

				System.out.println("psubscribe onMessage Channel: " + channel);
				System.out.println("psubscribe onMessage message: " + message);
				unsubscribe();
			}

			public void onSubscribe(String channel, int subscribedChannels) {
			}

			public void onUnsubscribe(String channel, int subscribedChannels) {
			}

			public void onPSubscribe(String pattern, int subscribedChannels) {
				System.out.println("psubscribe onPSubscribe pattern: " + pattern);
				System.out.println("psubscribe onPSubscribe subscribedChannels :"
						+ subscribedChannels);
			}

			public void onPUnsubscribe(String pattern, int subscribedChannels) {
				System.out.println("psubscribe onPUnsubscribe pattern: " + pattern);
				System.out.println("psubscribe onPUnsubscribe subscribedChannels :"
						+ subscribedChannels);
			}

			public void onPMessage(String pattern, String channel,
					String message) {
				System.out.println("psubscribe onPMessage pattern:" + pattern);
				System.out.println("psubscribe onPMessage Channel:" + channel);
				System.out.println("psubscribe onPMessage message:" + message);
				punsubscribe();
			}
		}, "foo.*");

	}

	public void psubscribeMany() throws UnknownHostException, IOException {

		Jedis jedis = createJedis();

		jedis.psubscribe(new JedisPubSub() {
			public void onMessage(String channel, String message) {
				System.out.println("psubscribeMany onMessage Channel: " + channel);
				System.out.println("psubscribeMany onMessage message: " + message);
				unsubscribe();
			}

			public void onSubscribe(String channel, int subscribedChannels) {
			}

			public void onUnsubscribe(String channel, int subscribedChannels) {
			}

			public void onPSubscribe(String pattern, int subscribedChannels) {
				System.out.println("psubscribeMany onPSubscribe pattern: " + pattern);
				System.out.println("psubscribeMany onPSubscribe subscribedChannels :"
						+ subscribedChannels);
			}

			public void onPUnsubscribe(String pattern, int subscribedChannels) {
				System.out.println("psubscribeMany onPUnsubscribe pattern: " + pattern);
				System.out.println("psubscribeMany onPUnsubscribe subscribedChannels :"
						+ subscribedChannels);
			}

			public void onPMessage(String pattern, String channel,
					String message) {
				punsubscribe(pattern);
			}
		}, "foo.*", "bar.*");

	}

	public void subscribeLazily() throws UnknownHostException, IOException,
			InterruptedException {
		final JedisPubSub pubsub = new JedisPubSub() {
			public void onMessage(String channel, String message) {
				unsubscribe(channel);
			}

			public void onSubscribe(String channel, int subscribedChannels) {
			}

			public void onUnsubscribe(String channel, int subscribedChannels) {
			}

			public void onPSubscribe(String pattern, int subscribedChannels) {
			}

			public void onPUnsubscribe(String pattern, int subscribedChannels) {
			}

			public void onPMessage(String pattern, String channel,
					String message) {
				punsubscribe(pattern);
			}
		};

	}

	public void binarySubscribe() throws UnknownHostException, IOException,
			InterruptedException {

		Jedis jedis = createJedis();
		jedis.subscribe(new BinaryJedisPubSub() {
			public void onMessage(byte[] channel, byte[] message) {

				System.out.println("binarySubscribe onMessage Channel: " + new String(channel));
				System.out.println("binarySubscribe onMessage message: " + new String(message));
				unsubscribe();
			}

			public void onSubscribe(byte[] channel, int subscribedChannels) {
				System.out.println("binarySubscribe onSubscribe Channel: " + new String(channel));
				System.out.println("binarySubscribe onSubscribe subscribedChannels: "
						+ subscribedChannels);

			}

			public void onUnsubscribe(byte[] channel, int subscribedChannels) {
				System.out.println("binarySubscribe onUnsubscribe Channel: " + new String(channel));
				System.out.println("binarySubscribe onUnsubscribe subscribedChannels: "
						+ subscribedChannels);
			}

			public void onPSubscribe(byte[] pattern, int subscribedChannels) {
			}

			public void onPUnsubscribe(byte[] pattern, int subscribedChannels) {
			}

			public void onPMessage(byte[] pattern, byte[] channel,
					byte[] message) {
			}
		}, SafeEncoder.encode("foo"));

	}

}
