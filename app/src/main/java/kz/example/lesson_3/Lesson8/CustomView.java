package kz.example.lesson_3.Lesson8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

public class CustomView extends View {
    private Paint paint;

    public CustomView(Context context, AttributeSet attributeSet) {
        super(context);
        init();
    }

    public CustomView(Context context) {
        super(context);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, 0, getWidth(), getHeight(), paint);
        canvas.drawCircle(getWidth()/2, getHeight()/2, 50, paint);
    }
}
