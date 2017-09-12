package com.example.myapplication9;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bingnanfeng02 on 2017/8/13.
 */
public class Else {
    public static boolean isMobile(String number) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0-9]))\\d{8}$");

        Matcher m = p.matcher(number);

        return m.matches();
    }
    public   static   boolean   iszimu(String   s)
    {
        char   c   =   s.charAt(0);
        int   i   =(int)c;
        if((i>=65&&i<=90)||(i>=97&&i<=122))
        {
            return   true;
        }
        else
        {
            return   false;
        }
    }
    public static boolean ishanzi(String str){

        char[] chars=str.toCharArray();
        boolean isGB2312=false;
        for(int i=0;i<chars.length;i++){
            byte[] bytes=(""+chars[i]).getBytes();
            if(bytes.length==2){
                int[] ints=new int[2];
                ints[0]=bytes[0]& 0xff;
                ints[1]=bytes[1]& 0xff;
                if(ints[0]>=0x81 && ints[0]<=0xFE && ints[1]>=0x40 && ints[1]<=0xFE){
                    isGB2312=true;
                    break;
                }
            }
        }
        return isGB2312;
    }
}
