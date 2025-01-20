import redis.clients.jedis.Jedis;

void main() {
    try (Jedis jedis = new Jedis("localhost", 6379)) {

        // Redis hash key
        String hashKey = "user:1";

        // Adding fields to the hash
        jedis.hset(hashKey, "name", "John Doe");
        jedis.hset(hashKey, "email", "john.doe@example.com");
        jedis.hset(hashKey, "age", "29");

        // Fetching individual fields from the hash
        String name = jedis.hget(hashKey, "name");
        String email = jedis.hget(hashKey, "email");
        String age = jedis.hget(hashKey, "age");

        System.out.printf("""
                User Details:
                --------------
                Name  : %s
                Email : %s
                Age   : %s
                """, name, email, age);

        // Fetching all fields and values from the hash
        java.util.Map<String, String> userDetails = jedis.hgetAll(hashKey);
        System.out.println("All User Details: " + userDetails);

        // Modifying a field in the hash
        jedis.hincrBy(hashKey, "age", 20);
        System.out.printf("Updated age: %s%n", jedis.hget(hashKey, "age"));

        // Deleting a field from the hash
        jedis.hdel(hashKey, "email");
        System.out.println("Deleted 'email' field");

        // Checking if a field exists
        boolean emailExists = jedis.hexists(hashKey, "email");
        System.out.printf("Does 'email' field exist? : %b%n", emailExists);

        // Deleting the entire hash
        jedis.del(hashKey);
        System.out.println("Deleted entire user hash");
    }
}

