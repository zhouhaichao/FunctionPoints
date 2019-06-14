package com.example.point.functionpoints.activity.shitu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.point.functionpoints.R;
import com.example.point.functionpoints.util.DensityUtil;
import com.example.point.functionpoints.view.CameraPreview;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HaiChao on 2019/6/13.
 */
public class CameraActivity extends Activity implements
        Camera.AutoFocusCallback,
        Camera.PictureCallback {

    private final int ALLOW_PIC_LEN = 2000;       //最大允许的照片尺寸的长度   宽或者高

    @BindView(R.id.sfv_preview)
    FrameLayout fl_camera;//预览窗口
    @BindView(R.id.btn_record)
    ImageView btnRecord;//拍照按钮

    @BindView(R.id.btn_cancel)
    ImageView btn_cancel;//取消拍照按钮

    @BindView(R.id.btn_comfrim)
    ImageView btn_comfrim;//图片确认按钮

    @BindView(R.id.iv_image)
    ImageView iv_image;//图片展示按钮

    @BindView(R.id.btn_returnCamera)
    ImageView btn_returnCamera;//图片展示按钮

    @BindView(R.id.rl_camera)
    RelativeLayout rl_camera;//拍照按钮

    @BindView(R.id.ll_photo)
    LinearLayout ll_photo;//拍照按钮

    private Bitmap bitmap;

    private Camera camera = null;//相机
    private SurfaceHolder holder;//SurfaceView控制器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
        initView();
//        RxPermissions rxPermissions = new RxPermissions(this);
//        rxPermissions.request(Manifest.permission.CAMERA).subscribe(new Consumer<Boolean>() {
//            @Override
//            public void accept(Boolean aBoolean) throws Exception {
//                if(aBoolean){
//
//                }else{
//                    Toast.makeText(CameraActivity.this,"没有相机权限",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


    }

    /**
     * 初始化
     */
    private void initView() {

        camera = Camera.open();    //初始化 Camera对象
        CameraPreview mPreview = new CameraPreview(this, camera);
        fl_camera.addView(mPreview);

        holder = mPreview.getHolder();

        Camera.Parameters mParameters = camera.getParameters();
        mParameters.setPictureFormat(PixelFormat.JPEG);

        List<String> focusModes = mParameters.getSupportedFocusModes();


        //设置对焦模式
        if (focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO))
            mParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);

        try {
            camera.setParameters(mParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 修正surfaceView的宽高
        //mCameraManager.setFitPreSize(camera);

        Camera.Size adapterSize = camera.getParameters().getPreviewSize();

        Camera.Parameters parameters = camera.getParameters();

        try {
            Camera.Size size = findFitPicResolution(camera, adapterSize);
            parameters.setPreviewSize(size.width, size.height);
            parameters.setPictureSize(size.width, size.height);
            camera.setParameters(parameters);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Camera.Size findFitPicResolution(Camera camera, Camera.Size adapterSize) throws Exception {

        Camera.Size resultSize = null;

        float bl = (float) adapterSize.width / adapterSize.height;
        Camera.Parameters cameraParameters = camera.getParameters();
        List<Camera.Size> supportedPicResolutions = cameraParameters.getSupportedPictureSizes();


        for (Camera.Size size : supportedPicResolutions) {

            if (adapterSize.width >= size.width && adapterSize.height >= size.height) {
                resultSize = size;
                break;
            }

//            if ((float) size.width / size.height == bl && size.width <= ALLOW_PIC_LEN && size.height <= ALLOW_PIC_LEN) {
//                if (resultSize == null) {
//                    resultSize = size;
//                } else if (size.width > resultSize.width) {
//                    resultSize = size;
//                }
//            }
        }
        if (resultSize == null) {
            return supportedPicResolutions.get(0);
        }
        return resultSize;
    }

    /**
     * 点击事件
     */
    @OnClick({R.id.btn_record, R.id.sfv_preview, R.id.btn_cancel, R.id.btn_comfrim, R.id.btn_word, R.id.btn_returnCamera})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_record:
                camera.takePicture(null, null, this);//开始拍照
                break;
            case R.id.sfv_preview:
                camera.autoFocus(this);//自动对焦
                break;
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.btn_comfrim:
                //addWord(this,"dsajodjo");
                break;
            case R.id.btn_word:
                addWord(this,"dsajodjo");
                break;
            case R.id.btn_returnCamera:
                showCamera();
                hidePictrue();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (camera==null){
//            camera=getCamera();
//        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    /**
     * 获取相机
     *
     * @return
     */
    private Camera getCamera() {
        return Camera.open();
    }


    /**
     * 释放资源
     */
    public void releaseCamera() {
//        if (camera!=null){
//            camera.setPreviewCallback(null);//取消回调
//            camera.stopPreview();//停止预览
//            camera.release();//释放资源
//            camera=null;//清除对象
//        }
    }

    /**
     * 自动对焦回调
     *
     * @param success
     * @param camera
     */
    @Override
    public void onAutoFocus(boolean success, Camera camera) {
        if (success) {

        }
    }

    /**
     * 获取图片数据回调
     *
     * @param data
     * @param camera
     */
    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        Bitmap bp = BitmapFactory.decodeByteArray(data, 0, data.length);
        bitmap = addWaterMark(this, rotateBitmap(bp, 90), "滨将省市对比焦点奇偶暗示将建瓯家搜到就大爱仕达", System.currentTimeMillis());
        bp.recycle();
        bp = null;

        iv_image.setImageBitmap(bitmap);
        showPictrue();
        hideCamera();


//        File savePath = Environment.getExternalStorageDirectory();
//        String saveFileName = "Capture" + System.currentTimeMillis() + ".jpg";
//        File saveFile = new File(savePath, saveFileName);
//        FileOutputStream fos=null;
//        try {
//            fos=new FileOutputStream(saveFile);
//            fos.write(data);//写入数据
//            fos.flush();
//            Toast.makeText(this, "照片已保存在"+saveFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//            camera.stopPreview();
//            camera.startPreview();//重新进入预览状态
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            if(fos!=null){
//                try {
//                    fos.close();//关闭输出流
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

    private Bitmap rotateBitmap(Bitmap origin, float alpha) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(alpha);
        // 围绕原地进行旋转
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        origin.recycle();
        return newBM;
    }


    private void hideCamera() {
        rl_camera.setVisibility(View.GONE);
        camera.stopPreview();
    }

    private void showCamera() {
        rl_camera.setVisibility(View.VISIBLE);
        camera.startPreview();
    }

    private void showPictrue() {
        ll_photo.setVisibility(View.VISIBLE);
    }

    private void hidePictrue() {
        ll_photo.setVisibility(View.GONE);
    }


    public  Bitmap addWaterMark(Context context, Bitmap src, String addr, long time) {
        context = context.getApplicationContext();
        int addrSize = DensityUtil.dip2px(context, 15);//地址字体大小
        int dateSize = DensityUtil.dip2px(context, 25);//日期字体大小
        int timeSize = DensityUtil.dip2px(context, 45);//时间字体大小
        int space = DensityUtil.dip2px(context, 20);//边距
        int rowSpace = DensityUtil.dip2px(context, 5);//行高

        int width = src.getWidth();
        int height = src.getHeight();

        String dateStr = new SimpleDateFormat("MM-dd  HH:mm").format(new Date(time));
        String timeStr = new SimpleDateFormat("yyyy").format(new Date(time));


        int startY = 0;

        float addrLenght = 0;

        Bitmap bmpTemp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpTemp);
        Paint p = new Paint();
        String familyName = "宋体";
        Typeface font = Typeface.create(familyName, Typeface.NORMAL);
        p.setColor(Color.WHITE);
        p.setAlpha(0xcc);
        p.setTypeface(font);
        p.setTextSize(addrSize);


        addrLenght = p.measureText(addr);
        int row = (int) (addrLenght / width);


        canvas.drawBitmap(src, 0, 0, p);

        startY = height - space;//最底部出去space

        startY = startY - row * (rowSpace + addrSize);//除去地址文字高度

        canvas.drawText(addr, space, startY, p);

        startY = startY - (rowSpace + addrSize);// 画分界线的高度

        p.setColor(Color.WHITE);
        p.setAlpha(0xFF);
        p.setTextSize(dateSize);
        p.setStrokeWidth(4);

        canvas.drawLine(space, startY, addrLenght + space, startY, p);

        p.setStrokeWidth(1);

        startY = startY - rowSpace * 2;//画 日期
        canvas.drawText(dateStr, space, startY, p);


        startY = startY - dateSize - rowSpace;//画时间
        p.setTextSize(timeSize);
        p.setStrokeWidth(2);
        canvas.drawText(timeStr, space, startY, p);

        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        return bmpTemp;
    }

    public void addWord(Context context,String word){
        int timeSize = DensityUtil.dip2px(context, 35);//字体大小
        Canvas canvas = new Canvas(bitmap);
        Paint p = new Paint();
        String familyName = "宋体";
        Typeface font = Typeface.create(familyName, Typeface.NORMAL);
        p.setColor(Color.WHITE);
        p.setTypeface(font);
        p.setTextSize(timeSize);

        float wordWidth = p.measureText(word);
        float startX = 0 ,startY =0 ;

        startY = bitmap.getHeight()/2;

        if(bitmap.getWidth()>wordWidth){//一行写下
            startX = (bitmap.getWidth() - wordWidth)/2;
        }
        canvas.drawText(word, startX, startY, p);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();

        iv_image.invalidate();
    }
}
