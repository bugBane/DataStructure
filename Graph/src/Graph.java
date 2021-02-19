import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    // 存储顶点集合
    private ArrayList<String> vertexList;
    // 存储图对应的连接矩阵
    private int[][] edges;
    // 表示边的数目 默认0
    private int numOfEdges;

    // 用来协助深度算法 记录顶点是否已经走过
    private boolean[] vertexFlag;

    public Graph(int num) {
        this.vertexList = new ArrayList<>();
        this.edges = new int[num][num];
        this.vertexFlag = new boolean[num];
    }

    // 添加顶点
    public void add(String vertex) {
        vertexList.add(vertex);
    }

    // 连接矩阵
    public void connectionMatrix(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    // 返回权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 根据索引返回顶点
    public String getVertex(int i) {
        return vertexList.get(i);
    }

    // 打印矩阵
    public void show() {
        for (int[] ints : edges) {
            System.out.println(Arrays.toString(ints));
        }
    }

    // 返回i顶点的第一个邻接点
    public int getFirstApproximal(int i) {
        for (int j = 0; j < numOfEdges; j++) {
            if (edges[i][j] != 0) {
                return j;
            }
        }
        return -1;
    }

    // 返回i顶点邻接点j的下一个邻接点
    public int getApproximalPoint(int i, int j) {
        for (int k = j + 1; k < numOfEdges; k++) {
            if (edges[i][k] != 0) {
                return k;
            }
        }
        return -1;
    }

    // 图深度算法
    // 深度的意思和八皇后及迷宫问题类似，当所以节点都走过，打印最深的那一条路径
    public void dfs() {
        // 对每一个顶点都进行深度测算
        for (int i = 0; i < numOfEdges; i++) {
            dfs(vertexFlag, i);
        }
    }

    // 对顶点i进行深度测算
    private void dfs(boolean[] vertexFlag, int i) {
        if (vertexFlag[i]) {
            return;
        }
        System.out.print(vertexList.get(i) + "节点走过  ");
        vertexFlag[i] = true;
        // 先获取第一个邻接点
        int j = getFirstApproximal(i);
        while (j != -1) {
            if (!vertexFlag[j]) {
                dfs(vertexFlag, j);
            }
            // 在获取i邻接点j下一个邻接点
            j = getApproximalPoint(i, j);
        }
    }

    // 图广度算法(广度不使用递归，要一层一层)
    public void bfs() {
        // 对每一个顶点都进行广度测算
        for (int i = 0; i < numOfEdges; i++) {
            if (!vertexFlag[i]) {
                bfs(vertexFlag, i);
            }
        }
    }

    // 对顶点i进行广度测算
    private void bfs(boolean[] vertexFlag, int i) {
        System.out.print(vertexList.get(i) + "节点走过  ");
        vertexFlag[i] = true;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(i);
        while (!queue.isEmpty()) {
            i = queue.removeFirst();
            int j = getFirstApproximal(i);
            while (j != -1) {
                if (!vertexFlag[j]) {
                    System.out.print(vertexList.get(j) + "节点走过  ");
                    vertexFlag[j] = true;
                    queue.addLast(j);
                }
                j = getApproximalPoint(i, j);
            }
        }
    }

    // 测试
    public static void main(String[] args) {
        String[] testVertex = {"张三", "李四", "王五", "赵六", "王二麻子"};
        Graph graph = new Graph(5);
        for (String s : testVertex) {
            graph.add(s);
        }
        graph.connectionMatrix(0, 1, 1);
        graph.connectionMatrix(0, 2, 1);
        graph.connectionMatrix(1, 2, 1);
        graph.connectionMatrix(1, 3, 1);
        graph.connectionMatrix(1, 4, 1);

        // 打印
        graph.show();
//        graph.dfs();
        graph.bfs();
    }
}
