import redis.clients.jedis.Jedis;

void main() {
    try (Jedis jedis = new Jedis("localhost", 6379)) {
        // Set a key
        jedis.set("greeting", "Hello, Redis!");

        // Get the value of the key
        String value = jedis.get("greeting");
        System.out.println("Stored value: " + value);

        // Increment a numeric key
        jedis.set("counter", "1");
        jedis.incr("counter");
        System.out.println("Counter: " + jedis.get("counter"));

        // Delete a key
        jedis.del("greeting");
        System.out.println("Deleted 'greeting'. Exists? " + jedis.exists("greeting"));
    }
}