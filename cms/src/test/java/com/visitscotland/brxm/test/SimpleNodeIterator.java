package com.visitscotland.brxm.test;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SimpleNodeIterator implements NodeIterator {

    private List<Node> nodes;
    private Iterator<Node> iterator;
    private long position;

    public SimpleNodeIterator(List<Node> nodes){
        this.nodes = Collections.unmodifiableList(nodes);
    }

    @Override
    public boolean hasNext() {
        if (iterator == null) {
            iterator = nodes.iterator();
        }
        return iterator.hasNext();
    }

    @Override
    public Node nextNode() {
        position++;
        return iterator.next();
    }

    @Override
    public Object next() {
        return nextNode();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove is not supported");
    }

    @Override
    public long getSize() {
        return -1; // Size is unknown; override if you want to provide it
    }

    @Override
    public long getPosition() {
        return position;
    }

    @Override
    public void skip(long skipNum) {
        for (long i = 0; i < skipNum && hasNext(); i++) {
            nextNode();
        }
    }
}
