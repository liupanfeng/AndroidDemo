package com.liupf.androidstudy.algorithm;

/**
 * Created by lpf on 2017/9/7.
 * 回文数：正着读和反着读都一样，称之为回文数。
 */
public class PalindromNumber {

    public static void main(String[] args) {
        int a = 12343210;
//        int b =getWei(a);
        int b = a / 10;
        boolean t=isPalindrome(a);
        System.out.println(a+"是回文数："+t);

    }

    /**
     * 获取整数的位数  依据的原理是，java的向0取整的原理。
     * 根据取余数跟0作比较，来获取数字的位数
     *
     * @param num
     * @return
     */
    public static int getWei(int num) {
        int count = 1;
        while (num / 10 > 0) {
            num /= 10;
            count++;
        }
        return count;
    }

    public static int pow(int m, int n) {
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= m;
        }
        return res;
    }

    /**
     * 判断数字是否是回文数
     *
     * @param num 带判断的数字
     * @return
     */
    public static boolean isPalindrome(int num) {
        boolean isPalindrome = false;
        if (num < 0) {//判断负数的情况，负数不是回文数
            return false;
        }
        if (num < 10) {//个位数直接就是回文数
            return true;
        } else {
            int wei = getWei(num);//看看这个数字是多少位的数字
            int pow = pow(10, wei - 1);///这里决定这个情况下，必须是两位数字   这里是得到与此数字匹配的位数标志
            int x=num;
            int half=wei/2;
            int i=0;
            while(i<half){
                int a=x/pow%10;//  这个是得到高位数字
                int b=num%10;// 这个是获取低位上的数字
                if(a==b){
                    pow/=10;//这样可以得到次高位数
                    num/=10;//这样可以得到次低位
                }else{
                    return false;
                }
                i++;
            }
            return true;
        }
    }

}
