import redis.clients.jedis.Jedis;

void main() {
    try (Jedis jedis = new Jedis("localhost", 6379)) {
        String zsetKey = "leaderboard";

        // Clear existing ZSet
        jedis.del(zsetKey);

        // Add members with scores
        jedis.zadd(zsetKey, 50, "Player1");
        jedis.zadd(zsetKey, 70, "Player2");
        jedis.zadd(zsetKey, 30, "Player3");
        jedis.zadd(zsetKey, 90, "Player4");

        // Get all members (ascending by score)
        System.out.println("Leaderboard (ascending): " + jedis.zrangeWithScores(zsetKey, 0, -1));

        // Get all members (descending by score)
        System.out.println("Leaderboard (descending): " + jedis.zrevrangeWithScores(zsetKey, 0, -1));

        // Get rank of a specific member
        System.out.println("Rank of Player3: " + jedis.zrank(zsetKey, "Player3")); // 0-based index
        System.out.println("Rank of Player3 (reverse): " + jedis.zrevrank(zsetKey, "Player3"));

        // Get score of a member
        System.out.println("Score of Player2: " + jedis.zscore(zsetKey, "Player2"));

        // Get members with scores within a range
        System.out.println("Players with scores between 40 and 100: " + jedis.zrangeByScoreWithScores(zsetKey, 40, 100));

        // Remove a member
        jedis.zrem(zsetKey, "Player3");
        System.out.println("After removing Player3: " + jedis.zrangeWithScores(zsetKey, 0, -1));
    }
}
