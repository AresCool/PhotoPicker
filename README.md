# PhotoPicker

> 基于 [lovetuzitong/MultiImageSelector](https://github.com/lovetuzitong/MultiImageSelector) 修改的一个照片选择库。

功能：

- 支持主流图片加载库
- 照片尺寸、Gif过滤
- 运行时权限检查

## 截图
![](http://ww1.sinaimg.cn/mw690/006fnUCcgw1f4tn5o3110j308c0etjsd.jpg)
![](https://github.com/wzfu/PhotoPicker/raw/1.0/renderings/image_02.png)
![](https://github.com/wzfu/PhotoPicker/raw/1.0/renderings/image_03.png)
![](https://github.com/wzfu/PhotoPicker/raw/1.0/renderings/image_04.png)

## 使用介绍

- Applicaton 初始化
````
PhotoPicker.init(ImageLoader, null);
 - [PicassoImageLoader](/sample/src/main/java/cc/dagger/photopicker/demo/imagloader/PicassoImageLoader.java)
```