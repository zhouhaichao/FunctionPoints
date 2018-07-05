package com.example.point.functionpoints.util;

import com.example.point.functionpoints.R;

/**
 * Created by bobo on 2018/7/4.
 */

public class RandomImage {

    public static int getFruitImage()
    {
        int imageRes = R.drawable.ic_fruit_1;

        switch ((int)(Math.random()*10))
        {
            case 1:
                imageRes = R.drawable.ic_fruit_1;
                break;
            case 2:
                imageRes = R.drawable.ic_fruit_2;
                break;
            case 3:
                imageRes = R.drawable.ic_fruit_3;
                break;
            case 4:
                imageRes = R.drawable.ic_fruit_4;
                break;
            case 5:
                imageRes = R.drawable.ic_fruit_5;
                break;
            case 6:
                imageRes = R.drawable.ic_fruit_6;
                break;
            case 7:
                imageRes = R.drawable.ic_fruit_7;
                break;
            case 8:
                imageRes = R.drawable.ic_fruit_8;
                break;
            case 9:
                imageRes = R.drawable.ic_fruit_9;
                break;
            case 0:
                imageRes = R.drawable.ic_fruit_10;
                break;

        }
        return imageRes;
    }

}
