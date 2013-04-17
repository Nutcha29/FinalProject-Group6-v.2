package example;

public class getUD {
	public static void main(String[] args){
		User[] users;
		ProcessState ps = new ProcessState();
		users = ps.getAllUserData();
		for (int i =0; i<users.length;i++){
			System.out.println(users[i].getAllUserData()+"\n");
		}
	}
	
}
