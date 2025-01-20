import redis.clients.jedis.Jedis;

void main() {
    try (Jedis jedis = new Jedis("localhost", 6379)) {
        // Key for the list
        String listKey = "myList";

        // Clear any existing list
        jedis.del(listKey);

        // Add elements to the list
        jedis.lpush(listKey, "Task1", "Task2", "Task3");
        jedis.rpush(listKey, "Task4", "Task5");

        // Retrieve the full list
        System.out.println("Full List: " + jedis.lrange(listKey, 0, -1));

        // Get the length of the list
        System.out.println("List Length: " + jedis.llen(listKey));

        // Pop elements from the list
        System.out.println("LPOP: " + jedis.lpop(listKey)); // Removes Task3 (leftmost)
        System.out.println("RPOP: " + jedis.rpop(listKey)); // Removes Task5 (rightmost)

        // Check the updated list
        System.out.println("Updated List: " + jedis.lrange(listKey, 0, -1));
    }
}