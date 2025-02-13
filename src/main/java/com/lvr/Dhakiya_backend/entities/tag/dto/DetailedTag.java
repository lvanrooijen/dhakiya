package com.lvr.Dhakiya_backend.entities.tag.dto;

import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.enums.Status;

public record DetailedTag(Long id, String title, Double percentage, Status status) {
  public static DetailedTag from(Tag tag) {
    return new DetailedTag(tag.getId(), tag.getTitle(), tag.getPercentage(), tag.getStatus());
  }
}
