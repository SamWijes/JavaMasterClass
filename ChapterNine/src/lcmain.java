import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class lcmain {
    public static void main(String[] args) {
        Solution s=new Solution();
        System.out.println(s.lengthOfLongestSubstring("pwwkew"));
    }

}

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<String> charSet = new HashSet<>();

        String subStr="";
        int strLen=0;
        if (s.isEmpty()){
            return 0;
        }
        if (s.isBlank()||s.length()==1){
            return 1;
        }



        for(int j=0;j<s.length();j++){
            subStr="";
            charSet.clear();
            subStr+=String.valueOf( s.charAt(j));
            charSet.add(String.valueOf( s.charAt(j)));

            for(int i=j+1;i<s.length();i++){
                String strCh=String.valueOf( s.charAt(i));
                if (charSet.contains(strCh)){
                    //System.out.println(subStr+" here  "+i);
                    strLen =Integer.max(strLen,subStr.length());
                    j=i-1;
                    break;
                       //pwwkew

                }else{
                    subStr+=strCh;

                    charSet.add(strCh);

                }

            }
            System.out.println(subStr+" "+strLen+" "+j);
        }
        return strLen;


    }
}