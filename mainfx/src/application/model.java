package application;
public class model extends loginController {
	
public int comp(String a,String b) {
	if(a.equals(b)) {
		System.out.println("true");
		return 1;
	}
	System.out.println("false : "+a.equals(b));
	return 0;
}

}
