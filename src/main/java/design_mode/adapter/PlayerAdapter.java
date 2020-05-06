package design_mode.adapter;

/**
 * 你和你同伴一起合作开发，你同伴写一个部分，你写一个部分，现在两个部分要对接。
 *   结过到对接时，你们发现两个人都自定义了接口，而且两个人都开发完了，都不想改，那怎么办，只能写一个适配器去适配两个接口。
 * 又或者说你开发新版本的时候重新定义了接口，要和旧版本写适配的时候，为了方便也可以使用适配器模式。
 *
 */
public class PlayerAdapter implements Player{
    private MP4 mp4;
    public PlayerAdapter(MP4 mp4){
        this.mp4 = mp4;
    }
    @Override
    public void action() {
        if(this.mp4 != null){
            this.mp4.play();
        }
    }
}
