/**
 * @author Brayden
 * @create 1/28/21 7:36 PM
 * @Description
 */
public class test
{
	public static void main(String[] args)
	{
		String a = ",2012";
		String b = "201,";
		String c = ",";
		String d = "";
		String e = null;

		String[] arr1 = a.split(",");
		String[] arr2 = b.split(",");
		String[] arr3 = c.split(",");
		String[] arr4 = d.split(",");

		for(String m : arr4) {
			if(m.equals("")){
				System.out.println("blank");
			}else
			{
				System.out.println(m);
			}
		}
	}
}
