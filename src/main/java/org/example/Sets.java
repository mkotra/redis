import redis.clients.jedis.Jedis;

void main() {
    try (Jedis jedis = new Jedis("localhost", 6379)) {
        String setKey1 = "set1";
        String setKey2 = "set2";

        // Clear existing sets
        jedis.del(setKey1, setKey2);

        // Add members to the first set
        jedis.sadd(setKey1, "Apple", "Banana", "Cherry", "Date");
        System.out.println("Set1 Members: " + jedis.smembers(setKey1));

        // Add members to the second set
        jedis.sadd(setKey2, "Banana", "Cherry", "Elderberry", "Fig");
        System.out.println("Set2 Members: " + jedis.smembers(setKey2));

        // Check membership
        System.out.println("Is 'Apple' in Set1? " + jedis.sismember(setKey1, "Apple"));
        System.out.println("Is 'Fig' in Set1? " + jedis.sismember(setKey1, "Fig"));

        // Set Operations
        System.out.println("Union of Set1 and Set2: " + jedis.sunion(setKey1, setKey2));
        System.out.println("Intersection of Set1 and Set2: " + jedis.sinter(setKey1, setKey2));
        System.out.println("Difference of Set1 and Set2: " + jedis.sdiff(setKey1, setKey2));
    }
}