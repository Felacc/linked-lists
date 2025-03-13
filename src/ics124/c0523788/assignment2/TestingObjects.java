package ics124.c0523788.assignment2;
import ics124.utils.UnitTest;

public class TestingObjects {
    UnitTest tests;
    
    public TestingObjects() {
        tests = new UnitTest(true, true);
        tests.initTests();
    }
    
    public static void main(String[] args) {
        TestingObjects me = new TestingObjects();
        me.run();
    }
    public void run() {
        tests.initTests();
        testEmptySll();
        // TODO: add more tests
        testEmptyDll();
        testSllAdd();
        testDllAdd();
        
        
        System.out.println(tests.summarizeTests());
    }
    
    public void testEmptySll() {
        Ics124SinglyLinkedList<Integer> l = new Ics124SinglyLinkedList<>();
        tests.assertEquals(l.size(), 0);
        // TODO: anything else we can assert about a SLL?
        tests.assertIsNull(l.head);
    }
    
    public void testEmptyDll() {
        Ics124DoublyLinkedList<Integer> l = new Ics124DoublyLinkedList();
        tests.assertEquals(l.size(), 0);
    }
    
    public void testSllAdd() {
        Ics124SinglyLinkedList<Integer> l = new Ics124SinglyLinkedList<>();
        l.add(0,1);
        l.add(1,2);
        l.add(2,4);
        l.printList();
        tests.assertEquals(l.size(), 3);
    }
    
    public void testDllAdd() {
        Ics124DoublyLinkedList<Integer> l = new Ics124DoublyLinkedList<>();
        l.add(0,1);
        l.add(1,2);
        l.add(2,4);
        tests.assertEquals(l.size(), 3);
    }
    
    public void testSllGet() {
        Ics124SinglyLinkedList<Integer> l = new Ics124SinglyLinkedList<>();
        l.add(0,1);
        l.add(1,2);
        l.add(2,4);
        tests.assertEquals(l.get(2), 4);
    }
    
    public void testDllGet() {
        Ics124DoublyLinkedList<Integer> l = new Ics124DoublyLinkedList<>();
        l.add(0,1);
        l.add(1,2);
        l.add(2,4);
        tests.assertEquals(l.get(0), 1);
    }
    
    public void testSllSet() {
        Ics124SinglyLinkedList<Integer> l = new Ics124SinglyLinkedList<>();
        l.add(0,1);
        l.add(1,2);
        l.add(2,4);
        l.set(1,9);
        tests.assertEquals(l.get(1), 9);
    }
    
    public void testDllSet() {
        Ics124DoublyLinkedList<Integer> l = new Ics124DoublyLinkedList<>();
        l.add(0,1);
        l.add(1,2);
        l.add(2,4);
        l.set(1,7);
        tests.assertEquals(l.get(1), 7);
    }
    
    public void testSllRemove() {
        Ics124SinglyLinkedList<Integer> l = new Ics124SinglyLinkedList<>();
        l.add(0,1);
        l.add(1,2);
        l.add(2,4);
        l.set(1,9);
        l.remove(0);
        tests.assertEquals(l.get(0), 9);
    }
    
    public void testDllRemove() {
        Ics124DoublyLinkedList<Integer> l = new Ics124DoublyLinkedList<>();
        l.add(0,1);
        l.add(1,2);
        l.add(2,4);
        l.set(1,7);
        l.remove(1);
        tests.assertEquals(l.get(1), 4);
    }
    
}
