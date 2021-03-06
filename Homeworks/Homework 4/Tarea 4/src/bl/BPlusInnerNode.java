package bl;

public class BPlusInnerNode<TKey extends Comparable<TKey>> extends BPlusNode<TKey> {
    protected final static int INNERORDER = 4;
    protected Object[] children;

    public BPlusInnerNode() {
        this.keys = new Object[INNERORDER + 1];
        this.children = new Object[INNERORDER + 2];
    }

    public BPlusNode<TKey> getChild(int index) {
        return (BPlusNode<TKey>)this.children[index];
    }

    public void setChild(int index, BPlusNode<TKey> child) {
        this.children[index] = child;
        if (child != null)
            child.setParent(this);
    }

    @Override
    public BTreeNodeType getNodeType() {
        return BTreeNodeType.InnerNode;
    }

    @Override
    public int search(TKey key) {
        int index = 0;
        for (index = 0; index < this.getKeyCount(); ++index) {
            int cmp = this.getKey(index).compareTo(key);
            if (cmp == 0) {
                return index + 1;
            }
            else if (cmp > 0) {
                return index;
            }
        }

        return index;
    }

    private void insertAt(int index, TKey key, BPlusNode<TKey> leftChild, BPlusNode<TKey> rightChild) {
        for (int i = this.getKeyCount() + 1; i > index; --i) {
            this.setChild(i, this.getChild(i - 1));
        }
        for (int i = this.getKeyCount(); i > index; --i) {
            this.setKey(i, this.getKey(i - 1));
        }

        this.setKey(index, key);
        this.setChild(index, leftChild);
        this.setChild(index + 1, rightChild);
        this.keyCount += 1;
    }

    @Override
    protected BPlusNode<TKey> split() {
        int midIndex = this.getKeyCount() / 2;

        BPlusInnerNode<TKey> newRNode = new BPlusInnerNode<TKey>();
        for (int i = midIndex + 1; i < this.getKeyCount(); ++i) {
            newRNode.setKey(i - midIndex - 1, this.getKey(i));
            this.setKey(i, null);
        }
        for (int i = midIndex + 1; i <= this.getKeyCount(); ++i) {
            newRNode.setChild(i - midIndex - 1, this.getChild(i));
            newRNode.getChild(i - midIndex - 1).setParent(newRNode);
            this.setChild(i, null);
        }
        this.setKey(midIndex, null);
        newRNode.keyCount = this.getKeyCount() - midIndex - 1;
        this.keyCount = midIndex;

        return newRNode;
    }

    @Override
    protected BPlusNode<TKey> pushUpKey(TKey key, BPlusNode<TKey> leftChild, BPlusNode<TKey> rightNode) {
        int index = this.search(key);

        this.insertAt(index, key, leftChild, rightNode);

        if (this.isOverflow()) {
            return this.dealOverflow();
        }
        else {
            return this.getParent() == null ? this : null;
        }
    }

    private void deleteAt(int index) {
        int i = 0;
        for (i = index; i < this.getKeyCount() - 1; ++i) {
            this.setKey(i, this.getKey(i + 1));
            this.setChild(i + 1, this.getChild(i + 2));
        }
        this.setKey(i, null);
        this.setChild(i + 1, null);
        --this.keyCount;
    }


    @Override
    protected void processChildrenTransfer(BPlusNode<TKey> borrower, BPlusNode<TKey> lender, int borrowIndex) {
        int borrowerChildIndex = 0;
        while (borrowerChildIndex < this.getKeyCount() + 1 && this.getChild(borrowerChildIndex) != borrower)
            ++borrowerChildIndex;

        if (borrowIndex == 0) {
            TKey upKey = borrower.transferFromSibling(this.getKey(borrowerChildIndex), lender, borrowIndex);
            this.setKey(borrowerChildIndex, upKey);
        }
        else {
            TKey upKey = borrower.transferFromSibling(this.getKey(borrowerChildIndex - 1), lender, borrowIndex);
            this.setKey(borrowerChildIndex - 1, upKey);
        }
    }

    @Override
    protected BPlusNode<TKey> processChildrenFusion(BPlusNode<TKey> leftChild, BPlusNode<TKey> rightChild) {
        int index = 0;
        while (index < this.getKeyCount() && this.getChild(index) != leftChild)
            ++index;
        TKey sinkKey = this.getKey(index);

        leftChild.fusionWithSibling(sinkKey, rightChild);

        this.deleteAt(index);

        if (this.isUnderflow()) {
            if (this.getParent() == null) {
                if (this.getKeyCount() == 0) {
                    leftChild.setParent(null);
                    return leftChild;
                }
                else {
                    return null;
                }
            }

            return this.dealUnderflow();
        }

        return null;
    }


    @Override
    protected void fusionWithSibling(TKey sinkKey, BPlusNode<TKey> rightSibling) {
        BPlusInnerNode<TKey> rightSiblingNode = (BPlusInnerNode<TKey>)rightSibling;

        int j = this.getKeyCount();
        this.setKey(j++, sinkKey);

        for (int i = 0; i < rightSiblingNode.getKeyCount(); ++i) {
            this.setKey(j + i, rightSiblingNode.getKey(i));
        }
        for (int i = 0; i < rightSiblingNode.getKeyCount() + 1; ++i) {
            this.setChild(j + i, rightSiblingNode.getChild(i));
        }
        this.keyCount += 1 + rightSiblingNode.getKeyCount();

        this.setRightSibling(rightSiblingNode.rightSibling);
        if (rightSiblingNode.rightSibling != null)
            rightSiblingNode.rightSibling.setLeftSibling(this);
    }

    @Override
    protected TKey transferFromSibling(TKey sinkKey, BPlusNode<TKey> sibling, int borrowIndex) {
        BPlusInnerNode<TKey> siblingNode = (BPlusInnerNode<TKey>)sibling;

        TKey upKey = null;
        if (borrowIndex == 0) {
            int index = this.getKeyCount();
            this.setKey(index, sinkKey);
            this.setChild(index + 1, siblingNode.getChild(borrowIndex));
            this.keyCount += 1;

            upKey = siblingNode.getKey(0);
            siblingNode.deleteAt(borrowIndex);
        }
        else {
            this.insertAt(0, sinkKey, siblingNode.getChild(borrowIndex + 1), this.getChild(0));
            upKey = siblingNode.getKey(borrowIndex);
            siblingNode.deleteAt(borrowIndex);
        }

        return upKey;
    }
}
