package com.example.point.functionpoints.util;

import com.example.point.functionpoints.R;

/**
 * Created by bobo on 2018/7/4.
 */

public class RandomImage {

    public static int getFruitImage() {
        int imageRes = R.drawable.ic_fruit_1;

        switch ((int) (Math.random() * 10)) {
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

    public static int getGradientBG() {
        int randomRes = R.drawable.bg_radius10_gradient2;

        switch ((int) (Math.random() * 6)) {
            case 1:
                randomRes = R.drawable.bg_radius10_gradient1;
                break;
            case 2:
                randomRes = R.drawable.bg_radius10_gradient2;
                break;
            case 3:
                randomRes = R.drawable.bg_radius10_gradient3;
                break;
            case 4:
                randomRes = R.drawable.bg_radius10_gradient4;
                break;
            case 5:
                randomRes = R.drawable.bg_radius10_gradient5;
                break;
            case 0:
                randomRes = R.drawable.bg_radius10_gradient6;
                break;
        }
        return randomRes;
    }
    public static int getGradientBG(int num) {
        int randomRes = R.drawable.bg_radius10_gradient2;

        switch ((num%6)) {
            case 1:
                randomRes = R.drawable.bg_radius10_gradient1;
                break;
            case 2:
                randomRes = R.drawable.bg_radius10_gradient2;
                break;
            case 3:
                randomRes = R.drawable.bg_radius10_gradient3;
                break;
            case 4:
                randomRes = R.drawable.bg_radius10_gradient4;
                break;
            case 5:
                randomRes = R.drawable.bg_radius10_gradient5;
                break;
            case 0:
                randomRes = R.drawable.bg_radius10_gradient6;
                break;
        }
        return randomRes;
    }
}
