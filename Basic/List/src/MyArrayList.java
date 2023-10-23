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
        // 看 data 数组容量够不够
        if (size == cap) {
            resize(2 * cap);
        }
        // 在尾部插入元素
        data[size] = e;
        size++;
    }

    public void add(int index, E e) {
         checkPositionIndex(index);

    }

    public void addFirst(E e) {
        
    }

    // 删
    public E removeLast() {
        // check if empty
         if (isEmpty()) {
            throw new NoSuchElementException();
         }
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
       
    }

    public E removeFirst() {
        
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
        
    }

    public boolean isEmpty() {
        
    }

    // change data capacity to newCap
    private void resize(int newCap) {
         
    }

    private boolean isElementIndex(int index) {
        
    }

    private boolean isPositionIndex(int index) {
        
    }

    /**
     * 检查 index 索引位置是否可以存在元素
     */
    private void checkElementIndex(int index) {
         
    }

    /**
     * 检查 index 索引位置是否可以添加元素
     */
    private void checkPositionIndex(int index) {
 
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

 
    }

}