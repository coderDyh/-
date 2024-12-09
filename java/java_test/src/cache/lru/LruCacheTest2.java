package cache.lru;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 12:15
 * @description
 */
public class LruCacheTest2 {
    public static void main(String[] args) {
         LruCacheWithExpiration cache = new LruCacheWithExpiration(4, 5000);

        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        cache.put("key4", "value4");

        // 多次获取key2，使其成为最近使用的元素
        for (int i = 0; i < 3; i++) {
            System.out.println("第" + (i + 1) + "次获取key2的值: " + cache.get("key2"));
        }

         cache.put("key5", "value5");

        // 验证key2因频繁被获取而未被淘汰，仍能获取到
        System.out.println("获取key2的值: " + cache.get("key2"));

        // 验证最早放入且少被使用的元素（比如key1）被淘汰了
        System.out.println("获取key1的值: " + cache.get("key1"));

    }
}
