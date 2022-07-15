package com.star4droid.tutoM;
import android.view.View;
import android.widget.TextView;
import android.view.WindowManager.*;
import android.view.WindowManager;
import android.animation.ValueAnimator;
import android.graphics.PixelFormat;
import android.os.Looper;
import android.os.Handler;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Gravity;
public class TutorialMaker {
public TutorialMaker(Context ctx){
this.ctx = ctx;
	wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
		LP = new WindowManager.LayoutParams();
			
			LP.type = WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW;
	
	LP.format = PixelFormat.RGBA_8888;
	
	LP.gravity = Gravity.LEFT | Gravity.TOP;
	
	LP.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |  WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
	LP.width = WindowManager.LayoutParams.WRAP_CONTENT;
	LP.height = WindowManager.LayoutParams.WRAP_CONTENT;
	
	LP.x = 0;
	LP.y = 0;
	LayoutInflater fxL = LayoutInflater.from(ctx);
this.view = fxL.inflate(R.layout.mouse, null);
text = this.view.findViewById(R.id.text);
//this.view.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)0, (int)1, 0xFF607D8B, Color.WHITE));
}
public void hide(){
	try {this.wm.removeView(this.view);} catch(Exception ex){}
}
public WindowManager wm;
public Context ctx;
public TextView text;
public WindowManager.LayoutParams LP;
public View view;
public interface onEnd {
	public void onEnd();
}
public onEnd end;
public TutorialMaker setOnEnd(onEnd i){
	this.end=i;
	return this;
}
public TutorialMaker showFrom(final float x1,final float y1,final float x2,final float y2,final int duration){
this.LP.x=0;
this.LP.y=0;
try {
this.wm.addView(this.view,this.LP);
} catch (Exception ex){
this.wm.updateViewLayout(this.view,this.LP);
} 
final WindowManager.LayoutParams lp=this.LP; 
final WindowManager WM=this.wm;
final View v=this.view;
final TutorialMaker tm=this;
final ValueAnimator v1 = ValueAnimator.ofFloat((float)(x1),(float)(x2));
final ValueAnimator v2 = ValueAnimator.ofFloat((float)(y1),(float)(y2));
v1.setDuration((long)(duration));
v2.setDuration((long)(duration));
v1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
@Override
public void onAnimationUpdate(ValueAnimator va) {
float fValue = (float)(va.getAnimatedValue());
lp.x=(int)fValue;
WM.updateViewLayout(v,lp);
}
});
v2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
@Override
public void onAnimationUpdate(ValueAnimator va) {
float fValue = (float)(va.getAnimatedValue());
lp.y=(int)fValue;
WM.updateViewLayout(v,lp);
}
});
class th extends Thread {
@Override
public void run(){
try {
java.lang.Thread.sleep(duration);
} catch (java.lang.InterruptedException ex564) {}
		new Handler(Looper.getMainLooper()).post(new Runnable() {
			@Override
			public void run() {
				if(tm.end!=null) tm.end.onEnd();
			}
		});
}
}
new th().start();
v1.start();
v2.start();
return this;
}
public TutorialMaker moveTo(float x,float y,int duration){
this.showFrom(this.LP.x,this.LP.y,x,y,duration);
return this;
}
public TutorialMaker showMessage(String s){
text.setText(s);
text.setVisibility(View.VISIBLE);
return this;
}
public void hideMessage(){
text.setVisibility(View.GONE);
}
public TutorialMaker showIn(View v,int duration){
int x=(int)(v.getMeasuredWidth()/2); 
int y=(int)(v.getMeasuredHeight()/2);
int _location[] = new int[2];
v.getLocationOnScreen(_location);
this.showFrom(this.LP.x,this.LP.y,_location[0]+x,_location[1],duration);
return this;
}
}
