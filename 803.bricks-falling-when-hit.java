import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=803 lang=java
 *
 * [803] Bricks Falling When Hit
 *
 * https://leetcode.com/problems/bricks-falling-when-hit/description/
 *
 * algorithms
 * Hard (31.16%)
 * Total Accepted:    17.7K
 * Total Submissions: 55.8K
 * Testcase Example:  '[[1,0,0,0],[1,1,1,0]]\n[[1,0]]'
 *
 * You are given an m x n binary grid, where each 1 represents a brick and 0
 * represents an empty space. A brick is stable if:
 * 
 * 
 * It is directly connected to the top of the grid, or
 * At least one other brick in its four adjacent cells is stable.
 * 
 * 
 * You are also given an array hits, which is a sequence of erasures we want to
 * apply. Each time we want to erase the brick at the location hits[i] = (rowi,
 * coli). The brick on that location (if it exists) will disappear. Some other
 * bricks may no longer be stable because of that erasure and will fall. Once a
 * brick falls, it is immediately erased from the grid (i.e., it does not land
 * on other stable bricks).
 * 
 * Return an array result, where each result[i] is the number of bricks that
 * will fall after the i^th erasure is applied.
 * 
 * Note that an erasure may refer to a location with no brick, and if it does,
 * no bricks drop.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
 * Output: [2]
 * Explanation: Starting with the grid:
 * [[1,0,0,0],
 * ⁠[1,1,1,0]]
 * We erase the underlined brick at (1,0), resulting in the grid:
 * [[1,0,0,0],
 * ⁠[0,1,1,0]]
 * The two underlined bricks are no longer stable as they are no longer
 * connected to the top nor adjacent to another stable brick, so they will
 * fall. The resulting grid is:
 * [[1,0,0,0],
 * ⁠[0,0,0,0]]
 * Hence the result is [2].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
 * Output: [0,0]
 * Explanation: Starting with the grid:
 * [[1,0,0,0],
 * ⁠[1,1,0,0]]
 * We erase the underlined brick at (1,1), resulting in the grid:
 * [[1,0,0,0],
 * ⁠[1,0,0,0]]
 * All remaining bricks are still stable, so no bricks fall. The grid remains
 * the same:
 * [[1,0,0,0],
 * ⁠[1,0,0,0]]
 * Next, we erase the underlined brick at (1,0), resulting in the grid:
 * [[1,0,0,0],
 * ⁠[0,0,0,0]]
 * Once again, all remaining bricks are still stable, so no bricks fall.
 * Hence the result is [0,0].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * grid[i][j] is 0 or 1.
 * 1 <= hits.length <= 4 * 10^4
 * hits[i].length == 2
 * 0 <= xi <= m - 1
 * 0 <= yi <= n - 1
 * All (xi, yi) are unique.
 * 
 * 
 */
class BrickHit {
    public int[] hitBricks(int[][] grid, int[][] hits) {
        //row count
    	final int m = grid.length;
    	//column count
        final int n = grid[0].length;

        final int t = hits.length;
        //connected bricks count each time
		int size;
		//result count each time
		int[] result = new int[t];
		//empty erasure
		Boolean[] emptyErasure = new Boolean[t];


        UnionFind gridUnion = new UnionFind(m * n + 1);
		//the last item (No. (m * n + 1)) is considered as ceil root
        final int ceil = m * n;

        //apply all erasures
		for (int i = 0; i < t; i++) {
			int[] hit = hits[i];
			//record empty erasure for later use (count)
			emptyErasure[i] = false;
			if (grid[hit[0]][hit[1]] == 0) {
				emptyErasure[i] = true;
			};
			grid[hit[0]][hit[1]] = 0;
		}

		//union first row brick to the ceil
		for (int c = 0; c < n; c++) {
			if (grid[0][c] == 1) {
				gridUnion.union(c, ceil);
			}
		}

		//union existing bricks
		for (int r = 1; r < m; r++) {
			for (int c = 0; c < n; c++) {
				//up brick judge
				if (grid[r][c] == 1) {
					if (grid[r - 1][c] == 1)
					{
						gridUnion.union(r * n + c, (r - 1) * n + c);
					}
					//left brick judge
					if (c > 0 && grid[r][c - 1] == 1)
					{
						gridUnion.union(r * n + c, r * n + c - 1);
					}
				}
			}
		}

		//calculate connected bricks after erasures apply
		size = gridUnion.sizeOf(ceil);

		//reverse each erasure
		for (int i = hits.length - 1; i >= 0; i--) {
			if (!emptyErasure[i])
			{
				grid[hits[i][0]][hits[i][1]] = 1;
				unionAdjacent(hits[i][0], hits[i][1], grid, gridUnion);

				//get dropped number based on the difference between before erasure and apply erasure
				int drop = gridUnion.sizeOf(ceil) - size - 1;
				result[i] = drop >= 0 ? drop : 0;
				size = gridUnion.sizeOf(ceil);
			} else {
				result[i] = 0;
			}
		}
        return result;
    }

