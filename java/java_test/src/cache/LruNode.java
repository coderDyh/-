package cache;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 11:50
 * @description
 */
public class LruNode {
    public Object key;
    public Object value;
    public long timestamp;
    public LruNode(Object key, Object value) {
        this.key = key;
        this.value = value;
        this.timestamp = System.currentTimeMillis();
    }
}
