package com.zhou.linkedlist;

public class Code_08_ZigZagPrintMatrix {
	public static void printMatrixZigZag(int[][] matrix) {
		int tR = 0;
		int tC = 0;
		int dR = 0;
		int dC = 0;
		int endR = matrix.length - 1;
		int endC = matrix[0].length - 1;
		boolean fromUp = false;
		while (tR != endR + 1) {
			printLevel(matrix, tR, tC, dR, dC, fromUp);
			//a点的行  根据他的列来判断，如果到了最右列，行数加一，往下走，否则行数不变
			tR = tC == endC ? tR + 1 : tR;
			//a点的列  如果往右到了最右列，列数不再动了，如果没到最右列，一直往右加
			tC = tC == endC ? tC : tC + 1;
			//b点的列 根据他的行来判断 如果到了最下行 那么才会一直往右走
			dC = dR == endR ? dC + 1 : dC;
			//b点的行 如果往下到了最下行，行数不再动，如果没到最下行，那么一直往下走
			dR = dR == endR ? dR : dR + 1;
			fromUp = !fromUp;
		}
		System.out.println();
	}
	//根据布尔值来判断 到底怎么打印这个 对角线
	public static void printLevel(int[][] m, int tR, int tC, int dR, int dC,boolean f) {
		if (f) {
			while (tR != dR + 1) {
				System.out.print(m[tR++][tC--] + " ");
			}
		} else {
			while (dR != tR - 1) {
				System.out.print(m[dR--][dC++] + " ");
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		printMatrixZigZag(matrix);

	}

}
