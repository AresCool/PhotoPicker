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
intent.setShowCarema(true);
intent.setMaxTotal(9);
intent.setSelectedPaths(imagePaths);
//ImageConfig config = new ImageConfig();
//config.minHeight = 400;
//config.minWidth = 400;
//config.mimeType = new String[]{"image/jpeg", "image/png"};
//config.minSize = 1 * 1024 * 1024;
//intent.setImageConfig(config);
startActivityForResult(intent, REQUEST_CAMERA_CODE);
```