package codecheck;

public class App {
	public static void main(String[] args) {
        for (int i = 1, l = args.length; i < l; i++) {
            if(args[0].charAt(args[0].length()-1) == args[i].charAt(0)){
                System.out.println(args[i]);
                return;
            }
        }
        System.out.println("string not found");
	}
}
