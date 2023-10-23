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
    public void addLast(E e) {
        int cap = data.length;
        // check if capacity is enough
        if (size == cap) {
            resize(2 * cap);
        }
        // 在尾部插入元素
        data[size] = e;
        size++;
    }

    public void add(int index, E e) {
        checkPositionIndex(index);
        // data[index] => data[index + 1] 
        System.arraycopy(data, index, data, index + 1, size - index); 
        data[index] = e; 
        size++;
    }

    public void addFirst(E e) {
        
    }

    // 删
    public E removeLast() {
        // check if empty
         if (isEmpty()) {
            throw new NoSuchElementException();
         }
         int cap = data.length;
         // check if need to resize
         if (size < cap / 4) {
            resize(cap / 2);
         }

         E deletedVal = data[size-1];
         // set last as null, for garbage collection
         data[size - 1] = null; 
         size--;
         return deletedVal;
    }

    public E remove(int index) {
       checkPositionIndex(index); 
       int cap = data.length;
        // check if need to resize
         if (size < cap / 4) {
            resize(cap / 2);
         }
       E deletedVal = data[index]; 
       // data[index + 1] => data[index] 
       System.arraycopy(data, index + 1, data, index, size - index - 1);
       // remove last 
        data[size - 1] = null;
        size--;
        return deletedVal;
    }

    public E removeFirst() {
        checkPositionIndex(0); 
        int cap = data.length; 
        if(size < cap / 4) {
            resize(cap / 2); 
        }
        E deletedVal = data[0]; 
        System.arraycopy(data, 1, data, 0, size - 1); 
        data[size - 1] = null; 
        size--; 
        return deletedVal;  
    }

    // 查
    public E get(int index) {
        checkElementIndex(index);
         return data[index];
    }

    // update - set and return previous value
    public E set(int index, E element) {
        checkElementIndex(index); 
        E oldVal = data[index];
        data[index] = element; 

        return oldVal;
    }

    // 工具方法
    public int size() {
        return data.length;
    }

    public boolean isEmpty() {
        return data.length == 0;
    }

    // change data capacity to newCap
    private void resize(int newCap) {
        if (size > newCap) {
            return;
        }
        E[] temp = (E[]) new Object[newCap];

        for (int i = 0; i < size; i++) {
            temp[i] = data[i];
        }
        // System.arraycopy(data, 0, temp, 0, size);
        data = temp;
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

    private void display() {
        System.out.println("size = " + size + " cap = " + data.length);
        System.out.println(Arrays.toString(data));
    }


    public static void main(String[] args) {
        // 初始容量设置为 3
        MyArrayList<Integer> arr = new MyArrayList<>(3);
        System.out.println(arr.size());
        System.out.println(arr.isEmpty());
 
    }

}