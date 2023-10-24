import java.util.Iterator;
import java.util.NoSuchElementException;

// 1. MyLinkedList and MyArrayList
// 2. opt getNode method 
// 3. MyLinkedList singlelinklist
// 4. test all the methods https://leetcode-cn.com/problems/design-linked-list/
// 5. stack and queue, using linkedlist 

public class MyLinkedList<E> implements Iterable<E> {
    // head tail
    final private Node<E> head, tail;
    private int size;

    // doubly linked list
    private static class Node<E> {
        E val;
        Node<E> next;
        Node<E> prev;

        Node(E val) {
            this.val = val;
        }
    }

    // init head and tail 
    public MyLinkedList() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }


    /***** Add *****/

    public void addLast(E e) {
        Node<E> x = new Node<>(e); 
        Node<E> temp = tail.prev;  

        tail.prev = x; 
        temp.next = x; 
        x.next = tail; 
        x.prev = temp; 

        size++;
    }

    public void addFirst(E e) {
        Node<E> x = new Node<>(e); 
        Node<E> temp = head.next; 

        head.next = x; 
        temp.prev = x; 

        x.next = temp; 
        x.prev = head; 

        size++;
    }

    public void add(int index, E element) {
        checkPositionIndex(index); 
        if (index == size) {
            addLast(element);
            return; 
        }

        Node<E> x = new Node<>(element); 
        Node<E> temp = getNode(index).prev; 

        temp.next = x; 
        getNode(index).prev = x; 

        x.prev = temp; 
        x.next = getNode(index); 
        
        size++; 
    }

    /***** delete *****/

    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException(); 
        }

        Node<E> x = head.next; 
        Node<E> temp = x.next; 

        head.next = temp; 
        temp.prev = head; 

        x.prev = x.next = null; 

        size--; 

        return x.val; 
    }

    public E removeLast() {
        if(isEmpty()) {
            throw new NoSuchElementException(); 
        }

        Node<E> x = tail.prev; 
        Node<E> temp = x.prev; 

        tail.prev = temp; 
        temp.next = tail; 

        x.prev = x.next = null; 

        size--; 

        return x.val;

    }

    public E remove(int index) {
        checkElementIndex(index); 
        
        Node<E> x = getNode(index); 
        Node<E> prev = x.prev; 
        Node<E> next = x.next; 
        E oldVal = x.val;
        prev.next = next; 
        next.prev = prev; 

        x.prev = x.next = null;  

        size--; 

        return oldVal;
    }

    /***** 查 *****/

    public E get(int index) {
        checkElementIndex(index); 
        Node<E> x = getNode(index); 
        return x.val; 
    }

    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return head.next.val;  
    }

    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException(); 
        }

        return tail.prev.val;
    }

    /***** 改 *****/

    public E set(int index, E val) {
        checkElementIndex(index); 
        E oldVal = getNode(index).val; 
        getNode(index).val = val; 

        return oldVal;
    }

    /***** 其他工具函数 *****/

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Node<E> getNode(int index) {
        checkElementIndex(index);
        Node<E> p = head.next;
        // TODO: 可以优化，通过 index 判断从 head 还是 tail 开始遍历
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * 检查 index 索引位置是否可以存在元素
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    /**
     * 检查 index 索引位置是否可以添加元素
     */
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> p = head.next;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E val = p.val;
                p = p.next;
                return val;
            }
        };
    }

    public static void main(String[] args) {
        // init linked list 
        MyLinkedList<Integer> lst = new MyLinkedList<>();
        lst.add(0, Integer.valueOf(3));
        System.out.println(lst.size());
        System.out.println(lst.isEmpty());
    }

}