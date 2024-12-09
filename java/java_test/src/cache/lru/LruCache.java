package cache.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 12:42
 * @description 哈希map + 双向链表实现
 */
public class LruCache {
    class DlinkedNode{
        int key;
        int value;
        DlinkedNode pre;
        DlinkedNode next;
        public DlinkedNode(int key, int value){
            this.key = key;
            this.value = value;
        }
        public DlinkedNode(){}
    }
    private final Map<Integer, DlinkedNode> cache;
    private final int capacity;
    private DlinkedNode head, tail;

    public LruCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<Integer, DlinkedNode>();
        head = new DlinkedNode();
        tail = new DlinkedNode();
        head.next = tail;
        tail.pre = head;
    }

    //put
    public void put(int key, int value){
        DlinkedNode node ;
        if(cache.containsKey(key)){
            node = cache.get(key);
            node.value = value;
            moveToHead(node);
        }else {
            node = new DlinkedNode(key, value);
            if(cache.size() >= capacity ){
                removeLastUsed();
            }
            cache.put(key, node);
            addToHead(node);
        }
    }

    public int get(int key){
        if (cache.containsKey(key)){
            DlinkedNode node = cache.get(key);
            moveToHead(node);
            return node.value;
        }
        return -1;
    }

    /**
     * 删除
     * */
    public void removeLastUsed() {
        DlinkedNode lastUsed = tail.pre;
        removeNode(lastUsed);
        cache.remove(lastUsed.key);
    }

    /**
     * 删除指定node
     * */
    private void removeNode(DlinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }


    /**
     * 移到头结点
     * */
    private void moveToHead(DlinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    /**
     * 添加到头部节点
     * */
    private void addToHead(DlinkedNode node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

}
