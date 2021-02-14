import java.util.Scanner;

public class HashTable {
    // 默认为10
    private int maxSize = 10;
    // 默认为null
    private EmployeeLinkedList[] employeeLinkedLists;

    public HashTable() {
        employeeLinkedLists = new EmployeeLinkedList[maxSize];
    }

    public HashTable(int maxSize) {
        this.maxSize = maxSize;
        employeeLinkedLists = new EmployeeLinkedList[maxSize];
    }

    public int hash(int id) {
        return id % maxSize;
    }

    // 增加或者修改
    public void put(Employee employee) {
        int hash = hash(employee.getId());
        // 第一次初始化链表
        if (employeeLinkedLists[hash] == null) {
            for (int i = 0; i < maxSize; i++) {
                employeeLinkedLists[i] = new EmployeeLinkedList();
            }
        }
        // 增加或者修改链表元素
        employeeLinkedLists[hash].update(employee);
    }

    // 删除
    public Employee remove(int id) {
        int hash = hash(id);
        if (employeeLinkedLists[hash] == null) {
            System.out.println("哈希表为空,无法删除元素");
            return null;
        }
        return employeeLinkedLists[hash].remove(id);
    }

    // 查询
    public Employee find(int id) {
        int hash = hash(id);
        if (employeeLinkedLists[hash] == null) {
            System.out.println("哈希表为空,没有元素");
            return null;
        }
        return employeeLinkedLists[hash].find(id);
    }

    // 遍历
    public void list() {
        if (employeeLinkedLists[0] == null) {
            System.out.println("哈希表为空,没有元素");
            return;
        }
        for (int i = 0; i < maxSize; i++) {
            System.out.print("哈希表[" + i + "]: ");
            employeeLinkedLists[i].list();
            System.out.println();
        }
    }

    // 测试
    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        System.out.println("欢迎使用哈希表：请操作");
        System.out.println("s展示哈希表");
        System.out.println("l查id员工");
        System.out.println("a增加/修改员工");
        System.out.println("g删除员工");
        System.out.println("e退出操作");
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            char c = scanner.next().charAt(0);
            switch (c) {
                case 's':
                    hashTable.list();
                    break;
                case 'l':
                    try {
                        System.out.println("请输入要查找的员工id");
                        int id = scanner.nextInt();
                        Employee employee = hashTable.find(id);
                        if (employee == null) {
                            System.out.println("id为" + id + "的员工不存在");
                        } else {
                            System.out.println(employee);
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    try {
                        System.out.println("请输入要添加/修改的员工id");
                        int id = scanner.nextInt();
                        System.out.println("请输入要添加/修改的员工姓名");
                        String name = scanner.next();
                        System.out.println("请输入要添加/修改的员工年龄");
                        int age = scanner.nextInt();
                        System.out.println("请输入要添加/修改的员工地址");
                        String address = scanner.next();
                        hashTable.put(new Employee(id, name, age, address));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        System.out.println("请输入要删除的员工id");
                        int id = scanner.nextInt();
                        Employee employee = hashTable.remove(id);
                        if (employee == null) {
                            System.out.println("id为" + id + "的员工不存在");
                        } else {
                            System.out.println(id + "的员工被删除了");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    flag = false;
                    break;
                default:
                    break;
            }
        }
    }
}

class EmployeeLinkedList {
    // 默认为null
    private Employee head;

    public EmployeeLinkedList() {
        head = new Employee();
    }

    // 修改链表元素或者加入到链表尾部
    public void update(Employee employee) {
        Employee temp = head;
        while (temp.getNext() != null) {
            if (temp.getNext().getId() == employee.getId()) {
                temp.getNext().setAge(employee.getAge());
                temp.getNext().setName(employee.getName());
                temp.getNext().setAddress(employee.getAddress());
                return;
            }
            temp = temp.getNext();
        }
        temp.setNext(employee);
    }

    // 删除链表元素
    public Employee remove(int id) {
        if (head.getNext() == null) {
            return null;
        }
        Employee temp = head;
        while (temp.getNext() != null) {
            if (temp.getNext().getId() == id) {
                Employee employee = temp.getNext();
                temp.setNext(temp.getNext().getNext());
                return employee;
            }
            temp = temp.getNext();
        }
        return null;
    }

    // 遍历链表元素
    public void list() {
        if (head.getNext() == null) {
            System.out.print("链表为空,没有元素");
            return;
        }
        Employee temp = head.getNext();
        while (temp != null) {
            System.out.print(temp + "  ");
            temp = temp.getNext();
        }
    }

    // 查找链表元素
    public Employee find(int id) {
        if (head.getNext() == null) {
//            System.out.println("链表为空,没有元素");
            return null;
        }
        Employee temp = head.getNext();
        while (temp != null) {
            if (temp.getId() == id) {
                return temp;
            }
            temp = temp.getNext();
        }
        return null;
    }
}

class Employee {
    private int id;
    private String name;
    private int age;
    private String address;
    private Employee next; // 默认为null

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Employee getNext() {
        return next;
    }

    public void setNext(Employee next) {
        this.next = next;
    }

    public Employee() {
    }

    public Employee(int id, String name, int age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
