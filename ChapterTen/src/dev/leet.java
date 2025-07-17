package dev;

import java.util.Arrays;

class ListNode {
      int val;
      ListNode1 next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode1 next) { this.val = val; this.next = next; }
  }
public class leet {
    public static void main(String[] args) {
        Solution sol=new Solution();
        ListNode1 l1=new ListNode1();
        ListNode1 l2=new ListNode1();

        int[] l1arr={2,4,3};
        int[] l2arr={5,6,4};

        fillNode(l1,l1arr,0);
        fillNode(l2,l2arr,0);

        sol.addTwoNumbers(l1,l2);
    }

    public static void fillNode(ListNode1 l1, int[] l1Array, int i){
        if (i==l1Array.length){
            return ;
        }
        l1.val=l1Array[i];
        l1.next=new ListNode1();
        i++;
        fillNode(l1.next,l1Array,i);
    }

}
//
//class Solution {
//    public ListNode1 addTwoNumbers(ListNode1 l1, ListNode1 l2) {
//
//        int l1val=ListValue(l1);
//        int l2val=ListValue(l2);
//        int result=l1val+l2val;
//        StringBuilder res=new StringBuilder(String.valueOf(result));
//        res.reverse();
//
//        int[] lstarray=new int[res.length()];
//        System.out.println(res);
//        for (int i = 0; i < res.length(); i++) {
//            lstarray[i]=Integer.parseInt(String.valueOf(res.charAt(i)));
//
//        }
//
//        System.out.println(Arrays.toString(lstarray));
//        System.out.println(l1val+l2val);
//        ListNode1 resList=new ListNode1();
//        fillNode(resList,lstarray,0);
//        return resList;
//    }
//    public int ListValue(ListNode1 l1){
//        StringBuilder valL1= new StringBuilder("");
//        ListNode1 node=l1;
//
//        while(node.next!=null){
//            ListNode1 nextNode=node.next;
//            valL1.append(node.val);
//            node=nextNode;
//        }
//        System.out.println(valL1.reverse());
//        return Integer.parseInt(valL1.reverse().toString());
//    }
//
//    public static void fillNode(ListNode1 l1, int[] l1Array, int i){
//        if (i==l1Array.length){
//            return ;
//        }
//        l1.val=l1Array[i];
//        l1.next=new ListNode1();
//        i++;
//        fillNode(l1.next,l1Array,i);
//    }
//}