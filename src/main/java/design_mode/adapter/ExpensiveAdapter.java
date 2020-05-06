package design_mode.adapter;

/**
 * 一般不用这类适配器，感觉这样的做法不太灵活，而且在java中，尽量少用继承，多用组合
 */
public class ExpensiveAdapter extends ExpensiveMP4 implements Player{
    @Override
    public void action() {
        play();
    }
}
