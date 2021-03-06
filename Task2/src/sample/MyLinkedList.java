package sample;

public class MyLinkedList<T> {

    private class ListItem {
        public T value;
        public ListItem next;

        public ListItem(T value, ListItem next) {
            this.value = value;
            this.next = next;
        }
    }

    protected ListItem head = null;
    protected ListItem tail = null;
    protected int size = 0;

    public MyLinkedList(MyLinkedList other) {
        ListItem item = other.head;
        while (item != null) {
            this.addLast(item.value);
            item = item.next;
        }
    }

    public MyLinkedList() {
    }


    public void addFirst(T value) {
        head = new ListItem(value, head);
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    public void addLast(T value) {
        ListItem temp = new ListItem(value, null);
        if (tail == null) {
            head = tail = temp;
        } else {
            tail.next = temp;
            tail = temp;
        }
        size++;
    }

    private void checkEmpty() throws Exception {
        if (head == null) {
            throw new Exception("List is empty");
        }
    }

    public T getFirst() throws Exception {
        checkEmpty();
        return head.value;
    }

    public T getLast() throws Exception {
        checkEmpty();
        return tail.value;
    }

    public ListItem get(int index) throws Exception {
        if (index < 0 || index > size - 1) {
            throw new Exception("Incorrect index");
        }
        ListItem curr = head;
        while (index != 0) {
            index--;
            curr = curr.next;
        }
        return curr;
    }

    public T getValue(int index) throws Exception {
        return this.get(index).value;
    }

    public int size() {
        return size;
    }

    public void removeFirst() throws Exception {
        checkEmpty();
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
    }

    public void removeLast() throws Exception {
        checkEmpty();
        if (size == 1) {
            head = tail = null;
        } else {
            for (ListItem curr = head; ; curr = curr.next) {
                if (curr.next.next == null) {
                    tail = curr;
                    tail.next = null;
                    break;
                }
            }
        }
        size--;
    }

    public MyLinkedList<Integer> process() {
        MyLinkedList<Integer> ans = new MyLinkedList<>();
        MyLinkedList<Integer> negativeNumber = new MyLinkedList<>();
        MyLinkedList<Integer> zeroNumber = new MyLinkedList<>();
        MyLinkedList<Integer> positiveNumber = new MyLinkedList<>();

        try {
            for (int i = 0; i < this.size; i++) {
                int number = (int) this.getValue(i);
                if (number < 0) {
                    negativeNumber.addLast(number);
                } else if (number == 0) {
                    zeroNumber.addLast(number);
                } else {
                    positiveNumber.addLast(number);
                }
            }

            for (int i = 0; i < negativeNumber.size; i++) {
                ans.addLast(negativeNumber.getValue(i));
            }
            for (int i = negativeNumber.size; i < negativeNumber.size + zeroNumber.size; i++) {
                ans.addLast(zeroNumber.getValue(i - negativeNumber.size));
            }
            for (int i = negativeNumber.size + zeroNumber.size; i < this.size; i++) {
                ans.addLast(positiveNumber.getValue(i - (negativeNumber.size + zeroNumber.size)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }
}
