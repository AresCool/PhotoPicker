# PhotoPicker

基于 [donglua/PhotoPicker](https://github.com/donglua/PhotoPicker)、[lovetuzitong/MultiImageSelector](https://github.com/lovetuzitong/MultiImageSelector)
修改的一个图片选择类库，

## 效果图
![](/renderings/image_01.png)
![](/renderings/image_02.png)
![](/renderings/image_03.png)
![](/renderings/image_04.png)

## 使用方法

### 单选

```java
PhotoPickerIntent intent = new PhotoPickerIntent(MainActivity.this);
intent.setSelectModel(SelectModel.SINGLE);
intent.setShowCarema(true);
//intent.setImageConfig(null);
startActivityForResult(intent, REQUEST_CAMERA_CODE);
```

### 多选

```java
    PhotoPickerIntent intent = new PhotoPickerIntent(MainActivity.this);
    intent.setSelectModel(SelectModel.MULTI);
    intent.setSelectModel(SelectModel.MULTI);
    intent.setShowCarema(true); // 是否显示拍照
    intent.setMaxTotal(9); // 最多选择照片数量，默认为9
    intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
    //ImageConfig config = new ImageConfig();
    //config.minHeight = 400;
    //config.minWidth = 400;
    //config.mimeType = new String[]{"image/jpeg", "image/png"};
    //config.minSize = 1 * 1024 * 1024;
    //intent.setImageConfig(config);
    startActivityForResult(intent, REQUEST_CAMERA_CODE);
```