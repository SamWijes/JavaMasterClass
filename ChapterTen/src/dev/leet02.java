package dev;

import javax.management.ObjectName;
import java.util.Arrays;


class ListNode1 {
    int val;
    ListNode1 next;
    ListNode1() {}
    ListNode1(int val) { this.val = val; }




    ListNode1(int val, ListNode1 next) { this.val = val; this.next = next;


    }
}
public class leet02 {

    public static void main(String[] args) {
        Solution sol=new Solution();
        int[] l1arr={2,4,3};
        int[] l2arr={5,6,4};
        ListNode1 l1=new ListNode1();
        fillNode(l1,l1arr);
        ListNode1 l2=new ListNode1();
        fillNode(l2,l2arr);

//        LinkTraverse(l1);
//        LinkTraverse(l2);
        int carry=0;
        while (l1.next!=null || l2.next!=null){

        }

    }

    public static void fillNode(ListNode1 l1, int[] l1Array){
        ListNode1 currentNode=l1;
        for (int j = 0; j <l1Array.length ; j++) {
            currentNode.val=l1Array[j];
            currentNode.next=new ListNode1();
            currentNode=currentNode.next;

        }



    }
    public static void LinkTraverse(ListNode1 l1){
        ListNode1 node=l1;
        System.out.print("link ");
        while(node.next!=null){
            System.out.print(node.val+" ");
            node=node.next;
        }
        System.out.println();
    }
}
class Solution {
    public ListNode1 addTwoNumbers(ListNode1 l1, ListNode1 l2) {
        //int len1 = getLength(l1);
//        int l1val=ListValue(l1);
//        int l2val=ListValue(l2);


        return l1;
    }

}