package bl;

public class BPlusLeafNode<TKey extends Comparable<TKey>, TValue> extends BPlusNode<TKey> {
    protected final static int LEAFORDER = 4;
    private Object[] values;

    public BPlusLeafNode() {
        this.keys = new Object[LEAFORDER + 1];
        this.values = new Object[LEAFORDER + 1];
    }

    public TValue getValue(int index) {
        return (TValue)this.values[index];
    }

    public void setValue(int index, TValue value) {
        this.values[index] = value;
    }

    @Override
    public BTreeNodeType getNodeType() {
        return BTreeNodeType.LeafNode;
    }

    @Override
    public int search(TKey key) {
        for (int i = 0; i < this.getKeyCount(); ++i) {
            int cmp = this.getKey(i).compareTo(key);
            if (cmp == 0) {
                return i;
            }
            else if (cmp > 0) {
                return -1;
            }
        }

        return -1;
    }


    /* The codes below are used to support insertion operation */

    public void insertKey(TKey key, TValue value) {
        int index = 0;
        while (index < this.getKeyCount() && this.getKey(index).compareTo(key) < 0)
            ++index;
        this.insertAt(index, key, value);
    }

    private void insertAt(int index, TKey key, TValue value) {
        // move space for the new key
        for (int i = this.getKeyCount() - 1; i >= index; --i) {
            this.setKey(i + 1, this.getKey(i));
            this.setValue(i + 1, this.getValue(i));
        }

        // insert new key and value
        this.setKey(index, key);
        this.setValue(index, value);
        ++this.keyCount;
    }

    @Override
    protected BPlusNode<TKey> split() {
        int midIndex = this.getKeyCount() / 2;

        BPlusLeafNode<TKey, TValue> newRNode = new BPlusLeafNode<TKey, TValue>();
        for (int i = midIndex; i < this.getKeyCount(); ++i) {
            newRNode.setKey(i - midIndex, this.getKey(i));
            newRNode.setValue(i - midIndex, this.getValue(i));
            this.setKey(i, null);
            this.setValue(i, null);
        }
        newRNode.keyCount = this.getKeyCount() - midIndex;
        this.keyCount = midIndex;

        return newRNode;
    }

    @Override
    protected BPlusNode<TKey> pushUpKey(TKey key, BPlusNode<TKey> leftChild, BPlusNode<TKey> rightNode) {
        throw new UnsupportedOperationException();
    }

    public boolean delete(TKey key) {
        int index = this.search(key);
        if (index == -1)
            return false;

        this.deleteAt(index);
        return true;
    }

    private void deleteAt(int index) {
        int i = index;
        for (i = index; i < this.getKeyCount() - 1; ++i) {
            this.setKey(i, this.getKey(i + 1));
            this.setValue(i, this.getValue(i + 1));
        }
        this.setKey(i, null);
        this.setValue(i, null);
        --this.keyCount;
    }

    @Override
    protected void processChildrenTransfer(BPlusNode<TKey> borrower, BPlusNode<TKey> lender, int borrowIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected BPlusNode<TKey> processChildrenFusion(BPlusNode<TKey> leftChild, BPlusNode<TKey> rightChild) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void fusionWithSibling(TKey sinkKey, BPlusNode<TKey> rightSibling) {
        BPlusLeafNode<TKey, TValue> siblingLeaf = (BPlusLeafNode<TKey, TValue>)rightSibling;

        int j = this.getKeyCount();
        for (int i = 0; i < siblingLeaf.getKeyCount(); ++i) {
            this.setKey(j + i, siblingLeaf.getKey(i));
            this.setValue(j + i, siblingLeaf.getValue(i));
        }
        this.keyCount += siblingLeaf.getKeyCount();

        this.setRightSibling(siblingLeaf.rightSibling);
        if (siblingLeaf.rightSibling != null)
            siblingLeaf.rightSibling.setLeftSibling(this);
    }

    @Override
    protected TKey transferFromSibling(TKey sinkKey, BPlusNode<TKey> sibling, int borrowIndex) {
        BPlusLeafNode<TKey, TValue> siblingNode = (BPlusLeafNode<TKey, TValue>)sibling;

        this.insertKey(siblingNode.getKey(borrowIndex), siblingNode.getValue(borrowIndex));
        siblingNode.deleteAt(borrowIndex);

        return borrowIndex == 0 ? sibling.getKey(0) : this.getKey(0);
    }
}
