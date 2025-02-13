package com.lvr.Dhakiya_backend.entities.progressreport.dto;

import com.lvr.Dhakiya_backend.entities.progressreport.ProgressReport;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.dto.DetailedTag;
import com.lvr.Dhakiya_backend.entities.tag.enums.Status;
import java.util.Comparator;
import java.util.List;

public record GetProgressReport(
    Long id,
    Long environmentId,
    List<DetailedTag> tags,
    DetailedTag strength,
    DetailedTag weakness) {
  public static GetProgressReport from(ProgressReport progressReport, List<Tag> tags) {
    List<DetailedTag> detailedTags = tags.stream().map(tag -> DetailedTag.from(tag)).toList();
    DetailedTag strength =
        detailedTags.stream().max(Comparator.comparing(DetailedTag::percentage)).orElse(null);

    if (strength == null || strength.status() == Status.NO_DATA) {
      strength = null;
    }
    DetailedTag weakness =
        detailedTags.stream().min(Comparator.comparing(DetailedTag::percentage)).orElse(null);

    if (weakness == null || weakness.status() == Status.NO_DATA) {
      weakness = null;
    }

    return new GetProgressReport(
        progressReport.getId(),
        progressReport.getEnvironment().getId(),
        detailedTags,
        strength,
        weakness);
  }
}
