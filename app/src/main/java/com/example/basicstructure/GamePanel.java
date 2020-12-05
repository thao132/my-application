package com.example.basicstructure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    Element myelement;
    ParallaxBackground background;
    ArrayList<Enemies> enemies = new ArrayList<Enemies>();
    int thoigiannapdan = 0;
    int thoigiankethu = 0;

    Enemies motkethu;
    ArrayList<Bullet> bullets = new ArrayList<>();


    public void doDrawBullet(Canvas canvas) {

        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setTextSize(20);
        canvas.drawRect(10, 10, thoigiannapdan*10, 20, p);
        if (thoigiannapdan >= 10) {
            thoigiannapdan = 0;
            Bullet onebullet = new Bullet(getResources(), myelement.mX, myelement.mY, R.drawable.fire);
            bullets.add(onebullet);
        }

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).doDraw(canvas);
        }
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).x > canvas.getWidth()) {
                bullets.remove(i);
            }
        }
    }


    public boolean vc_b_e(Bullet bullet, Enemies enemies) {
        float nuarong_b = (float) bullet.getWidth() / 2;
        int nuacao_b = bullet.getHeight() / 2;
        float nuarong_e = (float) enemies.getWidth() / 2;
        int nuacao_e = enemies.getHeight() / 2;
        int kc_ht_x = Math.abs(bullet.gettamX() - enemies.gettamX());
        int kc_ht_y = Math.abs(bullet.gettamY() - enemies.gettamY());
        if (kc_ht_x <= nuarong_b + nuarong_e && kc_ht_y <= nuacao_b + nuacao_e)
            return true;
        else
            return false;
    }

    public void xetvacham(Canvas canvas) {
        try {
            for (int i = 0; i < bullets.size(); i++)
                for (int j = 0; j < enemies.size(); j++) {
                    if (vc_b_e(bullets.get(i), enemies.get(j)) == true) {
                        bullets.remove(i);
                        enemies.remove(j);
                    }
                }
        } catch (Exception e) {
            Log.d("loi", e.toString());
        }
    }


    public void doDrawEnemies(Canvas canvas) {
        if (thoigiankethu >= 10) {
            thoigiankethu = 0;
            Enemies motkethu = new Enemies(getResources(),
                    canvas.getWidth(), canvas.getHeight());
            enemies.add(motkethu);
        }
        for (int i = 0; i < enemies.size(); i++)
            enemies.get(i).doDraw(canvas);
        for (int i = 0; i < enemies.size(); i++)
            if (enemies.get(i).y < 0)
                enemies.remove(i);
        Log.d("viendan", "so vien: " + enemies.size());
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        background = new ParallaxBackground(this.getResources());
        setFocusable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        background.doDrawRunning(canvas);
        thoigiannapdan++;
        thoigiankethu++;
        if (myelement != null) {
            myelement.doDraw(canvas);
            this.doDrawBullet(canvas);
            this.doDrawEnemies(canvas);
            xetvacham(canvas);
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        if (thread.isAlive()) {
            thread.setRunning(false);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        if (myelement == null) {
            myelement = new Element(getResources(), (int) event.getX(), (int) event.getY());
            Log.d("abc", "khoi tao dau tien");
            return true;
        } else {
            myelement.mX = (int) event.getX() - myelement.bitmap.getWidth() / 2;
            myelement.mY = (int) event.getY() - myelement.bitmap.getHeight() / 2;
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            myelement.mX = (int) event.getX() - myelement.bitmap.getWidth() / 2;
            myelement.mY = (int) event.getY() - myelement.bitmap.getHeight() / 2;
            Log.d("abc", "ddddddddddddddddddddddddddddown");
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            myelement.mX = (int) event.getX() - myelement.bitmap.getWidth() / 2;
            myelement.mY = (int) event.getY() - myelement.bitmap.getHeight() / 2;
            Log.d("abc", "uuuuuuuuuuuuuuuuuuuuuuuuuuuup");
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            myelement.mX = (int) event.getX() - myelement.bitmap.getWidth() / 2;
            myelement.mY = (int) event.getY() - myelement.bitmap.getHeight() / 2;
            Log.d("abc", "mmmmmmmmmmmmmmmmmmmmmmmmmmove");
        }

        return true;
    }


}
