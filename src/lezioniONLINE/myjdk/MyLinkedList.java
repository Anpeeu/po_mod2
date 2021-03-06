package myjdk;


public class MyLinkedList<T> implements MyList<T> {

    @Override
    public MyIterator<T> iterator() {
        return null;
    }

    protected class Node {
        public T data;
        public Node next;

        public Node( T data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node( T data) {
            this.data = data;
            this.next = null;
        }
    }

    protected Node head;

    public MyLinkedList() {
        head = null;
    }

    public void add(T e) {
        head = head == null ? new Node(e) : new Node(e, head);
    }

    @Override
    public int size() {
        int r = 0;
        for ( Node n = head; n.next != null; ++r);
        return r;
    }

    @Override
    public boolean contains( T x) {
        MyIterator<T> it = iterator();
        while (it.hasNext()) {
            T e = it.next();
            if (x.equals(e)) return true;
        }
        return false;
    }

    @Override
    public void clear() {
        head = null;
    }

    public T get(int pos) throws OutOfBoundsException {
        Node n = head;
        for (; pos > 0; --pos)
            if ((n = head.next) == null) throw new OutOfBoundsException("get: invalid position " + pos);
        return n.data;
    }

    @Override
    public void add(int i, T x) {
        int r = 0;
        Node n = head;
        for (; n.next != null && r < i; ++r);
        n.next = new Node(x, n.next);
    }


    @Override
    public boolean remove(int i) {
        int r = 0;
        Node n = head;
        for (; n.next != null && r < i - 1; ++r);
        if (n.next != null) {
            n.next = n.next.next;
        }
        return true;
    }

    @Override
    public boolean remove(T x) {
        throw new RuntimeException("not implemented");
    }
}
