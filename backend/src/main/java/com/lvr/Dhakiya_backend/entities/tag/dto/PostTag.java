package com.lvr.Dhakiya_backend.entities.tag.dto;

import com.lvr.Dhakiya_backend.entities.tag.Tag;

public record PostTag(Long environmentId, String title) {
  public static Tag to(PostTag dto) {
    return new Tag(dto.title());
  }
}
