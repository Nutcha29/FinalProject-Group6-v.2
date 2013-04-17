package example;

public class testaction {
	public static void main(String[] args){
		ProcessAction pa = new ProcessAction();
		Action[] a = pa.getAllActions();
		for(int i=0; i<a.length;i++){
			System.out.print(a[i].aid+"\t");
			System.out.print(a[i].type+"\t");
			System.out.print(a[i].uid+"\t");
			System.out.print(a[i].timestamp+"\t");
			System.out.print(a[i].detail+"\t");
			System.out.print("\n");
		}
	}
}
