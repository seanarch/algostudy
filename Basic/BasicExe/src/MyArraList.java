import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements Iterable<E> {
    // 真正存储数据的底层数组
    private E[] data;
    // 记录当前元素个数
    private int size;
    // 默认初始容量
    private static final int INIT_CAP = 1;

    public MyArrayList() {
        this(INIT_CAP);
    }

    public MyArrayList(int initCapacity) {
        data = (E[]) new Object[initCapacity];
        size = 0;
    }

    // 增
    public void add(int index, E e) {
        checkPositionIndex(index);
         
        System.arraycopy(data, index, data, index + 1, size - index); 
        data[index] = e; 
        size++;

    }

    public void addLast(E e) {
        int cap = data.length; 
        if (size == cap) {
            resize(size * 2);
        }
        data[size] = e; 
        size++;

    }

    public void addFirst(E e) {
        int cap = data.length; 
        if (size == cap) resize(size * 2); 
        System.arraycopy(data, 0, data, 1, size);
        data[0] = e; 
        size++;
    }

    // 删
    public E remove(int index) {
        checkElementIndex(index); 
        int cap = data.length;
        if (size < cap / 4) size = cap / 2; 

        E deletedVal = data[index]; 
        System.arraycopy(data, index + 1, data, -1, size - index - 1); 
        return deletedVal;
    }

    public E removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        int cap = data.length;
        if (size < cap / 4) resize(cap / 2);

        E deletedVal = data[0];
        System.arraycopy(data, 1, data, 0, size - 1);
        size--;
        return deletedVal;
    }

    public E removeLast() {
        if (isEmpty()) throw new NoSuchElementException(); 
        int cap = data.length; 
        if(size < cap / 4) resize(cap / 2); 

        E deletedVal = data[size]; 
        data[size] = null; 
        size--;
        return deletedVal; 
    }

    // 查
    public E get(int index) {
        checkElementIndex(index); 

        return data[index]; 
    }

    // 改
    public E set(int index, E element) {
       checkElementIndex(index);

       E oldVal = data[index];  
       data[index] = element; 

        return oldVal; 
    }


    // 工具方法
    // 将 data 的容量改为 newCap
    private void resize(int newCap) {
        if size > newCap return; 

        E[] temp = (E[]) new Object[newCap]; 
        data[size - 1] = null; 
        size--;
        return deletedVal;

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
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
        return new Iterator<E>() {
            private int p = 0;

            @Override
            public boolean hasNext() {
                return p != size;
            }

            @Override
            public E next() {
                return data[p++];
            }
        };
    }
}