package opgave01.models;

import java.lang.annotation.ElementType;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements ListEaaa<E>{

    private Node<E> head;
    private Node<E> tail;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public void add(E e) {
        Node<E> newNode = new Node<>(e);
        if(tail == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    @Override
    public boolean remove(E e) {
        boolean removed = false;
        Node<E> previousNode = head;
        Node<E> node = head.getNext();
        if(head.getElement() == e) {
            head = head.next;
            removed = true;
        }
        else {
            while (node.next != null && !removed) {
                if (node == e) {
                    previousNode.setNext(node.getNext());
                    removed = true;
                }
                node = node.getNext();
            }
        }
        if(previousNode == tail && removed) {
            tail = null;
        }
        return removed;
    }

    @Override
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        Node<E> oldHead = head;
        head = newNode;
        newNode.setNext(oldHead);
    }

    @Override
    public E getFirst() {
        return head.getElement();
    }

    @Override
    public void removeFirst() {
        if(head == tail) {
            head = null;
            tail = null;
        }
        else {
            head = head.getNext();
        }
    }

    @Override
    public boolean contains(E e) {
        boolean found = false;
        if(tail.getElement() == e) {
            found = true;
        }
        else {
            Node<E> node = head;
            while (node.next != null && !found) {
                if (node.getElement() == e) {
                    found = true;
                }
                node = node.getNext();
            }
        }
        return found;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    @Override
    public int size() {
        int size = 0;
        Node<E> node = head;
        while(node.next != null) {
            size += 1;
            node = node.getNext();
        }
        if(tail != head) {
            size += 1;
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public E get(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException("Index too high or below 0");
        }
        int i = 0;
        Node<E> node = head;
        return getHelper(index, node, i);

        /*
        E element = null;
        if(index > size() - 1) {
            return null;
        }
        else if(index == 0) {
            element = head.element;
        }
        else if (index == size() - 1) {
            element = tail.element;
        }
        else {
            Node<E> node = head.getNext();
            int i = 1;
            while(node.next != null && i != index) {
                node.getNext();
                i++;
            }
            if(i == index) {
                element = node.element;
            }
        }
        return element;
         */
    }

    private E getHelper(int index, Node<E> node, int i) {
        if (i == index) {
            return node.getElement();
        }
        else {
            i++;
            node = node.getNext();
            return getHelper(index, node, i);
        }

    }

    @Override
    public void add(int index, E e) {
        if(index == 0) {
            Node<E> oldHead = head;
            head = new Node<>(e);
            head.setNext(oldHead);
        } else if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index is too high or below 0");
        }
        else {
            int i = 1;
            Node<E> previous = head;
            Node<E> current = previous.getNext();
            while(i != index){
                previous = current;
                current = current.getNext();
                i++;
            }
            Node<E> newNode = new Node<>(e);
            previous.setNext(newNode);
            newNode.setNext(current);
        }
    }

    @Override
    public E remove(int index) {
        E element;
        if(index < 0 || index >= size()){
            throw new IndexOutOfBoundsException("Index too high or below 0");
        }
        if (index == 0) {
            head = head.getNext();
            element = head.getElement();
        }
        else {
            int i = 1;
            Node<E> previous = head;
            Node<E> current = previous.getNext();
            Node<E> next = current.getNext();
            while(i != index) {
                previous = current;
                current = next;
                next = next.getNext();
                i++;
            }
            previous.setNext(next);
            element = current.getElement();
        }
        return element;
    }

    @Override
    public int indexOf(E e) {
        int i = 0;
        Node<E> current = head;
        while(i < size() && current.element != e){
            current = current.getNext();
            i++;
        }
        return i;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator(head);
    }

    public class LinkedListIterator implements Iterator<E> {

        private Node<E> current;

        public LinkedListIterator(Node<E> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if(!hasNext()) {
                throw new NoSuchElementException("No more elements");
            }
            E element = current.getElement();
            current = current.getNext();
            return element;
        }
    }

    public class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element) {
            this.element = element;
        }

        public void setNext(Node<E> node) {
            next = node;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }
    }

}