	private static void unionAdjacent(int row, int column, int[][] grid, UnionFind gridUnion)
	{
		//row count
		final int m = grid.length;
		//column count
		final int n = grid[0].length;

		final int ceil = m * n;

		if (row == 0) {
			gridUnion.union(row * n + column, ceil);
		}

		if (row - 1 >= 0 && grid[row - 1][column] == 1) {
			gridUnion.union(row * n + column, (row - 1) * n + column);
		}
		if (row + 1 < m && grid[row + 1][column] == 1) {
			gridUnion.union(row * n + column, (row + 1) * n + column);
		}
		if (column - 1 >= 0 && grid[row][column - 1] == 1) {
			gridUnion.union(row * n + column, row * n + column - 1);
		}
		if (column + 1 < n && grid[row][column + 1] == 1) {
			gridUnion.union(row * n + column, row * n + column + 1);
		}
	}
	public class UnionFind {

		// TODO - Add instance variables?
		int[] parent;

		/* Creates a UnionFind data structure holding n vertices. Initially, all
		   vertices are in disjoint sets. */
		public UnionFind(int n) {
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = -1;
			}
		}

		/* Throws an exception if v1 is not a valid index. */
		private void validate(int vertex) {
			if (vertex < 0 || vertex >= parent.length) {
				throw new IllegalArgumentException("index not valid");
			}
		}

		/* Returns the size of the set v1 belongs to. */
		public int sizeOf(int v1) {
			validate(v1);
			if (parent[v1] <= -1)
			{
				return Math.abs(parent[v1]);
			} else {
				return sizeOf(parent[v1]);
			}
		}

		/* Returns the parent of v1. If v1 is the root of a tree, returns the
		   negative size of the tree for which v1 is the root. */
		public int parent(int v1) {
			validate(v1);
			return parent[v1];
		}

		/* Returns true if nodes v1 and v2 are connected. */
		public boolean connected(int v1, int v2) {
			validate(v1);
			validate(v2);
			int parent1 = find(v1);
			int parent2	= find(v2);
			return parent1 == parent2;
		}

		/* Connects two elements v1 and v2 together. v1 and v2 can be any valid
		   elements, and a union-by-size heuristic is used. If the sizes of the sets
		   are equal, tie break by connecting v1's root to v2's root. Unioning a
		   vertex with itself or vertices that are already connected should not
		   change the sets but may alter the internal structure of the data. */
		public void union(int v1, int v2)
		{
			int parent1 = find(v1);
			int parent2 = find(v2);
			if (parent1 != parent2)
			{
				if (parent[parent1] <= parent[parent2])
				{
					parent[parent1] += parent[parent2];
					parent[parent2] = parent1;
				}
				else if (parent[parent1] > parent[parent2])
				{
					parent[parent2] += parent[parent1];
					parent[parent1] = parent2;
				}
			}
		}

		/* Returns the root of the set V belongs to. Path-compression is employed
		   allowing for fast search-time. */
		public int find(int vertex) {
			validate(vertex);
			int currentV = vertex;
			List<Integer> compressionList = new ArrayList();
			while (parent[currentV] >= 0) {
				compressionList.add(currentV);
				currentV = parent[currentV];
			}
			int root = currentV;

			//path compression
			for (Integer i : compressionList) {
				parent[i] = root;
			}
			return root;
		}

		public void printArray() {
			for (int i = 0; i < parent.length; i++)
			{
				System.out.print(i + " ");
			}
			System.out.println("");
			for (int p : parent)
			{
				System.out.print(p + " ");
			}
			System.out.println("");
		}

	}
	public static void main(String[] args)
    {

    }
}
