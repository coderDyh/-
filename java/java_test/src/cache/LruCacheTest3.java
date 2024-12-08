package cache;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 12:15
 * @description
 */
public class LruCacheTest3 {
    public static void main(String[] args) {
         LruCacheWithExpiration cache = new LruCacheWithExpiration(3, 2000);

        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");

        // 更新key2的值，应将其移到最近使用的位置
        cache.put("key2", "new_value2");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cache.put("key4", "value4");

        // 验证更新后，key2未因LRU被淘汰，且旧值被替换
        System.out.println("获取key2的值: " + cache.get("key2"));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

          // 验证部分元素过期后，缓存的情况
        System.out.println("获取key1的值: " + cache.get("key1"));
        System.out.println("获取key3的值: " + cache.get("key3"));


    }
}
