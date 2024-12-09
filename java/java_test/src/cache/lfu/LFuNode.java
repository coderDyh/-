package cache.lfu;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/9 09:32
 * @description LRU node
 */
public class LFuNode {
    int key, value, frequence = 1;
    LFuNode prev, next;
    public LFuNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
