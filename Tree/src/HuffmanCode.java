import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] zipArr = zip(str.getBytes());
        System.out.println(Arrays.toString(zipArr));
        byte[] bytes = unZip(zipArr, huffmanCodeTable);
        System.out.println(new String(bytes));
    }

    // 压缩
    public static byte[] zip(byte[] bytes) {
        // 创建赫夫曼集合
        List<HuffmanCodeNode> huffmanList = transferHuffmanList(bytes);
        // 将集合转换为赫夫曼树
        HuffmanCodeNode huffmanTree = createHuffmanTree(huffmanList);
        // 先序遍历赫夫曼树
//        prevOrder(huffmanTree);
        // 生成赫夫曼编码表
        createHuffmanCodeTable(huffmanTree, "", new StringBuilder());
        // 打印赫夫曼编码表
//        System.out.println(huffmanCodeTable);
        // 根据赫夫曼编码压缩字符串
        return zipArr(bytes, huffmanCodeTable);

    }

    // 解压
    public static byte[] unZip(byte[] zipArr, Map<Byte, String> huffmanCodeTable) {
        // 反转赫夫曼编码表
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodeTable.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        String s = "";
        List<Byte> list = new ArrayList<>();
        for (char c : byteArrToString(zipArr).toCharArray()) {
            s += c;
            if (map.containsKey(s)) {
                list.add(map.get(s));
                s = "";
            }
        }
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    // 数组转换成字符串
    private static String byteArrToString(byte[] zipArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < zipArr.length; i++) {
            boolean flag = (i == zipArr.length - 1);
            sb.append(byteToBitString(!flag, zipArr[i]));
        }
        return sb.toString();
    }

    // 字节转换成赫夫曼树编码
    public static String byteToBitString(boolean flag, byte b) {
        // 将byte转为int(需要使用Integer.toBinaryString方法)，需要掌握原码、补码、反码的知识！！！
        // 注意：toBinaryString此方法处理int值：
        //      如果为正数(符号位为0)那么返回结果不会自动补高位0到32bit，前面的0没有，只有已有位！！！(如5 =二进制> 101)
        //      如果为负数(符号位为1)，那么返回结果是负数的补码，因为有符号所以返回32bit的补码数，因为int类型为8位，所以只需要取后八位就行！！！补码=原码的反码(先除去符号位)+1
        //      (如-5的原码：10000000000000000000000000000101 =反码(除符号位)> 11111111111111111111111111111010 =+1(带符号位)> 11111111111111111111111111111011)
        int temp = b;
        // 如果是byte数组的最后一组数据，那么不应该补位
        if (flag) {
            // 按位与 256:1 0000 0000 | 0000 0101 => 1 0000 0101 然后取低八位
            temp |= 256;
        }
        // 高位补位之后转换之后的字符串也有高位
        String s = Integer.toBinaryString(temp);
        if (flag) {
            return s.substring(s.length() - 8);
        } else {
            return s;
        }
    }

    // 将字节数组压缩为byte数组
    public static byte[] zipArr(byte[] bytes, Map<Byte, String> huffmanCodeTable) {
        StringBuilder sb = new StringBuilder();
        for (Byte b : bytes) {
            sb.append(huffmanCodeTable.get(b));
        }
        String zipStr = sb.toString();
        // 切割压缩字符串
        int length = (zipStr.length() + 7) / 8;
        byte[] zipArr = new byte[length];

        for (int i = 0, index = 0; i < zipStr.length(); i += 8) {
            // 符号位为1代表为负数，补码转成原码需要：补码带符号先-1再除符号位取反码(原码、反码、补码的关系，主要原因是计算机只有加法运算器) 原码=(补码-1)的反码
            // 如果只使用加法?对于减一个数就相当于加这个数的相反数(2-5=2+(-5)),所以计算机操作二进制的时候就需要有一个符号位来代表是正数还是负数，1/0
            // 为何使用补码?当计算机实际计算(以4bit举例)：2(二进制:0010)+(-5(二进制:1101))=(二进制:1111)-7 != -3(二进制:1011) 所以会产生矛盾，负数需要特殊处理！！！
            // 如何处理(原码、补码、反码)?-5(二进制:1101)，那么实际操作需要转化原二进制(原码)，如何才能让负数的二进制转换成我们想要的二进制呢?
            //      借助平常生活经验:时钟12点 -> 10点 我们可以选择 12-2=10 也可以 12+(12-2)=22%12=10，所以计算机可以采取 => 2+(-5)=(2+(8-5)) 注意：会存在进位问题(所以要保留符号位不参与运算)，但是对于低位来说是一样的！！
            //      即：-5：1101 = 3：0011 在二进制中是相等的(只考虑低位)，但是因为要区分正数和负数，所以设置符号位，所以-5的新二进制0011应该加上符号位：1011(这就是补码)
            //      经过对比发现 原码：1101 和 补码：1011很不相同，如何转换呢？因为原码和补码是 取模的关系(%) 所以 除去符号位那么 补码=模-原码
            //      所以模-原码，就相当于二进制原码取反+1，此时的取反的产生的二进制就是反码：  原码：1101 =去符号位取反> 反码：1010 =带符号+1> 补码：1011
            //      为什么要取反+1就等于模-原码？-3(去符号位不参与运算)：101 取反为010，在二进制操作中101+010=111+001=模1000，所以补码=模-原码=(原码+原码的反码+1)-原码=原码的反码+1
            // 有了原码和补码，那么再处理：2(原码:0010)+(-5(补码:1011))=(二进制:1101)符号位1所以是补码：补码=原码的反码+1 => 原码=(补码-1)的反码 => 1011=-3所以实际计算正确
            // 负数-负数，如-2-3 => -2(补码:1110)+(-3(补码:1101)) 两位符号位都是1，所以需要保留符号位取低3位+符号位：1011 符号位1是补码 => 原码：1101=-5
            // 亦或者-(2+3) => 0010+0011取低3位+符号位1101=-5. 主要是要理解计算机只有加法运算器如何进行减法操作！！！！！！！！！！！！！！
            if (i + 8 >= zipStr.length()) {
                zipArr[index++] = (byte) Integer.parseInt(zipStr.substring(i, zipStr.length()), 2);
            } else {
                zipArr[index++] = (byte) Integer.parseInt(zipStr.substring(i, i + 8), 2);
            }

        }
        return zipArr;
    }

    // 赫夫曼编码表
    public static Map<Byte, String> huffmanCodeTable = new HashMap<>();

    // 根据赫夫曼树生成赫夫曼编码表
    public static void createHuffmanCodeTable(HuffmanCodeNode huffmanCodeNode, String code, StringBuilder stringBuilder1) {
        // 用来拼接编码
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder1);
        stringBuilder2.append(code);
        if (huffmanCodeNode == null) {
            return;
        }
        // 非叶子节点
        if (huffmanCodeNode.getData() == null) {
            // 左递归
            createHuffmanCodeTable(huffmanCodeNode.getLeft(), "0", stringBuilder2);
            // 右递归
            createHuffmanCodeTable(huffmanCodeNode.getRight(), "1", stringBuilder2);
        } else {
            huffmanCodeTable.put((Byte) huffmanCodeNode.getData(), stringBuilder2.toString());
        }
    }

    // 字符串切割为char数组，转换成list集合
    public static List<HuffmanCodeNode> transferHuffmanList(byte[] bytes) {
        HashMap<Byte, Integer> huffmanMap = new HashMap<>();
        for (byte b : bytes) {
            if (huffmanMap.containsKey(b)) {
                huffmanMap.put(b, huffmanMap.get(b) + 1);
            } else {
                huffmanMap.put(b, 1);
            }
        }
        List<HuffmanCodeNode> list = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : huffmanMap.entrySet()) {
            list.add(new HuffmanCodeNode(entry.getValue(), entry.getKey()));
        }
        return list;
    }


    // 创建赫夫曼树
    public static HuffmanCodeNode createHuffmanTree(List<HuffmanCodeNode> list) {
        while (list.size() > 1) {
            Collections.sort(list);
            HuffmanCodeNode leftNode = list.get(0);
            HuffmanCodeNode rightNode = list.get(1);
            HuffmanCodeNode parentNode = new HuffmanCodeNode(leftNode.getValue() + rightNode.getValue(), null);
            parentNode.setLeft(leftNode);
            parentNode.setRight(rightNode);
            list.remove(leftNode);
            list.remove(rightNode);
            list.add(parentNode);
        }
        return list.get(0);
    }

    // 前序遍历赫夫曼树
    public static void prevOrder(HuffmanCodeNode huffmanCodeNode) {
        if (huffmanCodeNode == null) {
            System.out.println("赫夫曼树为空");
            return;
        }
        huffmanCodeNode.prevOrder();
    }
}

class HuffmanCodeNode implements Comparable<HuffmanCodeNode> {
    // 权值
    private int value;
    // 数据
    private Object data;
    // 左子树
    private HuffmanCodeNode left;
    // 右子树
    private HuffmanCodeNode right;

    public HuffmanCodeNode(int value, Object data) {
        this.value = value;
        this.data = data;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public HuffmanCodeNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanCodeNode left) {
        this.left = left;
    }

    public HuffmanCodeNode getRight() {
        return right;
    }

    public void setRight(HuffmanCodeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HuffmanCodeNode{" +
                "value=" + value +
                ", data=" + data +
                '}';
    }

    // 先序遍历
    public void prevOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.prevOrder();
        }
        if (this.right != null) {
            this.right.prevOrder();
        }
    }

    @Override
    public int compareTo(HuffmanCodeNode o) {
        return this.value - o.value;
    }
}