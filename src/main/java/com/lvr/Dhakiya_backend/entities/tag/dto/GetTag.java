package com.lvr.Dhakiya_backend.entities.tag.dto;

import com.lvr.Dhakiya_backend.entities.tag.Tag;

public record GetTag(Long id, String title) {
  public static GetTag from(Tag tag) {
    return new GetTag(tag.getId(), tag.getTitle());
  }
}
