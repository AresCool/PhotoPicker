package com.luosifan.photopicker.adapter;

import com.luosifan.photopicker.bean.Image;

import java.util.List;

/**
 * Created by donglua on 15/6/30.
 */
public interface Selectable {


  /**
   * Indicates if the item at position position is selected
   *
   * @param image Photo of the item to check
   * @return true if the item is selected, false otherwise
   */
  boolean isSelected(Image image);

  /**
   * Toggle the selection status of the item at a given position
   *
   * @param image Photo of the item to toggle the selection status for
   */
  void toggleSelection(Image image);

  /**
   * Clear the selection status for all items
   */
  void clearSelection();

  /**
   * Count the selected items
   *
   * @return Selected items count
   */
  int getSelectedItemCount();

  /**
   * Indicates the list of selected photos
   *
   * @return List of selected photos
   */
  List<Image> getSelectedPhotos();

}
