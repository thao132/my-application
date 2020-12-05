package com.example.basicstructure;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

    private boolean running;
    private SurfaceHolder surfaceholder;
    private GamePanel gamepanel;

    public MainThread(SurfaceHolder surfaceholder, GamePanel gamepanel) {
        this.surfaceholder = surfaceholder;
        this.gamepanel = gamepanel;
    }

    public void setRunning(boolean r) {
        running = r;
    }

    @SuppressLint("WrongCall")
    @Override
    public void run() {
        super.run();
        long dem = 0L;
        Canvas canvas = null;
        while (running) {
            canvas = surfaceholder.lockCanvas();
            if (canvas != null) {
                gamepanel.onDraw(canvas);
                surfaceholder.unlockCanvasAndPost(canvas);
            }
            Log.d("testloop:", "loop " + (dem++));
        }
    }
}
