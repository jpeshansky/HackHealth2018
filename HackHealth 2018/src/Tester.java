/* Jennifer Peshansky
 * 111049923
 * CSE 260
 * Feb 3, 2018 - HackHealth 2018 
 */

public abstract class Tester {
	public static void main(String[] args) {
		User u = new User();
		u.setName("THIS IS MY NAME NOW");
		for (int i = 0; i < 100; i++) u.getOlder();
		u.save();
		User t = new User(true);
		t.defeatEnemy(1);
		t.save();
	}
}
