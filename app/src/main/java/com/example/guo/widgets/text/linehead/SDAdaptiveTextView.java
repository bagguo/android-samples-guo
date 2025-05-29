package com.example.guo.widgets.text.linehead;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author Darren.eth
 * @title 解决TextView文字排版中文行末不对齐问题
 * 效果见[./中文行末对不齐.jpg]
 *
 * 解决方案：
 * 如果整行宽度超过控件可用宽度，则按字符测量，在超过可用宽度的前一个字符处手动换行
 *
 * 来源：https://cloud.tencent.com/developer/article/1899344
 */
public class SDAdaptiveTextView extends AppCompatTextView {

    public SDAdaptiveTextView(Context context) {
        super(context);
    }

    public SDAdaptiveTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SDAdaptiveTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 使用该方法设置TextView的文本内容, 该方法不能再主线程中执行
     *
     * @param text
     */
    public void setAdaptiveText(String text) {
        this.setText(text);
        this.setText(adaptiveText(this));
    }

    private String adaptiveText(final TextView textView) {
        //原始文本
        final String text = textView.getText().toString();
        //获取TextView的Paint
        final Paint paint = textView.getPaint();
        //TextView的可用宽度
        final float width = textView.getWidth() - textView.getPaddingLeft() - textView.getPaddingRight();

        //将原始文本按行拆分
        String[] lines = text.replaceAll("\r", "").split("\n");
        StringBuilder builder = new StringBuilder();

        for (String line : lines) {

            //文本内容小于TextView宽度，即不换行，不作处理
            if (paint.measureText(line) <= width) {
                builder.append(line);
            } else {
                //如果整行宽度超过控件可用宽度，则按字符测量，在超过可用宽度的前一个字符处手动换行
                float lineWidth = 0;

                for (int i = 0; i != line.length(); ++i) {
                    char c = line.charAt(i);
                    lineWidth += paint.measureText(String.valueOf(c));
                    if (lineWidth <= width) {
                        builder.append(c);
                    } else {
                        //单行超过TextView可用宽度，换行
                        builder.append("\n");
                        lineWidth = 0;
                        --i;//该代码作用是将本轮循环回滚，在新的一行重新循环判断该字符
                    }
                }
            }
        }
        return builder.toString();
    }
}
