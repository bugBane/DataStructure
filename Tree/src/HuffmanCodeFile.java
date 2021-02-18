import java.io.*;
import java.util.Map;

public class HuffmanCodeFile {

    public static void main(String[] args) {
        String srcPath1 = "F:/Project/DataStructure/Tree/src/test.txt";
        String srcPath2 = "F:/Project/DataStructure/Tree/src/test2.txt";
        String zipPath = "F:/Project/DataStructure/Tree/src/test.zip";
        zipFile(srcPath1, zipPath);
        unZipFile(zipPath,srcPath2);

    }

    // 压缩
    private static void zipFile(String srcPath, String zipPath) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fis = new FileInputStream(srcPath);
            fos = new FileOutputStream(zipPath);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            byte[] zipArr = HuffmanCode.zip(bytes);
            Map<Byte, String> huffmanCodeTable = HuffmanCode.huffmanCodeTable;
            oos = new ObjectOutputStream(fos);
            oos.writeObject(zipArr);
            oos.writeObject(huffmanCodeTable);
        } catch (IOException e) {
            e.getMessage();
        } finally {
            try {
                oos.close();
                fos.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 解压
    private static void unZipFile(String zipPath, String srcPath) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        FileOutputStream oos = null;
        try {
            fis = new FileInputStream(zipPath);
            ois = new ObjectInputStream(fis);
            byte[] zipArr = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodeTable = (Map<Byte, String>) ois.readObject();
            // 解码
            byte[] bytes = HuffmanCode.unZip(zipArr, huffmanCodeTable);
            oos = new FileOutputStream(srcPath);
            oos.write(bytes);
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                oos.close();
                ois.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
