package com.luosifan.photopicker.picker;

import com.luosifan.photopicker.model.PhotoFilter;
import com.luosifan.photopicker.model.PickerParams;
import com.luosifan.photopicker.model.PickerTheme;

/**
 * Created by wzfu on 16/5/10.
 */
public class Load {

    private PickerParams params;
    private int enterAnim;
    private int exitAnim;

    public Load(){
        this.params = new PickerParams();
        // 默认显示列数
        this.params.gridColumns = 3;
    }

    public Load showCamera(boolean showCamera){
        this.params.showCamera = showCamera;
        return this;
    }

    public Load gridColumns(int columns){
        if(columns <= 0){
            throw new IllegalArgumentException("columns must not be > 0.");
        }
        this.params.gridColumns = columns;
        return this;
    }

    public Load filter(PhotoFilter filter){
        this.params.filter = filter;
        return this;
    }

    /**
     * 启动页面动画
     * @param enterAnim
     * @param exitAnim
     * @return
     */
    public Load anim(int enterAnim, int exitAnim){
        this.enterAnim = enterAnim;
        this.exitAnim = exitAnim;
        return this;
    }

    public Load theme(PickerTheme theme){
        this.params.theme = theme;
        return this;
    }

    public SingleSelect singleSelect(){
        return new SingleSelect(params, enterAnim, exitAnim);
    }

    public Multiselect multiselect(){
        return new Multiselect(params, enterAnim, exitAnim);
    }
}
