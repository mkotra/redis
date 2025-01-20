import redis.clients.jedis.Jedis;

void main() {
    try (Jedis jedis = new Jedis("localhost", 6379)) {
        String script =
                """
                    local current = redis.call('GET', KEYS[1]) \
                    if not current then \
                        redis.call('SET', KEYS[1], ARGV[1]) \
                        return ARGV[1] \
                    else \
                        local new = current + ARGV[1] \
                        redis.call('SET', KEYS[1], new) \
                        return new \
                    end""";

        String key = "counter";
        String incrementValue = "5";

        // Run the Lua script
        Object result = jedis.eval(script, 1, key, incrementValue);
        System.out.println("New counter value: " + result);
    }
}