import org.junit.Test;

import java.util.List;

/**
 * @author Brayden
 * @create 5/9/21 5:26 PM
 * @Description
 */
public class ThreeSumTest
{
	@Test
	public void test3sum() {
		Solution a = new Solution();
		List<List<Integer>> result = a.threeSum(new int[] { -1, 0, 1, 2, -1, -4 });
		System.out.println(result);
	}
}
