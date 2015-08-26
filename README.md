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
intent.setShowCarema(true); // 是否显示拍照， 默认false
// intent.setImageConfig(null); // 设置显示照片的参数
startActivityForResult(intent, REQUEST_CAMERA_CODE);
```

### 多选

```java
PhotoPickerIntent intent = new PhotoPickerIntent(MainActivity.this);
intent.setSelectModel(SelectModel.MULTI);
intent.setShowCarema(true); // 是否显示拍照， 默认false
intent.setMaxTotal(9); // 最多选择照片数量，默认为9
intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
// intent.setImageConfig(config); // 设置显示照片的参数
startActivityForResult(intent, REQUEST_CAMERA_CODE);
```

### 预览

```java
PhotoPreviewIntent intent = new PhotoPreviewIntent(MainActivity.this);
intent.setCurrentItem(position); // 当前选中照片的下标
intent.setPhotoPaths(imagePaths); // 已选中的照片地址
startActivityForResult(intent, REQUEST_PREVIEW_CODE);
```

### ActivityResult Setting

```java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if(resultCode == RESULT_OK) {
        switch (requestCode) {
            // 选择照片
            case REQUEST_CAMERA_CODE:
                loadAdpater(data.getStringArrayListExtra(`PhotoPickerActivity.EXTRA_RESULT`));
                break;
            // 预览
            case REQUEST_PREVIEW_CODE:
                loadAdpater(data.getStringArrayListExtra(`PhotoPreviewActivity.EXTRA_RESULT`));
                break;
        }
    }
}
```