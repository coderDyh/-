package cache.lfu;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/9 09:35
 * @description LFU 缓存 双哈希
 */
public class LFUCache {
    private int capacity;
    private final Map<Integer, LFuNode> keyMap = new HashMap<>();
    private final Map<Integer, LFuNode> freqMap = new HashMap<>();
    private int minFreq = 0;
    private LFUCache (int capacity) {
        this.capacity = capacity;
    }

    //get
    public int get (int key) {
        LFuNode node = getNode(key);
        return node == null ? -1 : node.value;
    }

    /**
     * get Node
     * */
    public LFuNode getNode(int key) {
        if (!keyMap.containsKey(key)) {
            return null;
        }
        LFuNode node = keyMap.get(key);
        remove(node);
        LFuNode dummy = freqMap.get(node.frequence);
        if (dummy.prev == dummy){
            freqMap.remove(node.frequence);
            if (minFreq == node.frequence){
                minFreq ++;
            }
        }
        pushTop(++node.frequence, node);
        return node;
    }

    private void pushTop(int freq, LFuNode node) {
        LFuNode lFuNode = freqMap.computeIfAbsent(freq, k->newList());
        node.prev = lFuNode;
        node.next = lFuNode.next;
        node.prev.next = node;
        node.next.prev = node;
    }

    public LFuNode newList(){
        LFuNode dummy = new LFuNode(0,0);
        dummy.prev = dummy;
        dummy.next = dummy;
        return dummy;
    }

    public void remove(LFuNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    //put
    public void put (int key, int value) {
        LFuNode node = getNode(key);
        if (node != null) {
            node.value = value;
            return;
        }
        if (keyMap.size() >= capacity) {
            LFuNode node1 = freqMap.get(minFreq);
            LFuNode node2 = node1.prev;
            freqMap.remove(node2.key);
            remove(node2);
            if (node1.prev ==null){
                freqMap.remove(minFreq);
            }
        }
        node = new LFuNode(key, value);
        keyMap.put(key, node);
        pushTop(1,node);
        minFreq = 1;
    }
}
