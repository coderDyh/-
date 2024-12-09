package cache.lru;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 12:09
 * @description
 */
public class LruCacheTest1 {
    public static void main(String[] args) {
        LruCacheWithExpiration cache = new LruCacheWithExpiration(5, 3000);
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        System.out.println("获取key2的值: " + cache.get("key2"));

        // 模拟等待4秒，让部分数据过期
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 验证过期后获取不存在的键
        System.out.println("获取key1的值: " + cache.get("key1"));

        // 再次放入新键值对，触发LRU淘汰和过期清理
        cache.put("key6", "value6");

          // 验证被淘汰的键不存在
        System.out.println("获取key3的值: " + cache.get("key3"));

        // 验证新放入的键能获取到
        System.out.println("获取key6的值: " + cache.get("key6"));

    }
}
