package bl;

import java.util.ArrayList;

// A node of chains 
public class HashNode<K, V>
{
    K key;
    V value;

    // Reference to next node 
    HashNode<K, V> next;

    // Constructor 
    public HashNode(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public HashNode<K, V> getNext() {
        return next;
    }

    public void setNext(HashNode<K, V> next) {
        this.next = next;
    }
}