package bl;

public class BPlusTree<TKey extends Comparable<TKey>, TValue> {
    private BPlusNode<TKey> root;

    public BPlusTree() {
        this.root = new BPlusLeafNode<TKey, TValue>();
    }

    public void insert(TKey key, TValue value) {
        BPlusLeafNode<TKey, TValue> leaf = this.findLeafNodeKey(key);
        leaf.insertKey(key, value);

        if (leaf.isOverflow()) {
            BPlusNode<TKey> n = leaf.dealOverflow();
            if (n != null)
                this.root = n;
        }
    }

    public TValue search(TKey key) {
        BPlusLeafNode<TKey, TValue> leaf = this.findLeafNodeKey(key);

        int index = leaf.search(key);
        return (index == -1) ? null : leaf.getValue(index);
    }

    public void delete(TKey key) {
        BPlusLeafNode<TKey, TValue> leaf = this.findLeafNodeKey(key);

        if (leaf.delete(key) && leaf.isUnderflow()) {
            BPlusNode<TKey> n = leaf.dealUnderflow();
            if (n != null)
                this.root = n;
        }
    }

    private BPlusLeafNode<TKey, TValue> findLeafNodeKey(TKey key) {
        BPlusNode<TKey> node = this.root;
        while (node.getNodeType() == BTreeNodeType.InnerNode) {
            node = ((BPlusInnerNode<TKey>)node).getChild( node.search(key) );
        }

        return (BPlusLeafNode<TKey, TValue>)node;
    }
}