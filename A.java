import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            boolean[][] rel1 = new boolean[n][n];
            boolean[][] rel2 = new boolean[n][n];
            fill(rel1, scanner, n);
            fill(rel2, scanner, n);

            outputInfo(rel1);
            outputInfo(rel2);

            boolean[][] composition = composition(rel1, rel2);
            for (int i = 0; i < rel1.length; i++) {
                for (int j = 0; j < rel1.length; j++) {
                    System.out.print((composition[i][j] ? 1 : 0) + " ");
                }
                System.out.println();
            }
        }
    }

    private static void outputInfo(boolean[][] rel) {
        System.out.printf("%d %d %d %d %d\n",
                isReflexive(rel) ? 1 : 0,
                isIrreflexive(rel) ? 1 : 0,
                isSymmetric(rel) ? 1 : 0,
                isAntisymmetric(rel) ? 1 : 0,
                isTransitive(rel) ? 1 : 0);
    }

    static void fill(boolean[][] arr, Scanner scanner, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = scanner.nextInt() == 1;
            }
        }
    }

    private static boolean reflexivity(boolean[][] rel, boolean isAnti) {
        for (int i = 0; i < rel.length; i++) {
            if (rel[i][i] == isAnti) return false;
        }
        return true;
    }

    static boolean isReflexive(boolean[][] rel) {
        return reflexivity(rel, false);
    }

    static boolean isIrreflexive(boolean[][] rel) {
        return reflexivity(rel, true);
    }

    private static boolean symmetry(boolean[][] rel, boolean isAnti) {
        for (int i = 0; i < rel.length; i++) {
            for (int j = 0; j < i; j++) {
                if (rel[i][j]) {
                    if (rel[j][i] == isAnti) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static boolean isSymmetric(boolean[][] rel) {
        return symmetry(rel, false);
    }

    static boolean isAntisymmetric(boolean[][] rel) {
        return symmetry(rel, true);
    }

    static boolean isTransitive(boolean[][] rel) {
        for (int i = 0; i < rel.length; i++) {
            for (int j = 0; j < rel.length; j++) {
                if (!rel[i][j]) {
                    for (int k = 0; k < rel.length; k++) {
                        if (rel[i][k] && rel[k][j]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    static boolean[][] composition(boolean[][] rel1, boolean[][] rel2) {
        boolean[][] result = new boolean[rel1.length][rel1.length];
        for (int i = 0; i < rel1.length; i++) {
            for (int j = 0; j < rel2.length; j++) {
                for (int k = 0; k < rel1.length; k++) {
                    if (rel1[i][k] && rel2[k][j]) {
                        result[i][j] = true;
                    }
                }
            }
        }
        return result;
    }
}