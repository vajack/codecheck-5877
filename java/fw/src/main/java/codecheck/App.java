package codecheck;
import java.io.*;
import java.util.ArrayList;

public class App {
	public static void main(String[] args) {
		
        String selectPlayer[] = new String[2];
        selectPlayer[0] = args[0];
        selectPlayer[1] = args[1];
        
        String anserWord = args[2];
        ArrayList<String> words = new ArrayList<String>();
        for (int i = 3, l = args.length; i < l; i++) {
            words.add(args[i]);
        }
        
        int turn = 0;
        while(anserWord != "...."){
            String decAns[] = new String[2];
            decAns = decisionAns(createCmd(selectPlayer[turn],anserWord,words));
            if (turn == 0){
                System.out.println("FIRST (" + decAns[0] + "): " + decAns[1]);
                turn = 1;
            }else{
                System.out.println("SECOND (" + decAns[0] + "): " + decAns[1]);
                turn = 0;
            }
            anserWord = decAns[1];
            if (decAns[0] == "OK") {
                words.remove(words.indexOf(anserWord));
            }
        }
        if (turn == 0){
            System.out.println("WIN - FIRST");
        }else{
            System.out.println("WIN - SECOND");
        }
	}
    
    public static String createCmd(String player,String preAns,ArrayList words){
        
        String cmd = player + " " + preAns;
        for (int i = 0, l = words.size(); i < l; i++) {
            cmd = cmd + " " + words.get(i);
        }
        
        return cmd;
    }
    
    
    public static String[] decisionAns(String cmd){
        
        String ans[] = new String[2];
        
        try {
            Runtime aiFirst = Runtime.getRuntime();
            Process resultProcess = aiFirst.exec(cmd);
            InputStream resultFirstAI = resultProcess.getInputStream();
            BufferedReader resultReader = new BufferedReader(new InputStreamReader(resultFirstAI));
            
            ans[1] = resultReader.readLine();
            
            if(ans[1] == null){
                ans[0] = "NG";
                ans[1] = "....";
            }else{
                ans[0] = "OK";
            }
        } catch (IOException ex) {
            ans[0] = "system error.";
        }
        
        return ans;
    }
}
