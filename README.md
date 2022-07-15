# TutorialMaker
tell the user what he/she should do using this class.

- create new pointer 
```
TutorialMaker t = new TutorialMaker(YourActivity.this);
```
- move the pointer to x y 
```
t.moveTo(x,y,duration);
```
- move the pointer to View
```
t.showIn(View,duration);
```
- move the pointer from x1,y1 to x2,y2
```
t.showFrom(x1,y1,x2,y2,duration);
```
- show message with the pointer
```
t.showMessage("hi bro"):
```
- hide the message 
```
t.hideMessage():
```
- direct show with configuration 
```
new TutorialMaker(this).moveTo(x,y,duration)
.showIn(View,duration)
.showMessage("hi")
.setOnEnd(new TutorialMaker.onEnd(){
//something
});
```
