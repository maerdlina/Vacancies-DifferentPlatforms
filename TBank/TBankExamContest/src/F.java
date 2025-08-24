import java.util.*;

public class F {
    private int[] U, V, dir, par;
    private boolean[] vis;
    private List<List<Edge>> graph;
    private int n, m;


    private static class Edge {
        int to, id;

        Edge(int to, int id) {
            this.to = to;
            this.id = id;
        }
    }

    public F(int n, int m, int[][] edges) {
        this.n = n;
        this.m = m;
        U = new int[m];
        V = new int[m];
        graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            U[i] = edges[i][0];
            V[i] = edges[i][1];
            graph.get(U[i]).add(new Edge(V[i], i));
            graph.get(V[i]).add(new Edge(U[i], i));
        }

        dir = new int[m];
        vis = new boolean[n + 1];
        par = new int[n + 1];
    }

    public void solve() {
        for (int s = 1; s <= n; s++) {
            if (!vis[s]) {
                if (!dfs(s, -1)) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < m; i++) {
            if (dir[i] == +1) {
                output.append(U[i]).append(" ").append(V[i]).append("\n");
            } else {
                output.append(V[i]).append(" ").append(U[i]).append("\n");
            }
        }
        System.out.print(output);
    }

    private boolean dfs(int u, int parentEdge) {
        vis[u] = true;
        for (Edge edge : graph.get(u)) {
            int v = edge.to;
            int eid = edge.id;

            if (eid == parentEdge) continue;
            if (dir[eid] != 0) continue;

            if (!vis[v]) {
                if (!dfs(v, eid)) return false;
                if (par[v] == 1) {
                    setDir(eid, v); // v -> u
                    par[v] ^= 1;
                } else {
                    setDir(eid, u); // u -> v
                    par[u] ^= 1;
                }
            } else {
                setDir(eid, u);
                par[u] ^= 1;
            }
        }
        return true;
    }

    private void setDir(int eid, int tail) {
        dir[eid] = (U[eid] == tail) ? +1 : -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] edges = new int[m][2];

        for (int i = 0; i < m; i++) {
            edges[i][0] = scanner.nextInt();
            edges[i][1] = scanner.nextInt();
        }

        F graphDirection = new F(n, m, edges);
        graphDirection.solve();
    }
}
