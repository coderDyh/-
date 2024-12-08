package cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 11:51
 * @description
 */
public class LruCacheWithExpiration {
    private int capacity;

    private final Map<Object, LruNode> cache;

    private final LinkedList<LruNode> linkedList;

    private final long expireTime;

    public LruCacheWithExpiration(int capacity, long expireTime) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.linkedList = new LinkedList<>();
        this.expireTime = expireTime;
    }

    //put
    public void put(Object key, Object val){
        LruNode node;
        if(cache.containsKey(key)){
            node = cache.get(key);
            node.value = val;
            node.timestamp = System.currentTimeMillis();
            linkedList.remove(node);
            linkedList.addFirst(node);
        }else {
            node = new LruNode(key, val);
            if(cache.size() >= capacity){
                removeExipredAndLastUsed();
            }
            cache.put(key, node);
            linkedList.addFirst(node);
        }
    }

    //删除过期的最近的
    public void removeExipredAndLastUsed() {
        while (!linkedList.isEmpty()){
            LruNode node = linkedList.getLast();
            if(isExpired(node)){
                linkedList.removeLast();
                cache.remove(node.key);
            }else {
                break;
            }
        }
        if(!linkedList.isEmpty()){
            LruNode nodeToRemove = linkedList.getLast();
            linkedList.removeLast();
            cache.remove(nodeToRemove.key);
        }
    }

    //是否过期
    public boolean isExpired(LruNode node) {
        return System.currentTimeMillis() - node.timestamp > expireTime;
    }

    //get
    public Object get(Object key){
        if(cache.containsKey(key)){
            LruNode node = cache.get(key);
            node.timestamp = System.currentTimeMillis();
            linkedList.remove(node);
            linkedList.addFirst(node);
            return node.value;
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
